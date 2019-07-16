package com.sj.customer.company.mapper;

import com.sj.customer.company.demain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公司信息
 */
@Mapper
public interface CompanyMapper {
    /**
     * 添加公司信息
     * @param company 公司信息
     * @return
     * @throws Exception
     */
    int add(Company company) throws Exception;

    /**
     * 查询对应公司
     * @param id
     * @return
     */
    Company selectById(@Param("id")int id);

    List<Company> selectall();

}
