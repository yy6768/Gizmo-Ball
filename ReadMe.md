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





### 提交5

#### Issue

toolbar和switchModeBar的实现

#### Work

1. 实现了toolBar
2. 实现了switchModeBar

#### Description

更新：

1. public/icon文件夹贴图素材
2. toolBar.vue：选择素材组件按钮，组件操作按钮：旋转、删除、放大、缩小
3. switchmodeBar.vue：选择器和按钮

#### Test

通过了界面测试





### 提交6

#### Issue

实现了小球和圆形障碍物的绘制

#### Work

1. 实现了Ball类，添加了渲染和向后端发送消息功能，判断了是否为单例
2. 实现了Circle类，添加了渲染和向后端发送消息的功能

#### Description

更新：

1. Ball.js类完善了构造函数，添加了render函数
2. 新增了Circle.js函数
3. 更改了GameMap.js函数，完善了add()函数

#### Test

通过了使用测试





## 提交7

#### Issue

接口问题突出

现在指定好websocket和物理系统中的接口

GizmoManager(原来所说的world需要实现以下接口)

- add(Object e) 创建一个新的组件并且添加进去
- delete（Integer id)根据id删除
- List\<Object> getAll() 获取全部的组件
- Object get(int id) 获得组件



GizmoObject (对象类)

- 构造函数（Integer Id, Double x, Double y)

- setPosition(Double x, Double y)
- setType(String type)//这个不确定
- rotate() //旋转
- magnify()//放大
- shrink()//缩小

- toString()

  返回的字符串每个属性使用#分割

  - 对于小球而言

    Ball#{id}#{x}#{y}#{size}#{vx}#{vy}

    x是小球的圆心横坐标

    y是小球的圆心纵坐标  

    vx,vy就是二维向量的速度

    

  - 对于正方体而言

    Rectangle#{id}#{x}#{y}#{size}

    x是正方体的左上角横坐标

    y是正方体的左下角纵坐标	

  - 对于三角型而言

    Triangle#{id}#{x}#{y}#{size}#{rotate}

    x是三角形的左上角横坐标

    y是三角形的左下角纵坐标	

    rotate = 0 表示 直角点位于左上，1表示右上，2表示右下，3表示左下

  - 对于球体而言

    Circle#{id}#{x}#{y}#{size}

    x是球的圆心横坐标

    y是球的圆心纵坐标  

    

    size只有两种大和小

    位置之后会改成double

    接口已经设计为double



#### Work

1. 前端Map发送信息大改，现在包含了更多信息，例如组件id，位置等
2. 前端基类添加id
3. 前端Map.js add函数完善框架
4. 后端WebSocket完善
5. physicsInterface大改，描述如上

#### Description

1. 主要更改了Ball.js、GameMap.js
2. 后端WebSocket大改onMessage
3. 后端物理接口重构，详细查看physicsInterface

#### Test

通过了id的测试



### 提交8

#### Issue

physics组件的实现

#### Work

1. 实现了整个physics

#### Description

更新：

1. GizmoBall.java
2. GizmoRectangle
3. GizmoTriangle
4. WorldObject

#### Test

在Swing组件中完成了测试

