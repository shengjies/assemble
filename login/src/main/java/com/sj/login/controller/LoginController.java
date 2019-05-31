package com.sj.login.controller;

import com.alibaba.fastjson.JSON;
import com.sj.common.jwt.JwtUtil;
import com.sj.login.domain.User;
import com.sj.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response){
        User u = userService.login(user);
        if(u == null){
            request.setAttribute("msg","用户名密码错误");
            return "login";
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(JwtUtil.CLAIM_KEY_USER, JSON.toJSONString(u));
        String token = JwtUtil.getToken(map);
        try {
            response.sendRedirect("/main?token="+token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @ResponseBody
    @RequestMapping("/403")
    public String to403(){
        return "403";
    }
}
