package com.hwua.mapper;

import com.hwua.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {
    public List<Role> findRoleByUid(String id)throws Exception;
    public List<Role> findAllRoles()throws Exception;
    public int addRidAndUid(String userId,String roleId)throws Exception;
    public String[] queryUidByUid(String userId)throws Exception;
    public String[] queryRidByUid(String userId)throws Exception;
    public int addRole(Role role)throws Exception;
    public Role queryRoleById(String id)throws Exception;
    public int deleteRoleById(String id)throws Exception;
}
