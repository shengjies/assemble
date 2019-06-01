package com.sj.login.ser.server;

import com.sj.common.web.AjaxResult;
import com.sj.login.ser.domain.Ser;

/**
 * 服务器管理
 */
public interface ISerServer {
    /**
     * 分页查询服务器信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    AjaxResult list(int page,int rows);

    /**
     * 添加服务器信息
     * @param ser 服务器信息
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
    int del(int id)throws Exception;
}
