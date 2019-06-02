package com.sj.login.ser.server;

import com.sj.common.web.AjaxResult;
import com.sj.login.ser.domain.Ser;
import com.sj.login.ser.mapper.SerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 服务管理
 */
@Service
public class SerServerImpl implements ISerServer {

    @Autowired
    private SerMapper serMapper;
    /**
     * 分页查询服务器信息
     * @param page 页数大小
     * @param rows 页面大小
     * @return
     */
    public AjaxResult list(int page, int rows) {
        List<Ser> sers = serMapper.list((page-1)*rows,rows);
        return AjaxResult.tableData(serMapper.count(),sers);
    }

    /**
     * 添加服务器信息
     * @param ser 服务器信息
     * @return
     */
    public int add(Ser ser) throws Exception {
        ser.setSpwd(UUID.randomUUID().toString().replace("-",""));
        return serMapper.add(ser);
    }

    /**
     * 修改服务器信息
     * @param ser 服务器信息
     * @return
     */
    public int edit(Ser ser) throws Exception {
        return serMapper.edit(ser);
    }

    /**
     * 删除服务器信息
     * @param id 编号
     * @return
     * @throws Exception
     */
    public int del(int id) throws Exception {
        return serMapper.del(id);
    }

    /**
     * 系统密码验证
     * @param ser 服务信息
     * @throws Exception
     */
    public void verificationSer(Ser ser) throws Exception {
        if(ser == null) throw new Exception("验证信息为空");
        Ser s = serMapper.selectById(ser.getId(),ser.getSpwd());
        if(s ==null) throw new Exception("验证失败");
    }
}
