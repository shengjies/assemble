package com.sj.login.mapper;

import com.sj.login.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 用户登录信息
     * @param user 用户信息
     * @return
     */
    User login(User user);

    /**
     * 根据用户名称查询对应的用户信息
     * @param username 用户名称
     * @return
     */
    User findByUserName(@Param("username") String username);
}
