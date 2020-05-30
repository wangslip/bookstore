package cn.wangslip.test;

import cn.wangslip.bean.JdUser;
import cn.wangslip.service.JdUserService;
import cn.wangslip.service.impl.JdUserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : wangslip
 * @Project : bookstore
 * @Package : cn.wangslip.test
 * @Class : JdUserTest
 * @Description : 用户测试类
 * @CreateDate : 2020-05-30 02:21:00
 */
public class JdUserTest {
    public static void main(String[] args) {
        //构建Spring容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        JdUserService userService = applicationContext.getBean("userService", JdUserServiceImpl.class);

        JdUser user = userService.login("wangslip", "123");
        System.out.println(user);
        JdUser user1 = new JdUser();
        user1.setLogin_name("lucky");
        user1.setPassword("1232343");
        user1.setEmail("wangslip@sina.cn");
        user1.setRecommender("wangslip");
        System.out.println(userService.register(user1));
    }
}
