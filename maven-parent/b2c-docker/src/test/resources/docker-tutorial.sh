#!/usr/bin/env bash

# 启动docker守护进程
systemctl start docker

# 查看版本
docker --version

# 拉取hello-world镜像
docker pull hello-world
# 使用hello-world运行一个容器
docker run hello-world

# 列出所有镜像
docker image ls
docker images

# 删除镜像,image_name表示镜像名，image_id表示镜像id
dockere image rm image_name/image_id
docker rmi image_name/image_id

# 列出本机正在运行的容器
$ docker container ls
docker ps
# 列出本机所有容器，包括终止运行的容器
$ docker container ls --all

# 启动容器,container_id表示容器的id
docker start container_id
docker start container_name

# 停止容器,container_id表示容器的id
docker stop container_id
docker stop container_name
docker container kill container_id

# 删除容器,container_id表示容器id，通过docker ps可以看到容器id
$ docker rm container_id
# 删除所有容器
docker rm $(docker ps -q)
# 删除所有退出的容器
docker container prune

# 制作自己的 Docker 容器
# 新建.dockerignore文件,指定要排除的路径，不要打包进入 image 文件。如果你没有路径要排除，这个文件可以不新建。
# 新建Dockerfile文件
# -t参数:指定 image 文件的名字，后面还可以用冒号指定标签。如果不指定，默认的标签就是latest。
docker build -t b2c-docker .
# -d参数:后台运行,-p参数:容器的8080端口映射到本机的8080端口
docker run -d -p 8080:8080 b2c-docker
# 启动时即进入到容器里面
docker run -p 8080:8080 -it b2c-docker

# 查看 docker 容器的输出，即容器里面 Shell 的标准输出
docker container logs container_id

# 进入一个正在运行的 docker 容器
# 如果docker run命令运行容器的时候，没有使用-it参数，就要用这个命令进入容器。一旦进入了容器，就可以在容器的 Shell 执行命令了
docker container exec -it container_id /bin/bash