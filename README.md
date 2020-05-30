# bookstore #
#设置ssm-模板
## 登录功能如何实现 ##
### 1.编写Model层 ###
- 1.1 建立一张表 jd_user
- 1.2 建立maven对应的web工程 根据表建立对应的实体类
- (ssm 的jar包 mybatis 1.mybatis-spring 2.mysql驱动 3.dbcp连接池 4.ioc 5.aop 6.dao 7.tx 8.web 9.webmvc 10.json 11.jsp 12.servlet (jstl,json等))
- 1.3 根据Mybatis中的SQL定义文件 
根据账号和密码 查询
- 1.4 根据Mapper映射器规则,编写DAO(Mapper)接口
- 1.5 在spring的配置文件中 配置SqlSessionFactoryBean
(依赖于sql定义文件 和dataSource)
- 1.6 在Spring的配置文件中 配置MapperScannerConfigurer
为DAO接口产生实现类
- 1.7 封装一个Service类 使用DAO 完成登录
- 1.8 测试 Service
### 2.搭建基于标注的Spring MVC ###
- 2.1 在web.xml 中配置 DispatcherServlet
- 2.2 在Spring 配置文件中
开启组件扫描 和 标注形式的MVC(由于配置的是/,所以还需放行静态资源)
- 2.3 编写一个java类,实现登录功能的控制
@Controller @RequestMapping("/请求")
- 2.4 配置ViewResolver 对应的实现类 InternalResourceViewResolver,
配置 prefix 和 suffix	
- 2.5 测试请求 看是否能跳转到对应的页面
### 3. 真正编写 登录的控制方法 ###
接收页面请求的参数 	登录名	 和 		密码
调用Service 		根据返回值	判断是否登录成功


 