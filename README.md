### vue-manage

vue-manage包含了两个项目，一个为后台项目：vue-serevr-backend,提供基本的数据服务，另一个为前台项目：vue-backend-mange，提供可视化界面服务



#### 后台项目： vue-server-backend

后台项目的启动流程为：

1. 执行数据库文件

   ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/111.png)
   
   在项目的资源文件夹static目录下是后台的数据库文件，在自己的数据库中新建一个数据库，名为 `vue-manage`,之后运行SQL文件就可以了

2. 修改配置文件中数据库的配置

   ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/222.png)

   将数据库连接配置改为自己的就可以了

上述两步执行之后，就可以启动整个后台工程了。



#### 前台项目：vue-backend-mange



**由于前台项目是根据vue-cli 3.0构建的，所以编译启动之前要将vue-cli升级到3.0**

之后的步骤无外乎这几步了：

## Project setup

```
npm install
```

### 本地开发运行命令
```
npm run serve
```

### 多环境下 服务器测试环境运行命令
```
npm run dev-build
```

### 多环境下 服务器预生产环境运行命令
```
npm run uat-build
```

### 多环境下 服务器生产环境运行命令
```
npm run pro-build
```



1. 项目登录界面：

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/333.png)



2. 项目主页：

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/444.png)

	主页没写内容，哈哈



3. 用户管理界面：

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/555.png)

	用户管理界面基本的增删查改都有，分页功能也包含了。

	导出功能为将后台传回的流文件导出为Excel表格

	 ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/666.png)			

	用户导入功能为将Excel表格数据导入数据库中



4. 专辑管理界面

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/777.png)

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/888.png)

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/999.png)

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/1010.png)

   专辑管理界面包含基本的增删查改，以及下拉树框，百度富文本框以及文件的上传

5. 单集管理界面

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/1111.png)

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/1212.png)

   单集管理界面基本的增删查改，图片以及音频文件的上传，以及表格数据的多选操作

6. 专辑分类

    ![](https://github.com/LiuJiaBaiDing/vue-manage/blob/master/image/1313.png)

   专辑分类界面是一个树形结构界面，自定义iView组件的内容实现，基本的增删查改功能也包含了，Tree组件的异步加载也实现了

