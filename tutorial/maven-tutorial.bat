:: 超级pom所在的位置: $M2_HOME/lib/maven-model-builder-3.0.jar
:: 路径org/apache/maven/model/pom-4.0.0.xml

:: 打印出所有的Java系统属性和环境变量
mvn help:system

:: 取得帮助
help:describe -Dplugin=compiler -Ddetail
help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin:3.6.1 -Ddetail

:: maven-shade-plugin插件可生成可执行的jar文件

:: -pl构建指定模块 -am先构建模块的依赖模块
mvn clean install -pl b2c-cloud-test-boot -am

:: 编译
clean compile

:: clean插件的clean目标
clean:clean

:: compiler插件的compile目标
compiler:compile

:: Nexus默认管理员账号密码admin/admin123

:: 打包时跳过运行测试代码
package -DskipTests

:: 打包时跳过编译和运行测试代码,同时控制了两个插件的行为
package -Dmaven.test.skip=true

:: 主资源处理 主代码编译 测试资源处理 测试代码编译
clean test

:: 只执行特定的测试类,当然也可以在surefire插件中配置执行或者不执行某些测试类
test -Dtest=BootTest

:: 打包
clean package

:: 安装到本地仓库
clean install

:: 部署到远程仓库
clean deploy

:: 打包发布到仓库
clean package deploy

:: 使用meavn-shade-plugin生成可执行的jar文件

:: 依赖范围:控制依赖与这三种classpath(编译classpath 测试classpath 运行classpath)的关系
:: 三种classpath都有效 如spring-core
compile
:: 测试classpath有效 如junit 只在编译和运行测试代码有效
test
:: 编译和测试classpath有效 如servlet-api 在运行项目时容器已经提供,不需要maven重复引入
provided
:: 测试和运行classpath有效 如jdbc驱动 编译时只需要jdbc提供的接口 只有在测试和运行需要jdbc的具体实现
runtime
:: 同provided 但是必须指定systemPath 会造成不可移植的风险
system
:: 不会产生实际影响,通常指向packaging类型为pom的模块
import

:: 列出项目已解析依赖
dependency:list

:: 列出项目的依赖树
dependency:tree

:: 分析项目的依赖情况
dependency:analyze

:: 三个生命周期:clean清理项目 default构建项目 site建立项目站点
:: clean包括:
pre-clean clean(maven-clean-plugin:clean) post-clean
:: default包括:
validate initialize process-resources(maven-resources-plugin:resources) compiler(maven-compiler-plugin:compile)
process-test-resources(maven-resources-plugin:testResources) test-compiler(maven-compiler-plugin:testCompile)
test(maven-surefile-plugin:test) prepare-package package(maven-jar-plugin:jar) integration-test verify
install(maven-install-plugin:install) deploy(maven-deploy-plugin:deploy)
:: site包括:
pre-site site(maven-site-plugin:site) post-site site-deploy(maven-site-plugin:deploy)

:: 启动内置的jetty web容器
jetty:run -Djetty.port=9080

:: 查看当前激活的profile
help:active-profiles
:: 查看所有的profile
help:all-profiles