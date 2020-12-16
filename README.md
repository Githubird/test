# 简介
本项目是一个基于Spring Boot & MyBatis的种子项目,

用于快速构建中小型API、RESTFul API项目，

该种子项目已经有过多个真实项目的实践，

稳定使用、简单入门、快速开发

有利于专注于业务代码的编写,摆脱那些重复劳动。

## 特征&提供
* 最佳实践的项目结构、配置文件
* 统一响应结果封装及生成工具
* 统一异常处理
* `代码生成器`可以一键生成符合MyBatisPlus需求的代码
## 依赖框架
* `SpringBoot`
* `Druid` - 数据库连接池与监控
* `MyBatis Plus` - 数据库操作工具,MaBatis的无损增强包
* `Knife4j` - 文档工具 Swagger 增强版
* `FastJson` - 提高JSON序列化速度
## 快速开始
> Tips: 双击`shift`,输入文件名,可以快速跳转
>
### 1. 配置项自定义
> * 如果您想修改本项目地址,请使用Idea的重命名功能,可以进行快速重构
> * 如您就想简单的使用本框架,本部分说明中修改`项目地址`的地方,您就可以无视啦
1. 打开 `application-dev.yml` 进行针对性修改
    1. 第 5 行`type-aliases-package`改为你的实际项目地址
    2. 第 33 行`url`改为你的实际数据库连接
    3. 第 34-35 行,`username`,`password`改为你的实际用户名和密码
> 根据实际开发需求,可以定制化开发环境和生产环境需要的配置项
> 在`application.yml`中以关键词`DEBUG`标记
2. 打开 `项目地址/config/database/DruidDataSourceConfig.java`
    * 第 28 行`@ServletComponentScan("请修改为您的项目配置文件夹路径")`
    * eg: `me.sunshinenny.base.config`
3. 打开 `项目地址/BaseApplication.java`
    * 第 8 行`@MapperScan("请修改为您的项目Mapper文件夹路径")`
    * eg: `me.sunshinenny.base.mapper`
### 2. 代码生成器自定义
* 打开 `项目地址/util/mybatisPlus/CodeGenerator.java`
    * 修改 18-33 行,详见备注
    
### 3. 运行代码生成器
1. 点击左侧按钮运行
2. 输入数据库表名,多个表请用英文逗号隔开

### 4. 根据实际需求修改业务
具体来说,请编写接口`项目地址/controller/xxxController`



## 最佳实践
* 数据库表名，建议使用小写，多个单词使用下划线拼接
* Java文件夹名字使用驼峰,类名使用首字母大写的驼峰
* 单表操作99%可以使用MyBatis Plus完成,多表操作请优先考虑使用XML注解方式实现
* 建议业务失败直接使用`CustomException(错误类型,错误提示);`抛出，由统一异常处理器来封装业务失败的响应结果,无需自己处理，尽情抛出
> 错误类型,定义详见core/result/CustomExceptionType
>

* 开发规范建议遵循阿里巴巴Java开发手册
* 建议在使用Swagger管理API文档  



