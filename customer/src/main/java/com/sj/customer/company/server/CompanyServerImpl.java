package com.sj.customer.company.server;

import com.sj.customer.company.demain.Company;
import com.sj.customer.company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公司操作
 */
@Service
public class CompanyServerImpl implements ICompanyServer {
    @Autowired
    private CompanyMapper companyMapper;
    /**
     * 添加公司信息
     * @param company 公司信息
     * @return
     * @throws Exception
     */
    public int add(Company company) throws Exception {
        return companyMapper.add(company);
    }
}
