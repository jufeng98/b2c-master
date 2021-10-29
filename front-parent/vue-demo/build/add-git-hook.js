const chalk = require('chalk')
const fs = require('fs');
const path = require("path");

copyGitHook();

function copyGitHook() {
  console.log(chalk.green("复制git hooks到.git目录"));
  // 测试分支名称
  let argv = process.argv;
  const gitTestBranchName = argv[2];

  fs.readFile('build/pre-push', 'utf8', (err, prePushContent) => {
    prePushContent = prePushContent.replace("$gitTestBranchName", gitTestBranchName);
    let dotGitPath = findDotGitPath("build");
    if (dotGitPath === null) {
      console.log(chalk.red("未找到.git目录"));
    } else {
      let hookPath = dotGitPath + "/hooks";
      fs.writeFileSync(hookPath + "/pre-push", prePushContent);
      console.log(chalk.green("完成复制 pre-push 客户端钩子到" + hookPath + "目录"));
    }
  });
}

function findDotGitPath(currentDirPath) {
  let paths = fs.readdirSync(currentDirPath)
    .filter(name => {
      if (".git" === name) {
        return true;
      }
    });
  if (paths.length !== 0) {
    // 已找到.git目录
    return path.join(currentDirPath, paths[0]);
  }
  const parentPath = path.resolve(currentDirPath, '..');
  if (currentDirPath === parentPath) {
    return null;
  }
  return findDotGitPath(parentPath);
}
