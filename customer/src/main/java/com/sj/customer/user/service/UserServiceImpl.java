package com.sj.customer.user.service;

import com.alibaba.fastjson.JSON;
import com.sj.common.api.user.UserFeignApi;
import com.sj.common.jwt.JwtUtil;
import com.sj.common.utils.CodeUtils;
import com.sj.common.utils.PathUtils;
import com.sj.common.web.AjaxResult;
import com.sj.customer.company.demain.Company;
import com.sj.customer.company.mapper.CompanyMapper;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.mapper.UserMapper;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return
     */
    public AjaxResult login(User user) {
        if(user == null)
            return AjaxResult.error(CodeUtils.CODE_SYS_PWD, CodeUtils.CODE_SYS_PWD_MSG);
        //查询对应用户是否存在
        User u = userMapper.login(user);
        if (u == null)
            return AjaxResult.error(CodeUtils.CODE_USER_ERROR, CodeUtils.CODE_USER_ERROR_MSG);
        //查询对应公司
        Company company = companyMapper.selectById(u.getCompany_id());
        if (company == null)
            return AjaxResult.error(CodeUtils.CODE_COMPANY_ERROR, CodeUtils.CODE_COMPANY_ERROR_MSG);
        //验证对应服务器密码
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", company.getSid());
            map.put("spwd", company.getSpwd());
            UserFeignApi feignApi = Feign.builder()
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .target(UserFeignApi.class, PathUtils.APTH_DOMAIN);
            AjaxResult result = feignApi.verificationSer(map);
            if (Double.parseDouble(result.get("code").toString()) != 1) {
                return AjaxResult.error(CodeUtils.CODE_SYS_PWD, CodeUtils.CODE_SYS_PWD_MSG);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(CodeUtils.CODE_SYS_ERROR, CodeUtils.CODE_SYS_ERROR_MSG);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(JwtUtil.CLAIM_KEY_USER, JSON.toJSONString(u));
        //进行登录
        String token = JwtUtil.getToken(map);
        return AjaxResult.success(CodeUtils.CODE_USER_LOGIN_MSG, token);
    }


    /**
     * 根据用户名称查询对应的用户信息
     *
     * @param username 用户名称
     * @return
     */
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    /**
     * 分页查询用户信息
     *
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    public AjaxResult list(int page, int rows, String token) {
        User user = JwtUtil.getUserByToken(token);
        List<User> users = userMapper.list((page - 1) * rows, rows, user.getCompany_id());
        return AjaxResult.tableData(userMapper.count(user.getCompany_id()), users);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    public int edit(User user) throws Exception {
        return userMapper.edit(user);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户编号
     * @return
     * @throws Exception
     */
    public int del(int id) throws Exception {
        return userMapper.del(id);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    public int add(User user) throws Exception {
        return userMapper.add(user);
    }
}
