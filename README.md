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

### Compiles and hot-reloads for development

```
npm run serve
```

### Compiles and minifies for production

```
npm run build
```



1. 项目登录界面：

   ![](C:\Users\BaiDing\Desktop\333.png)



2. 项目主页：

   ![](C:\Users\BaiDing\Desktop\444.png)

	主页没写内容，哈哈



3. 用户管理界面：

   ![](C:\Users\BaiDing\Desktop\555.png)

	用户管理界面基本的增删查改都有，分页功能也包含了。

	导出功能为将后台传回的流文件导出为Excel表格

	[](C:\Users\BaiDing\Desktop\666.png)			

	用户导入功能为将Excel表格数据导入数据库中



4. 专辑管理界面

   ![](C:\Users\BaiDing\Desktop\777.png)

   ![](C:\Users\BaiDing\Desktop\888.png)

   ![](C:\Users\BaiDing\Desktop\999.png)

   ![1010](C:\Users\BaiDing\Desktop\1010.png)

   专辑管理界面包含基本的增删查改，以及下拉树框，百度富文本框以及文件的上传

5. 单集管理界面

   ![](C:\Users\BaiDing\Desktop\1111.png)

   ![1212](C:\Users\BaiDing\Desktop\1212.png)

   单集管理界面基本的增删查改，图片以及音频文件的上传，以及表格数据的多选操作

6. 专辑分类

   ![](C:\Users\BaiDing\Desktop\1313.png)

   专辑分类界面是一个树形结构界面，自定义iView组件的内容实现，基本的增删查改功能也包含了，Tree组件的异步加载也实现了

