package com.sj.common.api.company;

import com.sj.common.web.AjaxResult;
import com.sj.customer.company.demain.Company;
import com.sj.customer.company.server.ICompanyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司操作api
 */
@RestController
@RequestMapping("/api/c")
public class CompanyApi {
    @Autowired
    private ICompanyServer companyServer;
    /**
     * 初始化公司信息
     * @param company
     * @return
     */
    @RequestMapping("/init")
    public AjaxResult initCompanyInfo(@RequestBody Company company){
        try {
            companyServer.add(company);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
