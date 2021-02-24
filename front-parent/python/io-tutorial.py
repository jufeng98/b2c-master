import os
import pickle
import sqlite3

s = None
print(s is not None)
paths = os.getcwd().split(os.path.sep)
service_name = paths[len(paths) - 1]
print(service_name)
print(os.path.exists("data/book.json"))
data = open("data/book.json")
result = ""
result1 = ""
for line in data:
    try:
        result = result + line.strip()
        result1 = result1 + line
        print(line.strip(), end='')
    except ValueError:
        pass
data.close()

try:
    with open("data/book.json", "r") as out:
        lines = out.read().splitlines()
        for line in lines[30:38]:
            print(line)
        for line in lines[-5:]:
            print(line)
except IOError as e:
    print("error" + str(e))

if not os.path.exists("target"):
    os.mkdir("target")

out = open("target/test.json", "w")
try:
    print(result, file=out)
except IOError as e:
    print("error" + str(e))
finally:
    if out in locals():
        out.close()

try:
    with open("target/test.json", "w") as out:
        print(result1, file=out)
except IOError as e:
    print("error" + str(e))

try:
    with open("target/data.pickle", "wb") as dataPickle:
        pickle.dump([1, 2, "three"], dataPickle)

    with open("target/data.pickle", "rb") as dataPickle:
        anList = pickle.load(dataPickle)
except IOError as e:
    print("error" + str(e))

connection = sqlite3.connect("target/test.sqlite")
cursor = connection.cursor()
cursor.execute("""SELECT DATE('NOW')""")
connection.commit()
connection.close()
