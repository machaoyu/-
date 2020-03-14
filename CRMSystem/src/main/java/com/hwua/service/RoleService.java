package com.hwua.service;

import com.hwua.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRoles()throws Exception;
    public int addRidAndUid(String userId,String roleId)throws Exception;
    public String[] queryUidByUid(String userId)throws Exception;
    public String[] queryRidByUid(String userId)throws Exception;
    public int addRole(Role role)throws Exception;
    public Role queryRoleById(String id)throws Exception;
    public int deleteRoleById(String id)throws Exception;
}
