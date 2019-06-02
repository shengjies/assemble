package com.sj.login.user.controller;

import com.alibaba.fastjson.JSON;
import com.sj.common.jwt.JwtUtil;
import com.sj.common.web.AjaxResult;
import com.sj.login.user.domain.User;
import com.sj.login.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;
    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("/index")
    public String page(){
        return "login";
    }

    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public AjaxResult login(User user){
        User u = userService.login(user);
        if(u == null){
            return AjaxResult.error(0,"用户名密码错误");
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(JwtUtil.CLAIM_KEY_USER, JSON.toJSONString(u));
        String token = JwtUtil.getToken(map);
        return AjaxResult.success("登录成功",token);
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request){
        request.setAttribute("token",request.getParameter("token"));
        return "main";
    }

    /**
     * 用户注册
     * @param user 用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public AjaxResult register(User user)
    {
        try {
            return userService.register(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error(0,"操作异常...");

    }

    @ResponseBody
    @RequestMapping("/403")
    public String to403(){
        return "403";
    }
}
