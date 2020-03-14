package com.hwua.service;

import com.hwua.pojo.Users;

import java.util.List;

public interface UserService {
    public Users login(Users user)throws Exception;
    public List<Users> findAllUsers()throws Exception;
    public int addUser(Users user)throws Exception;
    public Users findUserById(String id)throws Exception;
    public Users queryUserByUserName(String username)throws Exception;
}
