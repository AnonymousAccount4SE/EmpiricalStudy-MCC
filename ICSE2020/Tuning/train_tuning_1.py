
import tensorflow as tf
import numpy as np
import os
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Dense, GRU, Embedding
from tensorflow.keras.optimizers import RMSprop
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint, TensorBoard
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from sklearn.metrics import accuracy_score
from sklearn.model_selection import cross_val_score, KFold
import nni
import logging

from torch.backends._nnapi.serializer import LOG

def sparse_cross_entropy(y_true, y_pred):
    """
    Calculate the cross-entropy loss between y_true and y_pred.

    y_true is a 2-rank tensor with the desired output.
    The shape is [batch_size, sequence_length] and it
    contains sequences of integer-tokens.

    y_pred is the decoder's output which is a 3-rank tensor
    with shape [batch_size, sequence_length, num_words]
    so that for each sequence in the batch there is a one-hot
    encoded array of length num_words.
    """
    print(y_true.shape)
    print(y_pred.shape)
    # Calculate the loss. This outputs a
    # 2-rank tensor of shape [batch_size, sequence_length]
    loss = tf.nn.sparse_softmax_cross_entropy_with_logits(labels=tf.cast(y_true, tf.int32),
                                                          logits=y_pred)

    # loss = tf.nn.softmax_cross_entropy_with_logits(labels=y_true,
    #                                                       logits=y_pred)
    # Keras may reduce this across the first axis (the batch)
    # but the semantics are unclear, so to be sure we use
    # the loss across the entire 2-rank tensor, we reduce it
    # to a single scalar with the mean function.
    loss_mean = tf.reduce_mean(loss)

    return loss_mean


def get_default_parameters():
    params = {
        'learning_rate': 0.001,
        'embedding_size': 128,
        'batch_size': 1024,
        'state_size': 256

    }
    return params

