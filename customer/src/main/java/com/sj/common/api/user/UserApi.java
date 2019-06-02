package com.sj.common.api.user;

import com.alibaba.fastjson.JSON;
import com.sj.common.jwt.JwtUtil;
import com.sj.common.web.AjaxResult;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息接口
 */
@RestController
@RequestMapping("/api/u")
public class UserApi {
    @Autowired
    private IUserService userService;
    /**
     * 初始化用户信息
     * @param user 用户信息
     * @return
     */
    @RequestMapping("/init")
    public AjaxResult init(@RequestBody User user){
        try {
            userService.add(user);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put(JwtUtil.CLAIM_KEY_USER, JSON.toJSONString(user));
            return AjaxResult.register(user.getSpath(), JwtUtil.getToken(map));
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    public AjaxResult login(@RequestBody User user){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
