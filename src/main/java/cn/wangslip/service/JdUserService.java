package cn.wangslip.service;

import cn.wangslip.bean.JdUser;
import org.apache.ibatis.annotations.Param;

public interface JdUserService {
    /**
     * @param login_name :登录名字
     * @param password   :密码
     * @return : cn.wangslip.bean.JdUser
     * @Method : findJdUser
     * @Description : 用户登录
     * @author : wangslip
     * @CreateDate : 2020-05-30 11:57:41
     */
    JdUser login( String login_name, String password);
    /**
     * @param user : 用户对象
     * @return : boolean
     * @Method : register
     * @Description : 用户注册
     * @author : wangslip
     * @CreateDate : 2020-05-30 12:03:58
     */
    boolean register(JdUser user);
}
