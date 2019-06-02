package com.sj.login.company.mapper;

import com.sj.login.company.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公司信息
 */
@Mapper
public interface CompanyMapper {
    /**
     * 分页查询公司信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    List<Company> list(@Param("page")int page,@Param("rows")int rows);

    /**
     * 统计总数
     * @return
     */
    int count();

    /**
     * 添加公司信息
     * @param company 公司信息
     * @return
     */
    int add(Company company) throws Exception;

    /**
     * 修改公司信息
     * @param company 公司信息
     * @return
     * @throws Exception
     */
    int edit(Company company) throws Exception;

    /**
     * 删除公司信息
     * @param id 编号
     * @return
     */
    int del(@Param("id")int id);

    /**
     * 根据id查询对应公司
     * @param id
     * @return
     */
    Company selectById(@Param("id")int id);
}
