# 论如何使用git进行团队合作

## 一、获取远程代码

```
# 打开你的工作文件夹
git clone https://github.com/hehao98/ShootGame.git
git fetch origin
git branch your_branch (给你的分支起个独特的名字)
```

## 二、做你的修改

## 三、提交代码

```
git add .
git commit -m "在这里写你干了什么"
git push origin your_branch
```

## 四、下次编写前更新代码

```
git fetch origin
# 回到二
```



