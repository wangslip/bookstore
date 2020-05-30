package cn.wangslip.controller;


import cn.wangslip.bean.JdUser;
import cn.wangslip.service.JdUserService;
import cn.wangslip.util.CheckCodeUtil;
import cn.wangslip.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;

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

    @RequestMapping("toRegister")
    public String toRegister() {
        return "register";
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
    @RequestMapping("login.do")
    public String login(String login_name, String password, HttpServletRequest request) {
        String md5password = Md5Util.getmd5password(login_name, password);
        JdUser jdUser = userService.login(login_name, md5password);
        if (jdUser != null) {
            request.getSession().setAttribute("jdUser", jdUser);
            return "book";
        }
        request.setAttribute("msg", "登录失败");
        return "login";
    }

    /**
     * @param user    :
     * @param request :
     * @return : java.lang.String
     * @Method : regist
     * @Description : 用户注册,(验证码验证,密码加密)
     * @author : wangslip
     * @CreateDate : 2020-05-30 14:26:45
     */
    @RequestMapping("register.do")
    public String regist(JdUser user, HttpServletRequest request) {
        //验证码确认
        String checkCode = user.getCheckcode();
        String imgcode = (String) request.getSession().getAttribute("checkcode");
        if (!checkCode.equals(imgcode)) {
            request.setAttribute("msg", "验证码不正确!");
            return "register";
        }

        String md5password = Md5Util.getmd5password(user.getLogin_name(), user.getPassword());
        user.setPassword(md5password);
        boolean result = userService.register(user);
        if (result) {
            return "registerOk";
        }
        request.setAttribute("msg", "用户名已存在, 注册失败");
        return "register";
    }

    @RequestMapping("checkcode.do")
    /**
     * @param req :
     * @param resp :
     * @Method : getCheckCode
     * @Description : 验证码验证
     * @author : wangslip
     * @CreateDate : 2020-05-30 17:40:31
     */
    public void getCheckCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //n-字母;l数字;nl数字+字母组合
        Map<String, Object> map = new CheckCodeUtil().check("n");
        String randomString = (String) map.get("code");
        BufferedImage bi = (BufferedImage) map.get("image");
        req.getSession().setAttribute("checkcode", randomString);
        //设置响应头通知浏览器以图片的形式打开
        resp.setContentType("image/jpeg");
        //设置响应头控制浏览器不要缓存
        resp.setDateHeader("expries", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        //将图片传给浏览器
        ImageIO.write(bi, "jpg", resp.getOutputStream());
    }
}
