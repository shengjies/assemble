package com.sj.customer.user.service;

import com.sj.common.jwt.JwtUtil;
import com.sj.common.web.AjaxResult;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 分页查询用户信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    public AjaxResult list(int page, int rows,String token) {
        User user = JwtUtil.getUserByToken(token);
        List<User> users = userMapper.list((page -1)*rows,rows,user.getCompany_id());
        return AjaxResult.tableData(userMapper.count(user.getCompany_id()),users);
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    public int edit(User user) throws Exception {
        return userMapper.edit(user);
    }

    /**
     * 删除用户信息
     * @param id 用户编号
     * @return
     * @throws Exception
     */
    public int del(int id) throws Exception {
        return userMapper.del(id);
    }

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public int add(User user) throws Exception {
        return userMapper.add(user);
    }
}
