#配置git支持中文路径和文件名的正确显示
git config --global core.quotepath false
#只针对当前仓库
git config user.name "jufeng98" 添加用户
git config user.email "375709770@qq.com" 添加邮箱

#针对所有仓库
git config --global user.name "jufeng98" 添加用户
git config --golbal user.email "375709770@qq.com" 添加邮箱

#给git clone命令添加参数--depth 1，示例如下（添加该参数后，只会clone最近一次commit至本地，而不会把所有的commits都clone到本地，从而节约时间）
git clone --depth 1 https://github.com/jufeng98/arthas.git

#出现一个.git的隐藏文件夹,是Git的版本库,Git的版本库里存了很多东西，其中最重要的就是称为stage（或者叫index）
#的暂存区，还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD
git init 把当前目录变成Git可以管理的仓库 

#把文件添加到仓库stage,每次修改，如果不add到暂存区，那就不会加入到commit中。
git add readme.txt
#撤销对文件的add操作
git reset HEAD readme.txt
#可以用-f强制添加到Git
git add -f App.class
#文件提交到仓库master branch
git commit -m "commment"
#查看仓库状态
git status
#查看文件的不同之处
git diff readme.txt
#查看文件变更历史
git log
#美化输出
git log --pretty=oneline
#查看图形化变更历史
git log --graph --pretty=oneline --abbrev-commit
#查看所有版本记录
git reflog
#回退到上一个版本
git reset --hard HEAD~1
#回退到上上个版本,依此类推
git reset --hard HEAD~2
#回退到特定的版本,其中3628是该版本id的前几位
git reset --hard 3628
#丢弃所有的本地改动与提交,替换为服务器master代码
git reset --hard origin/master
#撤销对文件的修改
git checkout -- readme.txt
#从版本库删除文件,同时删除本地文件
git rm readme.txt
#撤销删除文件操作
git checkout -- readme.txt
#生成密钥
ssh-keygen -t rsa -C "375709770@qq.com"
#添加git远程仓库
git remote add origin https://github.com/jufeng98/b2c.git

#由于远程库是空的，我们第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送的远程
#新的master分支，还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。
#推送到远程主干仓库
git push -u origin master
#把本地master分支的最新修改推送至GitHub
git push origin master
#从远程库克隆一个本地库
git clone https://github.com/jufeng98/b2c.git

#创建远程origin的dev分支到本地
git checkout -b dev origin/dev
#指定本地dev分支与远程origin/dev分支的链接
git branch -u dev origin/dev

#把最新的提交从origin/dev抓下来，然后，在本地合并，解决冲突，再推送,如果git pull提示“no tracking information”，
#则说明本地分支和远程分支的链接关系没有创建，用命令
git branch -u branch-name origin/branch-name
#拉取远程代码
git pull

#创建并切换(-b)到dev分支
git checkout -b dev
#相当于
$ git branch dev
$ git checkout dev
#查看分支
git branch
#合并指定分支到当前分支
git merge dev
#删除指定分支
git branch -d dev
#强制删除该分支
git branch -D feature-vulcan

#合并分支时，如果可能，Git会用Fast forward模式，但这种模式下，删除分支后，会丢掉分支信息。
#如果要强制禁用Fast forward模式，Git就会在merge时生成一个新的commit，这样，从分支历史上就可以看出分支信息。
#合并dev分支，请注意--no-ff参数，表示禁用Fast forward
git merge --no-ff -m "merge with no-ff" dev

#将当前工作环境储存起来,以备将来恢复
git stash
#查看已保存的工作环境
git stash list
#恢复已保存的工作环境，但是恢复后，stash内容并不删除，你需要用git stash drop来删除
git stash apply
#恢复的同时把stash内容也删了
git stash pop
#可以多次stash，恢复的时候，先用git stash list查看，然后恢复指定的stash
git stash apply stash@{0}

#查看远程库的信息
git remote
#显示更详细的远程库信息
git remote -v

#打标签,默认标签是打在最新提交的commit上的
git tag v1.0
#为特定的commit id打上标签
git tag v0.9 6224937
#查看标签信息
git show v0.9
#创建带有说明的标签，用-a指定标签名，-m指定说明文字
git tag -a v0.1 -m "version 0.1 released" 3628164
#通过-s用私钥签名一个标签,签名采用PGP签名，因此，必须首先安装gpg（GnuPG），如果没有找到gpg，或者没有gpg密钥对，就会报错
git tag -s v0.2 -m "signed version 0.2 released" fec145a
#标签打错了，也可以删除,因为创建的标签都只存储在本地，不会自动推送到远程。所以，打错的标签可以在本地安全删除。
git tag -d v0.1
#推送某个标签到远程
git push origin v1.0
#一次性推送全部尚未推送到远程的本地标签
git push origin --tags
#标签已经推送到远程，要删除远程标签就麻烦一点，先从本地删除
git tag -d v0.9
#然后，从远程删除。删除命令也是push
git push origin :refs/tags/v0.9

#如何参与一个开源项目呢？比如人气极高的bootstrap项目，这是一个非常强大的CSS框架，你可以访问它的项目
#主页https://github.com/twbs/bootstrap，点“Fork”就在自己的账号下克隆了一个bootstrap仓库，然后，从自
#己的账号下clone,如果你希望bootstrap的官方库能接受你的修改，你就可以在GitHub上发起一个pull request。
#当然，对方是否接受你的pull request就不一定了
git clone git@github.com:jufeng/bootstrap.git 

#让Git显示颜色，会让命令输出看起来更醒目
git config --global color.ui true

#忽略文件 在Git工作区的根目录下创建一个特殊的.gitignore文件，然后把要忽略的文件名填进去，Git就会自动
#忽略这些文件,不需要从头写.gitignore文件，GitHub已经为我们准备了各种配置文件，只需要组合一下就可以使
#用了。所有配置文件可以直接在线浏览：https://github.com/github/gitignore有些时候，你想添加一个文件到
#Git，但发现添加不了，原因是这个文件被.gitignore忽略了,或者你发现，可能是.gitignore写得有问题，需要
#找出来到底哪个规则写错了，可以用git check-ignore命令检查


#配置别名 --global参数是全局参数，也就是这些命令在这台电脑的所有Git仓库下都有用。
git config --global alias.st status
#当然还有别的命令可以简写，很多人都用co表示checkout，ci表示commit，br表示branch
$ git config --global alias.co checkout
$ git config --global alias.ci commit
$ git config --global alias.br branch
#甚至还有人丧心病狂地把lg配置成了：
git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"

#配置文件
#配置Git的时候，加上--global是针对当前用户起作用的，如果不加，那只针对当前的仓库起作用。配置文件放哪了？
#每个仓库的Git配置文件都放在.git/config文件中而当前用户的Git配置文件放在用户主目录下的一个隐藏文件.gitconfig中
#以上的git命令多用就熟了
