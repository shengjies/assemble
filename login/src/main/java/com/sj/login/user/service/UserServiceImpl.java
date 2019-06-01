package com.sj.login.user.service;

import com.sj.common.web.AjaxResult;
import com.sj.login.company.domain.Company;
import com.sj.login.company.mapper.CompanyMapper;
import com.sj.login.ser.domain.Ser;
import com.sj.login.ser.mapper.SerMapper;
import com.sj.login.user.domain.User;
import com.sj.login.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SerMapper serMapper;

    @Autowired
    private CompanyMapper companyMapper;
    /**
     * 用户登录细心
     * @param user 用户信息
     * @return
     */
    public User login(User user) {
        return userMapper.login(user);
    }

    /**
     * 用户注册
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    @Transactional
    public User register(User user) throws Exception {
        //查询未注册满的服务器
        Ser ser = serMapper.selectSer();
        if(ser == null)throw new Exception("无可用服务器");
        //初始化公司信息
        String  iso = "iso"+ser.getId()+(ser.getSuser_num()+1);
        Company company = new Company();
        company.setCname("公司");
        company.setIso(iso);
        company.setSid(ser.getId());
        companyMapper.add(company);
        company.setCname("公司"+company.getId());
        companyMapper.edit(company);
        //注册用户信息
        user.setCompany_id(company.getId());
        user.setSpath(ser.getSpath());
        userMapper.add(user);
        serMapper.editNum(ser.getId());
        return user;
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
