package com.sj.login.company.server;

import com.sj.common.web.AjaxResult;
import com.sj.login.company.domain.Company;
import com.sj.login.company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司信息
 */
@Service
public class CompanyServerImpl implements ICompanyServer {

    @Autowired
    private CompanyMapper companyMapper;
    /**
     * 分页查询公司信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    public AjaxResult list(int page, int rows) {
        List<Company> companies = companyMapper.list((page -1)*rows,rows);
        return AjaxResult.tableData(companyMapper.count(),companies);
    }

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return
     * @throws Exception
     */
    public int edit(Company company) throws Exception {
        return companyMapper.edit(company);
    }

    /**
     * 删除公司信息
     * @param id 编号
     * @return
     * @throws Exception
     */
    public int del(int id) throws Exception {
        return companyMapper.del(id);
    }
}
