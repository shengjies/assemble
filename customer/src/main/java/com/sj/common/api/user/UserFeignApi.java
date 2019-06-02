package com.sj.common.api.user;

import com.sj.common.web.AjaxResult;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface UserFeignApi {
    /**
     * 验证服务器密码
     * @param map 验证信息
     * @return
     */
    @RequestLine("POST /api/ser/v")
    @Headers("Content-Type: application/json")
    AjaxResult verificationSer(Map<String,Object> map);
}
