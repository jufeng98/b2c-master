# vue-demo

> made by yudong

commonJS require模块的解析规则:

　　假设Y是路径，X是文件名或目录名，当 Nodejs 遇到 require(Y+X) 时，按照下面的顺序处理：

　　1、如果 X 是核心模块（例如：require("http")）

　　a.返回该模块

　　b.不再继续执行

　　2、如果Y是以“./”、“/”或“../”开头

　　a.把X当成文件，从指定路径开始，依次查找下面文件：X、X.js、X.json、X.node，只要其中一个存在，就返回该文件，不再继续执行

　　b.把X当成目录，从指定路径开始，依次查找下面文件：X/package.json(main字段)、X/index.js、X/index.json、X/index.node，只要其中一个存在，就返回该文件，不再继续执行

　　3.如果 X 不是核心模块，也没有以“./”、“/”或“../”开头，则Nodejs会从当前模块的父目录开始，尝试从它的 /node_module 目录里加载模块，如果还是没有找到，则移动到再上一层父目录，直到文件系统的根目录

　　4.抛出“not found”

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at dev.b2c.javamaster.com:99999
npm run dev

# serve with hot reload at dev.b2c.javamaster.com:99999
npm run mock

# build for test with minification
npm run buildtest

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/)
and [docs for vue-loader](http://vuejs.github.io/vue-loader).
