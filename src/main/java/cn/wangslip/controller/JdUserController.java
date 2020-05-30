package cn.wangslip.controller;


import cn.wangslip.bean.JdUser;
import cn.wangslip.service.JdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : wangslip
 * @Project : bookstore
 * @Package : cn.wangslip.controller
 * @Class : JdUserController
 * @Description : 用户控制器
 * @CreateDate : 2020-05-30 09:19:01
 */
@Controller
public class JdUserController {
    @Autowired
    private JdUserService userService;

    @RequestMapping("/toHello")
    public String toHello() {
        return "hello";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * @param login_name :用户名
     * @param password   : 密码
     * @param request    :
     * @return : java.lang.String
     * @Method : login
     * @Description : 用户登录,验证账号密码
     * @author : wangslip
     * @CreateDate : 2020-05-30 09:17:30
     */
    @RequestMapping("/login.do")
    public String login(String login_name, String password, HttpServletRequest request) {
        JdUser jdUser = userService.login(login_name, password);
        if (jdUser != null) {
            request.getSession().setAttribute("jdUser", jdUser);
            return "book";
        }
        request.setAttribute("msg", "登录失败");
        return "login";
    }
}
