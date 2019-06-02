package com.sj.login.user.api;

import com.sj.common.web.AjaxResult;
import com.sj.login.user.domain.User;
import feign.Headers;
import feign.RequestLine;

/**
 * 用户操作
 */
public interface UserApi {
    /**
     * 初始化用户信息
     * @param user 用户信息
     * @return
     */
    @RequestLine("POST /api/u/init")
    @Headers("Content-Type: application/json")
    AjaxResult init(User user);
}
