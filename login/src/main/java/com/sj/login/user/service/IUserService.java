package com.sj.login.user.service;

import com.sj.common.web.AjaxResult;
import com.sj.login.user.domain.User;

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
     * 用户注册
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    AjaxResult register(User user) throws Exception;

    /**
     * 根据用户名称查询对应的用户信息
     * @param username 用户名称
     * @return
     */
    User findByUserName(String username);

    /**
     * 分页查询用户信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    AjaxResult list(int page,int rows);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    int edit(User user) throws Exception;

    /**
     * 删除用户信息
     * @param id 用户编号
     * @return
     * @throws Exception
     */
    int del(int id) throws Exception;
}
