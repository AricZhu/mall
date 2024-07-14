package com.kelin.mall.controller;

import com.kelin.mall.common.api.CommonResult;
import com.kelin.mall.dto.UmsAdminLoginParam;
import com.kelin.mall.mbg.model.UmsAdmin;
import com.kelin.mall.mbg.model.UmsPermission;
import com.kelin.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam) {
        UmsAdmin admin = adminService.register(umsAdminParam);
        if (admin == null) {
            return CommonResult.failed("注册失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("login")
    public CommonResult login(@RequestBody UmsAdminLoginParam adminLoginParam) {
        String token = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.failed("用户名或密码错误!");
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);

        return CommonResult.success(map);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("permission/{adminId}")
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable("adminId") Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
