#!/usr/bin/env bash
# 包含了一条条的指令，每一条指令构建一层，因此每一条指令的内容，就是描述该层应当如何构建
# 指定基础镜像
FROM nginx
# 维护者
LABEL maintainer="liangyudong"
# 指定工作目录
WORKDIR /mydir
# 等同于，在终端操作的 shell 命令, 在 docker build的时候执行
RUN echo '这是一个本地构建的nginx镜像' >/usr/share/nginx/html/index.html
# 从上下文目录中复制文件或者目录到容器里指定路径
COPY hom* /mydir/
COPY test.sh /mydir/
# 在 docker run的时候执行
CMD ["/mydir/test.sh","Jack"]
# 暴露的容器接口
EXPOSE 80
# 设置环境变量
ENV MYSQL_ROOT_PASSWORD 123456
# 开始构建docker镜像
docker build -t nginx:test .
# 类似于 CMD 指令，但其不会被 docker run 的命令行参数指定的指令所覆盖，而且这些命令行参数会被当作参数送给 ENTRYPOINT 指令指定的程序
ENTRYPOINT ["nginx", "-c"]
