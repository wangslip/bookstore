package cn.wangslip.mapper;

import cn.wangslip.bean.JdUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface JdUserDAO {
    JdUser findJdUser(@Param("login_name") String login_name,
                      @Param("password") String password);
}
