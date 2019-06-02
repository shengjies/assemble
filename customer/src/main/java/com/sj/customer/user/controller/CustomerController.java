package com.sj.customer.user.controller;

import com.sj.common.web.AjaxResult;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/main")
    public String main(HttpServletRequest request){
        request.setAttribute("token",request.getParameter("token"));
        return "main";
    }

    /**
     * 用户登录
     * @param user 用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult login(@RequestBody User user){
        return userService.login(user);
    }
}
