# EBook 网上书店

## 技术栈

### 后端
1. SpringBoot
2. SpringActuator
3. SpringSecurity
4. Swagger
5. Mail
6. Aliyun-java-sdk-core
7. MyBatis
8. Jackson

### 前端
1. Vue
2. ElementUI
3. BootStrap
4. Axios
5. vuex
6. vue-cli
7. V-Chart

### 项目管理工具

1. Maven
2. Git
3. Sunny-ngrok

## 数据库

- SQLyog: MySQL
- Navicat: MongoDB

## 功能

### 用户

- 游客预览
- 登录 & 退出
  - 拦截未登录用户提交订单
- 注册用户
  - 发送短信验证码
  - 验证短信合法性

- 忘记密码
  - 发送邮件找回密码

- 书籍列表预览
  - 查询所有书籍
  - 模糊查询指定书名/作者名的书籍

- 购物车功能
  - 加入购物车：库存数量检测
  - 清空购物车
  - 提交订单

- 个人订单
  - 提交时间
  - 订单列表

- 订单可视化统计

### 管理员

- 书籍管理
  - 修改信息
  - 删除书籍
  - 添加书籍

- 用户权限管理
  - 放行用户
  - 冻结用户

- 订单管理
  - 查询所有订单
  - 搜索订单

- 订单统计

## 快速开始

1. 下载项目：`git clone git@github.com:Wu-yikun/EBook.git`
2. **client**：
   - 下载项目依赖包：`npm install`
   - 启动前端项目：`npm run serve`
3. **server**：
   - 运行 **sql** 文件创建数据库
   - 启动后端项目

## 预览图

### 发送邮件找回密码

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxl9x1w5ylj31hc0u00yf.jpg)

### 注册

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxl9y2om7lj31hc0u0ag5.jpg)

### 首页

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaechvoqj31hc0u07wh.jpg)

### 用户页面

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlafk2ceej31hc0u0dpa.jpg)

### 个人订单查询

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlagf6507j31hc0u0jzl.jpg)

### 书籍搜索

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlahim205j31hc0u044i.jpg)

### 统计页面

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaia3rfmj31hc0u07ci.jpg)

### 管理员页面

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaex29hpj31hc0u0wna.jpg)

### 用户管理

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaiqaw4wj31hc0u00xs.jpg)

### 订单管理

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaja4tyyj31hc0u0wms.jpg)

## 项目部署（内网穿透）

### 开启隧道

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlaqbgdgwj30uc0hrte3.jpg)

### 域名访问

![](http://tva1.sinaimg.cn/large/006V2BYXly1gxlapn5116j31hc0u07wh.jpg)
