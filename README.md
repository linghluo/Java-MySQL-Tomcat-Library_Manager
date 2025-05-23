# Java-MySQL-Tomcat 图书管理系统

## 项目概述

这是一个简单的图书管理系统，基于 **Java** 编写，使用 **MySQL** 作为数据库，部署在 **Tomcat** 服务器上。该系统实现了基本的图书管理功能，包括图书的增、删、改、查等操作。系统还提供了用户管理功能，允许用户借阅和归还图书。

该项目最初作为 2024 年的 Java 作业完成，后续在 2025 年对数据库部分进行了修改和优化，目前项目尚未完成所有功能，预计不会再进行更新。

## 技术栈

- **前端**: HTML, CSS, JavaScript
- **后端**: Java 
- **数据库**: MySQL
- **Web 服务器**: Tomcat
- **外部 JAR 包**:
  - `druid-1.2.24.jar` (数据库连接池)
  - `gson-2.13.1.jar` (JSON 序列化与反序列化)
  - `jackson-annotations-2.18.2.jar` (Jackson 注解库)
  - `jackson-core-2.18.2.jar` (Jackson 核心库)
  - `jackson-databind-2.18.2.jar` (Jackson 数据绑定库)
  - `lombok.jar` (简化 Java 代码)
  - `mysql-connector-j-8.4.0.jar` (MySQL 数据库连接)
  
## 项目功能

### 1. 用户管理
- 用户可以通过登录系统查看自己的借书记录。
- 支持用户注册、登录、注销等操作。

### 2. 图书管理
- 进行图书的增、查操作。

### 3. 借书与归还
- 用户可以借阅图书，借阅记录会保存到数据库中。
- 用户可以归还图书，系统会更新借阅状态。

### 4. 数据统计
- 提供基本的数据统计功能，比如查看借阅图书，借书记录等。

## 安装与配置

### 1. 环境-非强制要求
- **IDEA 版本：2024.2**
- **Tomcat 版本：10.1.34**
- **java 版本：21**

### 2. 数据库设置
## 数据库设置

### 1. 创建数据库`webtest`
```sql
CREATE DATABASE IF NOT EXISTS webtest;
```
2. 使用数据库
切换到 webtest 数据库：
```
USE webtest;
```
3. 创建表格
regist 表
用于存储用户注册信息：
```
CREATE TABLE IF NOT EXISTS regist (
    registN VARCHAR(10) PRIMARY KEY,  -- registN为主键
    registP VARCHAR(20) NOT NULL,  -- registP不能为空
    nameown VARCHAR(15)  -- nameown可以为空
);
```
book 表
用于存储图书信息，并使用 is_borrowed 来标记图书是否被借出：
```
CREATE TABLE IF NOT EXISTS book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    is_borrowed BOOLEAN DEFAULT FALSE  -- 标记图书是否被借出
);
```
borrow_record 表
用于记录用户的借书情况，包含借书日期和是否归还：
```
CREATE TABLE IF NOT EXISTS borrow_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    USER VARCHAR(10),       -- 对应 regist.registN
    book_id INT,
    borrow_date DATE,       -- 记录借阅日期
    returned BOOLEAN DEFAULT FALSE,  -- 是否归还
    FOREIGN KEY (USER) REFERENCES regist(registN),  -- 外键关联 regist
    FOREIGN KEY (book_id) REFERENCES book(id)  -- 外键关联 book
);
```
4. 连接数据库
确保数据库设置完成后，连接到数据库并修改db.properties配置信息，运行项目。你可以使用 MySQL 客户端或其他数据库工具进行连接和管理。
```
用户名：root
密码：your_password
```
### 3. 配置 Tomcat 服务器

1. 直接在 IDEA 里配置你的 Tomcat 或者 你也可以直接按传统方式配置。

2. 启动 Tomcat 服务器，确保数据库连接配置正确。

### 4. 配置数据库连接

在项目的 `db.properties` 配置文件中，配置 MySQL 数据库连接。以下是配置参考：

```
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/webtest
username=root
password=你的密码
initialSize=10
maxActive=30
maxIdle=10
maxWait=60000
validationQuery=SELECT 1
testWhileIdle=true
timeBetweenEvictionRunsMillis=30000
minEvictableIdleTimeMillis=60000
```

5. 运行项目
在浏览器中访问 http://localhost:8080/前端页面名.html

登录或注册用户，开始使用图书管理系统。

6.已知问题
部分功能尚未完善，例如限制图书功能和没有限定用户的权限。

优化的空间比较大。

<h3>贡献</h3>
该项目目前应该是不再进行更新和维护了。
