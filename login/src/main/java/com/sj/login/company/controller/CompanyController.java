package com.sj.login.company.controller;

import com.sj.common.web.AjaxResult;
import com.sj.login.company.domain.Company;
import com.sj.login.company.server.ICompanyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 公司管理
 */
@Controller
@RequestMapping("/c")
public class CompanyController {
    @Autowired
    private ICompanyServer companyServer;

    @GetMapping
    public String page(HttpServletRequest request)
    {
        request.setAttribute("token",request.getParameter("token"));
        return "company";
    }

    /**
     * 分页查询公司信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResult list(int page,int rows){
        try {
            return companyServer.list(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public AjaxResult edit(Company company){
        try {
            companyServer.edit(company);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 删除公司信息
     * @param id 编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/del")
    public AjaxResult del(int id){
        try {
            companyServer.del(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
