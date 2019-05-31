package com.sj.login.service;

import com.sj.login.domain.User;

/**
 * 用户操作信息
 */
public interface IUserService {
    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    User login(User user);

    /**
     * 根据用户名称查询对应的用户信息
     * @param username 用户名称
     * @return
     */
    User findByUserName(String username);
}
