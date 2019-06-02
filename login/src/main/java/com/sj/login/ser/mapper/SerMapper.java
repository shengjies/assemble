package com.sj.login.ser.mapper;

import com.sj.login.ser.domain.Ser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务器管理
 */
@Mapper
public interface SerMapper {
    /**
     * 分页查询服务器信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    List<Ser> list(@Param("page")int page,@Param("rows")int rows);

    /**
     * 统计总数
     * @return
     */
    int count();

    /**
     * 添加服务器信息
     * @param ser
     * @return
     */
    int add(Ser ser) throws Exception;

    /**
     * 修改服务器信息
     * @param ser 服务器信息
     * @return
     */
    int edit(Ser ser) throws Exception;

    /**
     * 删除服务器信息
     * @param id 编号
     * @return
     * @throws Exception
     */
    int del(int id) throws Exception;

    /**
     * 查询一台未注册满的服务器信息
     * @return
     */
    Ser selectSer();

    /**
     * 修改服务器用户数量
     * @param id
     * @return
     * @throws Exception
     */
    int editNum(@Param("id")int id) throws Exception;

    /**
     * 根据编号和密码查询对应服务器信息
     * @param id
     * @return
     */
    Ser selectById(@Param("id")int id,@Param("spwd")String pwd);

    /**
     * 根据id查询对应服务器信息
     * @param id
     * @return
     */
    Ser selectSerById(@Param("id")int id);
}
