package com.hwua.mapper;

import com.hwua.pojo.Role;

import java.util.List;

public interface RoleMapper {
    public List<Role> findRoleById(String id)throws Exception;
}