def main(PARAMS):

    # print(args)
    os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
    os.environ["CUDA_VISIBLE_DEVICES"] = "1"
    gpu = tf.config.experimental.list_physical_devices(device_type='GPU')
    tf.config.experimental.set_memory_growth(gpu[0], True)


    # data preprocessing


    result_path = '/home1/michael/ICSE2020/ICSE2020_Code/result/'
    data_path = '/home1/michael/ICSE2020/ICSE2020_Code/data/'
    method_name_path = data_path + 'parsedMethodNameTokens.txt'
    context_path = data_path + 'parsedMethodContextTokens.txt'
    # method_name_path = data_path + 'filter_name_tokens.txt'
    # context_path = data_path + 'filter_context_tokens.txt'

    mark_start = 'ssss '
    mark_end = ' eeee'

    def load_data(src=True, start="", end=""):
        results = []
        if src:
            path = context_path
            with open(path) as file:
                texts = [start + line.strip() + end for line in file]
                results.extend(texts)
        else:
            path = method_name_path
            with open(path) as file:
                texts = [start + line.strip() + end for line in file]
                results.extend(texts)
        return results

    data_src = load_data(src=True)
    data_dest = load_data(src=False, start=mark_start, end=mark_end)

    print(len(data_src))
    print(len(data_dest))

    idx = 10000
    print(data_src[idx])
    print(data_dest[idx])

    num_words = 30000

    class TokenizerWrap(Tokenizer):
        """Wrap the Tokenizer-class from Keras with more functionality."""

        def __init__(self, texts, padding, reverse=False, num_words=None):

            Tokenizer.__init__(self, num_words=num_words, filters='"#$.?@\\^_`~\t\n', split=' ')
            # Create the vocabulary from the texts.
            self.fit_on_texts(texts)
            # Create inverse lookup from integer-tokens to words.
            self.index_to_word = dict(zip(self.word_index.values(), self.word_index.keys()))

            self.tokens = self.texts_to_sequences(texts)
            if reverse:
                # Reverse the token-sequences.
                self.tokens = [list(reversed(x)) for x in self.tokens]
                truncating = 'pre'
            else:
                truncating = 'post'

            # The number of integer-tokens in each sequence.
            self.num_tokens = [len(x) for x in self.tokens]

            self.max_tokens = np.mean(self.num_tokens) \
                              + 2 * np.std(self.num_tokens)
            self.max_tokens = int(self.max_tokens)

            self.tokens_padded = pad_sequences(self.tokens,
                                               maxlen=self.max_tokens,
                                               padding=padding,
                                               truncating=truncating)

        def token_to_word(self, token):
            """Lookup a single word from an integer-token."""

            word = " " if token == 0 else self.index_to_word[token]
            return word

        def tokens_to_string(self, tokens):
            """Convert a list of integer-tokens to a string."""

            # Create a list of the individual words.
            words = [self.index_to_word[token]
                     for token in tokens
                     if token != 0]

            # Concatenate the words to a single string
            # with space between all the words.
            text = " ".join(words)

            return text

        def text_to_tokens(self, text, reverse=False, padding=False):
            tokens = self.texts_to_sequences([text])
            tokens = np.array(tokens)

            if reverse:
                # Reverse the tokens.
                tokens = np.flip(tokens, axis=1)
                truncating = 'pre'
            else:
                # Sequences that are too long should be truncated
                # at the end.
                truncating = 'post'

            if padding:
                # Pad and truncate sequences to the given length.
                tokens = pad_sequences(tokens,
                                       maxlen=self.max_tokens,
                                       padding='pre',
                                       truncating=truncating)

            return tokens

    tokenizer_src = TokenizerWrap(texts=data_src,
                                  padding='pre',
                                  reverse=True,
                                  num_words=num_words)

    tokenizer_dest = TokenizerWrap(texts=data_dest,
                                   padding='post',
                                   reverse=False,
                                   num_words=num_words)

    tokens_src = tokenizer_src.tokens_padded
    tokens_dest = tokenizer_dest.tokens_padded
    print(tokens_src.shape)
    print(tokens_dest.shape)

    token_start = tokenizer_dest.word_index[mark_start.strip()]
    print(token_start)
    token_end = tokenizer_dest.word_index[mark_end.strip()]
    print(token_end)

    idx = 0
    print(tokens_src[idx])
    print(tokenizer_src.tokens_to_string(tokens_src[idx]))
    print(data_src[idx])

    print(tokens_dest[idx])
    print(tokenizer_dest.tokens_to_string(tokens_dest[idx]))
    print(data_dest[idx])

    encoder_input_data = tokens_src
    decoder_input_data = tokens_dest[:, :-1]
    print(decoder_input_data.shape)

    decoder_output_data = tokens_dest[:, 1:]
    print(decoder_output_data.shape)

    idx = 22
    decoder_input_data[idx]
    decoder_output_data[idx]
    tokenizer_dest.tokens_to_string(decoder_input_data[idx])
    tokenizer_dest.tokens_to_string(decoder_output_data[idx])



    x_data = \
        {
            'encoder_input': encoder_input_data,
            'decoder_input': decoder_input_data
        }

    y_data = \
        {
            'decoder_output': decoder_output_data
        }

    validation_split = 20000 / len(encoder_input_data)


    num_words = 30000
    encoder_input = Input(shape=(None,), name='encoder_input')

    embedding_size = PARAMS.get("embedding_size")
    # embedding_size = 128
    print("embedding_size:"+str(embedding_size))
    encoder_embedding = Embedding(input_dim=num_words,
                                  output_dim=embedding_size,
                                  name='encoder_embedding')

    # state_size = 512
    state_size = PARAMS.get("state_size")
    encoder_gru1 = GRU(state_size, name='encoder_gru1',
                       return_sequences=True)
    encoder_gru2 = GRU(state_size, name='encoder_gru2',
                       return_sequences=True)
    encoder_gru3 = GRU(state_size, name='encoder_gru3',
                       return_sequences=False)

    def connect_encoder():
        # Start the neural network with its input-layer.
        net = encoder_input

        # Connect the embedding-layer.
        net = encoder_embedding(net)

        # Connect all the GRU-layers.
        net = encoder_gru1(net)
        net = encoder_gru2(net)
        net = encoder_gru3(net)

        # This is the output of the encoder.
        encoder_output = net

        return encoder_output

    encoder_output = connect_encoder()

    decoder_initial_state = Input(shape=(state_size,),
                                  name='decoder_initial_state')

    decoder_input = Input(shape=(None,), name='decoder_input')

    decoder_embedding = Embedding(input_dim=num_words,
                                  output_dim=embedding_size,
                                  name='decoder_embedding')

    decoder_gru1 = GRU(state_size, name='decoder_gru1',
                       return_sequences=True)
    decoder_gru2 = GRU(state_size, name='decoder_gru2',
                       return_sequences=True)
    decoder_gru3 = GRU(state_size, name='decoder_gru3',
                       return_sequences=True)

    decoder_dense = Dense(num_words,
                          activation='linear',
                          name='decoder_output')

    def connect_decoder(initial_state):
        # Start the decoder-network with its input-layer.
        net = decoder_input

        # Connect the embedding-layer.
        net = decoder_embedding(net)

        # Connect all the GRU-layers.
        net = decoder_gru1(net, initial_state=initial_state)
        net = decoder_gru2(net, initial_state=initial_state)
        net = decoder_gru3(net, initial_state=initial_state)

        # Connect the final dense layer that converts to
        # one-hot encoded arrays.
        decoder_output = decoder_dense(net)

        return decoder_output

    decoder_output = connect_decoder(initial_state=encoder_output)

    model_train = Model(inputs=[encoder_input, decoder_input],
                        outputs=[decoder_output])

    model_encoder = Model(inputs=[encoder_input],
                          outputs=[encoder_output])

    decoder_output = connect_decoder(initial_state=decoder_initial_state)

    model_decoder = Model(inputs=[decoder_input, decoder_initial_state],
                          outputs=[decoder_output])

    optimizer = RMSprop(lr=PARAMS.get("learning_rate"))

    model_train.compile(optimizer=optimizer,
                        loss=sparse_cross_entropy)


    model_train.fit(x=x_data,
              y=y_data,
              # batch_size=1024,
              batch_size=PARAMS.get("batch_size"),
              # batch_size=1500,
              epochs=5,
              validation_split=validation_split,
              )

    def trans(input_text):
        input_tokens = tokenizer_src.text_to_tokens(text=input_text,
                                                    reverse=True,
                                                    padding=True)

        initial_state = model_encoder.predict(input_tokens)

        # Max number of tokens / words in the output sequence.
        max_tokens = tokenizer_dest.max_tokens

        shape = (1, max_tokens)
        decoder_input_data = np.zeros(shape=shape, dtype=np.int)

        # The first input-token is the special start-token for 'ssss '.
        token_int = token_start

        # Initialize an empty output-text.
        output_text = ''

        # Initialize the number of tokens we have processed.
        count_tokens = 0

        while token_int != token_end and count_tokens < max_tokens:
            decoder_input_data[0, count_tokens] = token_int

            x_data = \
                {
                    'decoder_initial_state': initial_state,
                    'decoder_input': decoder_input_data
                }

            decoder_output = model_decoder.predict(x_data)

            # Get the last predicted token as a one-hot encoded array.
            token_onehot = decoder_output[0, count_tokens, :]

            # Convert to an integer-token.
            token_int = np.argmax(token_onehot)

            # Lookup the word corresponding to this integer-token.
            sampled_word = tokenizer_dest.token_to_word(token_int)

            # Append the word to the output-text.
            output_text += " " + sampled_word

            # Increment the token-counter.
            count_tokens += 1

        # Sequence of tokens output by the decoder.
        output_tokens = decoder_input_data[0]

        return output_text

    gts = []
    labels = []
    logits = []
    all_srcs_test = []
    test_path = '/home1/michael/BadMethodName/Output_TestingRepos/InconsistentMethods/ICSE2020TestData/Tuning/'
    context_path = test_path + 'parsedMethodContextTokens.txt'
    # context_path = test_path + 'test.txt'
    gt_path = test_path + 'TestMethodNames.txt'
    label_path = test_path + 'TestLabels.txt'
    with open(context_path) as file:
        texts = [line.strip() for line in file]
        all_srcs_test.extend(texts)

    with open(gt_path) as file:
        texts = [line.strip() for line in file]
        gts.extend(texts)

    with open(label_path) as file:
        texts = [line.strip() for line in file]
        labels.extend(texts)

    for body in all_srcs_test:
        name = trans(body)
        print(name)
        name_trimed = name.replace(" eeee", "")
        name1 = []
        name1.append(name_trimed[1:])
        print(name1)
        logits.extend(name1)
    scores = 0
    for i in range(0,len(gts)):
        if gts[i] == logits[i] and labels[i] == "1":
            scores = scores + 1
        print(gts[i]+":"+logits[i])
    # scores = accuracy_score(labels,logits)
    nni.report_final_result(scores)
    # run(x_data, y_data, model,validation_split,model_encoder,model_decoder,trans)

if __name__ == '__main__':
    RECEIVED_PARAMS = nni.get_next_parameter()
    PARAMS = get_default_parameters()
    PARAMS.update(RECEIVED_PARAMS)
    main(PARAMS)

