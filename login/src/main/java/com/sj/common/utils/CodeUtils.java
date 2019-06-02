package com.sj.common.utils;

public class CodeUtils {

    /**
     * 系统异常
     */
    public static final int CODE_SYS_ERROR =0;

    public static final String CODE_SYS_ERROR_MSG = "系统异常,请联系管理员";

    /**
     * 用户登录成功
     */
    public static final int CODE_USER_LOGIN = 1;

    public static final String CODE_USER_LOGIN_MSG = "用户登录成功";

    /**
     * 用户名或密码错误
     */
    public static final int CODE_USER_ERROR=2;

    public static final String CODE_USER_ERROR_MSG = "用户名或密码错误";

    /**
     * 系统密码匹配错误
     */
    public static final int CODE_SYS_PWD = 3;

    public static final String CODE_SYS_PWD_MSG = "系统密码错误,请联系管理员";

    /**
     * 公司信息异常
     */
    public static final int CODE_COMPANY_ERROR = 4;

    public static final String CODE_COMPANY_ERROR_MSG = "公司信息异常，请联系管理员";

    /**
     * 服务器验证失败
     */
    public static final int CODE_SER_ERROR = 5;

    public static final String CODE_SER_ERROR_MSG = "服务器验证失败，请联系管理员";

}
