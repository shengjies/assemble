package com.sj.customer.company.server;

import com.sj.customer.company.demain.Company;

/**
 * 公司服务器
 */
public interface ICompanyServer {
    /**
     * 添加公司信息
     * @param company 公司信息
     * @return
     * @throws Exception
     */
    int add(Company company) throws Exception;
}
