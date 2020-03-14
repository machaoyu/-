package com.hwua.pojo;


import lombok.Data;


import java.util.List;
@Data
public class Role {

  private String id;
  private String roleName;
  private String roleDesc;
  private List<Permission> permissionList;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

}
