package com.sj.customer.service;

import com.sj.common.web.AjaxResult;
import com.sj.customer.domain.User;
import com.sj.customer.mapper.UserMapper;
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
    public AjaxResult list(int page, int rows) {
        List<User> users = userMapper.list((page -1)*rows,rows);
        return AjaxResult.tableData(userMapper.count(),users);
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
}
