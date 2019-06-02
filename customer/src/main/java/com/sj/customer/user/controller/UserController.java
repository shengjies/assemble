package com.sj.customer.user.controller;

import com.sj.common.web.AjaxResult;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/u")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public String page(HttpServletRequest request)
    {
        request.setAttribute("token",request.getParameter("token"));
        return "user";
    }

    /**
     * 分页查询用户信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResult list(HttpServletRequest request,int page,int rows){
       return  userService.list(page,rows,request.getParameter("token"));
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public AjaxResult edit(User user){
        try {
            userService.edit(user);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/del")
    public AjaxResult del(int id){
        try {
            userService.del(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
