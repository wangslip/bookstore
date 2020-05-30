package cn.wangslip.service.impl;

import cn.wangslip.bean.JdUser;
import cn.wangslip.mapper.JdUserDAO;
import cn.wangslip.service.JdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class JdUserServiceImpl implements JdUserService {
    @Autowired
    private JdUserDAO jdUserDAO;

    @Override
    /**
     * @param login_name :
     * @param password :
     * @return : cn.wangslip.bean.JdUser
     * @Method : findJdUser
     * @Description :
     * @author : wangslip
     * @CreateDate : 2020-05-30 02:09:58
     */
    public JdUser login(String login_name, String password) {
        return jdUserDAO.findJdUser(login_name, password);
    }
}


