package com.hwua.mapper;

import com.hwua.pojo.Permission;

public interface PermissionMapper {
    public Permission findPermissionById(String roleId)throws Exception;
}
