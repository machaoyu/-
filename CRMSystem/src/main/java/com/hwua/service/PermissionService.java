package com.hwua.service;


import com.hwua.pojo.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findPermissionByRid(String id) throws Exception;
    public List<Permission> findAllPermissions()throws Exception;
    public int addPermission(Permission permission)throws Exception;
    public List<Permission> findPermissionById(String id)throws Exception;
    public int addPermissionRole(String permissionId,String roleId)throws Exception;
}
