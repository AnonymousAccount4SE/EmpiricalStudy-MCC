# coding=utf-8

import tensorflow as tf
import os
import numpy as np
import time

os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
os.environ["CUDA_VISIBLE_DEVICES"] = "2"
# np.random.seed(1337)
gpu = tf.config.experimental.list_physical_devices(device_type='GPU')
tf.config.experimental.set_memory_growth(gpu[0], True)

data = list()
for i in range(5):
    data.append(np.random.randint(1, 4, 3))

start = time.time()
x = tf.constant(data, tf.float32)
print(x)
end = time.time()
print(end - start)

start = time.time()
for i in range(1):
    y = tf.constant(np.random.randint(1, 4, 3), tf.float32)
    x_norm = tf.sqrt(tf.reduce_sum(tf.square(x), axis=1))
    y_norm = tf.sqrt(tf.reduce_sum(tf.square(y), axis=0))

    x_y = tf.reduce_sum(tf.multiply(x, y), 1)
    cos = tf.divide(x_y, tf.multiply(x_norm, y_norm))

    values, indices = tf.math.top_k(cos, 2)
    test = values.numpy().tolist()
    # euclidean = tf.minimum(euclidean)

    # print(euclidean)
end = time.time()
print(end - start)
