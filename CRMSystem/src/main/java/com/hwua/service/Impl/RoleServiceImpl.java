package com.hwua.service.Impl;

import com.hwua.mapper.RoleMapper;
import com.hwua.pojo.Role;
import com.hwua.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRoles() throws Exception {
        return roleMapper.findAllRoles();
    }

    @Override
    public int addRidAndUid(String userId, String roleId) throws Exception {
        return  roleMapper.addRidAndUid(userId, roleId);
    }

    @Override
    public String[] queryUidByUid(String userId) throws Exception {
        return roleMapper.queryUidByUid(userId);
    }

    @Override
    public String[] queryRidByUid(String userId) throws Exception {
        return roleMapper.queryRidByUid(userId);
    }

    @Override
    public int addRole(Role role) throws Exception {
        return roleMapper.addRole(role);
    }

    @Override
    public Role queryRoleById(String id) throws Exception {
        return roleMapper.queryRoleById(id);
    }

    @Override
    public int deleteRoleById(String id) throws Exception {
        return roleMapper.deleteRoleById(id);
    }
}
