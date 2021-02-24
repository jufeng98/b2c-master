# 安装模块 python3 setup.py install
import json
import math
import os

# 字符串,可用单引号,双引号,三引号(多行字符串)
print('hello' + " string!" + """welcome to
    python world
""")
# \在末尾,表示字符串接着这一行,而不是换行
print("a very long\
 string")
movies = ["The Holy Grail", "The Life Of Brian", "The Meaning Of Life"]
print(movies[1], len(movies))

array = ['hello', 'world']
for item in array:
    print(item, len(item))
array.append('python')
array.insert(0, 'first')
array.insert(1, 1973)
array.remove('hello')
print(array)
for num in range(3):
    print("range:" + str(num))
i = 0
while i < len(array):
    print(i, array[i])
    i = i + 1
multiArray = [[1], [1, 2, 3], [1, 2, 3, 4]]
for item in multiArray:
    for innerItem in item:
        print(innerItem)

names = ['Jack', 'Rose']
print(isinstance(names, list))

print(min(multiArray[2]), max(multiArray[2]), max(3, 4, 8))

print(round(2.3), math.pow(2, 10), math.ceil(2.3))

print(list("hello"))

times = 23
if times > 23:
    print('good job')
elif times > 13:
    print('normal job')
else:
    print('bad job')


def join(arr, splitter=''):
    result = ''
    if len(arr) == 1:
        return arr[1]
    else:
        index = 0
        while index < len(arr):
            if index != len(arr) - 1:
                result = result + arr[index] + splitter
            else:
                result = result + arr[index]
            index = index + 1
    return result


print(join(names, ','))

welcomeStr = "hello world"
(a1, a2) = welcomeStr.split(" ")
print(a1, a2)

moviesJsonStr = json.dumps(movies)
print(moviesJsonStr)
print(json.loads(moviesJsonStr))

print(os.environ["PATH"])

people = {
    "Alice": {
        "phone": "12",
        "age": 15
    },
    "Jack": {
        "phone": "13",
        "age": 17
    },
}
if "Alice" in people:
    print(people["Alice"])
print(people.keys(), people.values(), people.items())

# False None 0 "" {} [] 在条件表达式里均视为False
print(bool([]), bool(0), True + False + 10)
