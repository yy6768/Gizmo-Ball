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



### 提交9

#### Issue



#### Work

1. physics组件和websocket的通信（通过反射）
2. 前端多个组件实现
3. 前端store的icon模块
4. 挡板的键盘事件绑定
5. 前端组件是否重叠的判断
6. 前端组件通过鼠标快速判断组件id

#### Description

更新

1. GizmoBall.java
2. GizmoRectangle.java
3. GizmoTriangle.java
4. WebSocket.java
5. GameMap.vue
6. GameMap.js
7. Ball.js



新增

1. StraightPipe.js
2. BendPipe.js
3. Baffle.js

#### Test

1. 后端WebSocket完成测试
2. 前端Baffle完成动画测试
3. 新增的组件都完成了显示和布局的测试





### 提交10&11

提交10：dyh

提交11：yy

#### Issue

未完成旋转

#### Work

提交10

更换了素材

修改了modeBar和模式的显示

新增了rectangle和triangle

提交11

前端补全了所有组件类

实现了物体的放大缩小

实现gamemap通过点击获得对象

#### Description

更新

1. 所有前端组件都更新过
2. 前端注意看GameMap

#### Test

1. 前端完成动画测试





### 提交12

#### Issue

1、后端websocket仍然没法正确处理消息发送
2、后端物理世界需要进行深拷贝

#### Work

1、前端组件新增Component和Shape，更好的使用继承关系处理方法
2、前端的轨道和三角进行重新绘制，原因是没办发旋转图片
3、后端的physical的一些信息进行修改（主要是调整了常量的位置）
4、后端的websocket进行了调试和Game的创建

#### Description

更新

全部前端组件js文件
vuex存储文件
switchBar.vue
GameDesignBar.vue

后端Physics文件夹
game.java和Websocket.java
新增

1. GizmoComponent.js
2. GizmoShape.js

#### Test

1.完成了前端显示测试
2.完成了后端打印测试





### 提交13

#### Issue

现在问题很多，总结下来下一阶段分工如下：

文件类

- 前端界面上没有保存导出的按钮，需要完成保存和导入（dyh，GameDesign.js）
  - 按钮需要在游戏模式禁用，只有布局模式可以保存和导入
  - 保存和导入需要和后端结合
- 前端js导入文件信息初始化布局（dyh,LayoutView.vue)
- 前端js的构造函数的修改（dyh,所有有旋转类的js）
- 后端打开文件传入信息（yy,FileController,FileService,WebSocket)

物理类

- 后端组件黑洞(Blackhole)、挡板(Baffle)、轨道(StraightPipe,BendPipe)没实现（wxt）
  - ![image-20221122175344354](http://typora-yy.oss-cn-hangzhou.aliyuncs.com/img/image-20221122175344354.png)
- 后端组件的球体、三角形、正方体的放大、旋转有问题（wxt，yy）
  - 旋转后会穿模![image-20221122175525641](http://typora-yy.oss-cn-hangzhou.aliyuncs.com/img/image-20221122175525641.png)
- 三角型的初始化角度
- 后端组件的toString方法重构
  - Ball的toString方法（我已经改好了，yy)
  - Baffle的toString方法（wxt）
- worldManager清空（wxt）
- WorldManager获得单独一个球，两个挡板（左挡板，右挡板）

#### Work

- 重构了WebSocketServer.java和Game.js统一了前后端的坐标衡量
- 添加了前端Websocket获取信息的部分

#### Description

更新

GameDesignBar.vue

后端Physics文件夹
game.java和Websocket.java

WorldPlace.java

上传了

1. GizmoComponent.js
2. GizmoShape.js

#### Test

完成了测试

但是仍然有问题
