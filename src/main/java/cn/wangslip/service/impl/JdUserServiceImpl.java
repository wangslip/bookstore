package cn.wangslip.service.impl;

import cn.wangslip.bean.JdUser;
import cn.wangslip.mapper.JdUserDAO;
import cn.wangslip.service.JdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class JdUserServiceImpl implements JdUserService {
    @Autowired
    private JdUserDAO userDAO;

    @Override
    /**
     * @param login_name :登录名字
     * @param password   :密码
     * @return : cn.wangslip.bean.JdUser
     * @Method : findJdUser
     * @Description : 用户登录
     * @author : wangslip
     * @CreateDate : 2020-05-30 11:57:41
     */
    public JdUser login(String login_name, String password) {
        return userDAO.findJdUser(login_name, password);
    }

    /**
     * @param user : 用户对象
     * @return : boolean 登录成功返回true,登录失败返回false
     * @Method : register
     * @Description : 用户注册
     * @author : wangslip
     * @CreateDate : 2020-05-30 12:03:58
     */
    @Override
    public boolean register(JdUser user) {
        try {
            return userDAO.insertJdUser(user) == 1 ? true : false;
        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
        return false;
    }


}


