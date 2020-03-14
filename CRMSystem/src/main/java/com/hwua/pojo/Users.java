package com.hwua.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Users {

  private String id;
  private String email;
  private String username;
  private String password;
  private String phoneNum;
  private long status;
  private List<Role> roleList=new ArrayList<>();


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
