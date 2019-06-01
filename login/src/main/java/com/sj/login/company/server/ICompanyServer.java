package com.sj.login.company.server;

import com.sj.common.web.AjaxResult;
import com.sj.login.company.domain.Company;

/**
 * 公司管理
 */
public interface ICompanyServer {
    /**
     * 分页查询公司信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    AjaxResult list(int page,int rows);

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return
     */
    int edit(Company company) throws Exception;

    /**
     * 删除公司信息
     * @param id 编号
     * @return
     * @throws Exception
     */
    int del(int id) throws Exception;
}
