package com.kelin.mall.service;

import com.kelin.mall.common.api.CommonResult;

public interface UmsMemberService {
    CommonResult generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone, String authCode);
}
