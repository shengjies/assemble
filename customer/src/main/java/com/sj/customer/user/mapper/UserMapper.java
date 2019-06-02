package com.sj.customer.user.mapper;

import com.sj.customer.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 用户登录信息
     * @param user 用户信息
     * @return
     */
    User login(User user);

    /**
     * 根据用户名称查询对应的用户信息
     * @param username 用户名称
     * @return
     */
    User findByUserName(@Param("username") String username);

    /**
     * 分页查询公司信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    List<User> list(@Param("page") int page, @Param("rows") int rows,@Param("companyId")int companyId);

    /**
     * 统计总数
     * @return
     */
    int count(@Param("companyId")int companyId);

    /**
     * 添加用户信息
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    int add(User user) throws Exception;

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    int edit(User user) throws Exception;

    /**
     * 删除用户信息
     * @param id 用户编号
     * @return
     * @throws Exception
     */
    int del(@Param("id") int id) throws Exception;
}
