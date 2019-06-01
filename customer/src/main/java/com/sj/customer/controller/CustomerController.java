package com.sj.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @RequestMapping("/main")
    public String main(HttpServletRequest request){
        request.setAttribute("token",request.getParameter("token"));
        return "main";
    }
}
