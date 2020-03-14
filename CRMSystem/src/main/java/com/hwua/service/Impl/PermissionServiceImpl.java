package com.hwua.service.Impl;


import com.hwua.mapper.PermissionMapper;
import com.hwua.pojo.Permission;
import com.hwua.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRid(String id) throws Exception {
        return permissionMapper.findPermissionByRid(id);
    }

    @Override
    public List<Permission> findAllPermissions() throws Exception {
        return permissionMapper.findAllPermissions();
    }

    @Override
    public int addPermission(Permission permission) throws Exception {
        return permissionMapper.addPermission(permission);
    }

    @Override
    public List<Permission> findPermissionById(String id) throws Exception {
        return permissionMapper.findPermissionById(id);
    }

    @Override
    public int addPermissionRole(String permissionId, String roleId) throws Exception {
        return permissionMapper.addPermissionRole(permissionId,roleId);
    }


}
