package com.sj.login.user.service;

import com.alibaba.fastjson.JSON;
import com.sj.common.jwt.JwtUtil;
import com.sj.common.utils.CodeUtils;
import com.sj.common.web.AjaxResult;
import com.sj.login.company.api.CompanyApi;
import com.sj.login.company.domain.Company;
import com.sj.login.company.mapper.CompanyMapper;
import com.sj.login.ser.domain.Ser;
import com.sj.login.ser.mapper.SerMapper;
import com.sj.login.user.api.UserApi;
import com.sj.login.user.domain.User;
import com.sj.login.user.mapper.UserMapper;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public AjaxResult login(User user) {
        User u = userMapper.login(user);
        if(u == null){
            return AjaxResult.error(0,"用户名密码错误");
        }
        if(u.getSign() == 0){
            //查询用户所属公司
            Company company = companyMapper.selectById(u.getCompany_id());
            if(company == null)
                return AjaxResult.error(CodeUtils.CODE_COMPANY_ERROR,CodeUtils.CODE_COMPANY_ERROR_MSG);
            //查询对应服务器信息
            Ser s = serMapper.selectSerById(company.getSid());
            if(s == null)
                return AjaxResult.error(CodeUtils.CODE_SER_ERROR,CodeUtils.CODE_SER_ERROR_MSG);
            //客户登录
            try {
                UserApi userApi = Feign.builder()
                        .encoder(new GsonEncoder())
                        .decoder(new GsonDecoder())
                        .target(UserApi.class,s.getSpath());
               AjaxResult result = userApi.userLogin(u);
                if (Double.parseDouble(result.get("code").toString()) != 1) {
                    return result;
                }
                System.out.println(result);
               return AjaxResult.login(s.getSpath(),result.get("data").toString(),u.getSign());
            }catch (Exception e){
                e.printStackTrace();
                return AjaxResult.error(CodeUtils.CODE_SYS_ERROR,CodeUtils.CODE_SYS_ERROR_MSG);
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(JwtUtil.CLAIM_KEY_USER, JSON.toJSONString(u));
        String token = JwtUtil.getToken(map);
        return AjaxResult.login("http://localhost/main",token,u.getSign());
    }

    /**
     * 用户注册
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    @Transactional
    public AjaxResult register(User user) throws Exception {
        //查询未注册满的服务器
        Ser ser = serMapper.selectSer();
        if(ser == null)throw new Exception("无可用服务器");
        //初始化公司信息
        String  iso = "iso"+ser.getId()+(ser.getSuser_num()+1);
        Company company = new Company();
        company.setCname("公司");
        company.setIso(iso);
        company.setSid(ser.getId());
        company.setSpwd(ser.getSpwd());
        companyMapper.add(company);
        company.setCname("公司"+company.getId());
        companyMapper.edit(company);
        try {
            CompanyApi companyApi = Feign.builder()
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .target(CompanyApi.class,ser.getSpath());
            AjaxResult result = companyApi.initCompany(company);
            if(Double.parseDouble(result.get("code").toString())==0){
                throw new Exception("公司注册失败...");
            }

        }catch (Exception e){
            //删除对应公司信息
            companyMapper.del(company.getId());
            throw new Exception(e.getMessage());
        }
        try {
            //注册用户信息
            user.setCompany_id(company.getId());
            user.setSpath(ser.getSpath());
            userMapper.add(user);
            UserApi userApi = Feign.builder()
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .target(UserApi.class,ser.getSpath());
            AjaxResult res = userApi.init(user);
            if(Double.parseDouble(res.get("code").toString())==0){
                throw new Exception("用户注册失败...");
            }
            serMapper.editNum(ser.getId());
            return res;
        }catch (Exception e){
            //删除公司信息
            companyMapper.del(company.getId());
            //删除用户信息
            userMapper.del(user.getId());
            throw new Exception(e.getMessage());
        }
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
