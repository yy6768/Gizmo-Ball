# **Gizmo** Ball

OOAD 2020 G05

## 项目说明

ECNU Lab3 弹球游戏



## 技术栈

前端技术栈：vue.js



后端技术栈：spring



## 提交记录

### 提交1

#### Issue

#### Work

创建了web端js渲染基类

#### Description

实现了web端的渲染基类BallObject
实现了构造函数更新函数，并且初始化了渲染函数

#### Test

未测试





### 提交2

#### Issue

#### Work

1. 完成了前端的布局
2. 引入了Element-ui和Boostrap
3. 初步编写了GameMap.js
4. 后端新增了两个文件模块用于处理文件和websocket
5. 初始化了store.js

#### Description

主要更改了
前端的
ContentField.vue
ToolBar.vue
SwitchModeBar.vue
GameMap.vue
GameMap.js
BallObject.js

新增了后端的
consumer文件夹
file文件夹

#### Test

通过了测试



### 提交3

#### Issue

#### Work

1、完成了前端map的绘制
2、初始化了后端的websocket
3、初始化了后端文件FileController

#### Description

##### 主要更改了

前端的
GameMap.vue
GameMap.js

##### 新增了后端的

consumer文件夹下的WebSocketServer.java
controller文件夹下的SaveGameController.java

#### Test



### 提交4

#### Issue

#### Work

1. 完成了前端map的点击事件监听器的绑定，并且完成的格子定位函数
2. 完成了前端和后端的websocket的连接
3. 初始化了物理引擎和后端的接口
4. 初始化了readMe

#### Description

##### 主要更改了

- 前端的
  - GameMap.vue
  - GameMap.js
  - LayoutView.vue
  - store模块
- 后端的
  - Game.java
  - WebSocketServer.java

##### 新增了后端的

physicsInterface模块

#### Test

通过了WebSocket测试