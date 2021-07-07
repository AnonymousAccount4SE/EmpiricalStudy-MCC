# coding=utf-8

result_list = []
actual_list = []

root_path = 'test_1222_66/consis/'

with open(root_path + 'result.txt', 'r') as f:
    for line in f:
        result_list.append(line.strip())

with open(root_path + 'ParsedMethodNameTokens.txt', 'r') as f:
    for line in f:
        actual_list.append(line.rstrip())

print(len(actual_list))

actual_list = actual_list[0:len(result_list)]

print(len(actual_list), len(result_list))

in_consistent = 0
consistent = 0

for i in range(len(actual_list)):
    actual_name = actual_list[i].split(' ')
    result_name = result_list[i].split(' ')
    mother = (len(actual_name) + len(result_name)) / 2
    child = len(list(set(actual_name).intersection(set(result_name))))
    # print(actual_name,result_name,child, mother)
    if child / mother > 0.94:
        consistent += 1
    else:
        in_consistent += 1

print(consistent, in_consistent)
