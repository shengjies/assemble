package com.sj.login.service;

import com.sj.login.domain.User;
import com.sj.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录细心
     * @param user 用户信息
     * @return
     */
    public User login(User user) {
        return userMapper.login(user);
    }

    /**
     * 根据用户名称查询对应的用户信息
     * @param username 用户名称
     * @return
     */
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
