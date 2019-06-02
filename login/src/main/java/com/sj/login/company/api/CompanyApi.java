package com.sj.login.company.api;

import com.sj.common.web.AjaxResult;
import com.sj.login.company.domain.Company;
import feign.Headers;
import feign.RequestLine;

public interface CompanyApi {

    @RequestLine("POST /api/c/init")
    @Headers("Content-Type: application/json")
    AjaxResult initCompany(Company company);
}
