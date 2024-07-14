package com.kelin.mall.service;

import com.kelin.mall.mbg.model.UmsAdmin;
import com.kelin.mall.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String userName);

    UmsAdmin register(UmsAdmin umsAdmin);

    String login(String userName, String password);

    List<UmsPermission> getPermissionList(Long adminId);
}
