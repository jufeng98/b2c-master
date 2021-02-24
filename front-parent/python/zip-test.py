import zipfile


def sanitize(time):
    return time.replace("-", ".").replace(":", ".")


try:
    with zipfile.ZipFile("data/hfpy_ch5_data.zip") as zipFile:
        print(zipFile.namelist())
        players = []
        for name in zipFile.namelist():
            fileContent = zipFile.read(name).decode('utf-8')
            fileContent = sanitize(fileContent)
            players.append(sorted(fileContent.strip().split(",")))
        print(players)
except IOError as e:
    print("error" + str(e))

try:
    with zipfile.ZipFile("data/hfpy_ch5_data.zip") as zipFile:
        print(zipFile.namelist())
        players = []
        for name in zipFile.namelist():
            fileContent = zipFile.read(name).decode('utf-8')
            # 列表推导
            player = [sanitize(content) for content in fileContent.strip().split(",")]
            players.append(sorted(set(player))[0:3])
        print(players)
except IOError as e:
    print("error" + str(e))

try:
    with zipfile.ZipFile("data/hfpy_ch6_data.zip") as zipFile:
        print(zipFile.namelist())
        players = []
        for name in zipFile.namelist():
            fileContent = zipFile.read(name).decode('utf-8')
            infos = fileContent.strip().split(",")
            player = {
                "name": infos[0],
                "birthday": infos[1]
            }
            scores = infos[2:len(infos) - 1]
            # 列表推导
            scores = [sanitize(content) for content in scores]
            player["scores"] = sorted(set(scores))[0:3]
            players.append(player)
        print(players)
except IOError as e:
    print("error" + str(e))


class Player:
    def __init__(self, fullname, birthday, times):
        self.name = fullname
        self.birthday = birthday
        self.scores = times

    def top3(self):
        return sorted(set([sanitize(content) for content in self.scores]))[0:3]


try:
    with zipfile.ZipFile("data/hfpy_ch6_data.zip") as zipFile:
        print(zipFile.namelist())
        players = []
        for name in zipFile.namelist():
            fileContent = zipFile.read(name).decode('utf-8')
            infos = fileContent.strip().split(",")
            scores = infos[2:len(infos) - 1]
            player = Player(infos[0], infos[1], scores)
            players.append(player)
        print(players)
except IOError as e:
    print("error" + str(e))


class NamedList(list):
    def __init__(self, a_name):
        list.__init__([])
        self.name = a_name


jonny = NamedList("Jonny")
print(type(jonny))
print(dir(jonny))
