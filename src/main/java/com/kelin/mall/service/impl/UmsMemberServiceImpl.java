package com.kelin.mall.service.impl;

import com.kelin.mall.common.api.CommonResult;
import com.kelin.mall.service.RedisService;
import com.kelin.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        String key = this.REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        redisService.set(key, sb.toString());
        redisService.expire(key, this.AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String authCodeInRedis = redisService.get(this.REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (authCode.equals(authCodeInRedis)) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码错误");
        }
    }
}
