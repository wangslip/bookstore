package cn.wangslip.service;

import cn.wangslip.bean.JdUser;
import org.apache.ibatis.annotations.Param;

public interface JdUserService {
    JdUser login( String login_name, String password);
}
