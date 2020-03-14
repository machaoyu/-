package com.hwua.mapper;

import com.hwua.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PermissionMapper {
    public List<Permission> findPermissionByRid(String id) throws Exception;
    public List<Permission> findAllPermissions()throws Exception;
    public int addPermission(Permission permission)throws Exception;
    public List<Permission> findPermissionById(String id)throws Exception;
    public int addPermissionRole(String permissionId,String roleId)throws Exception;
}
