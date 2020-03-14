package com.hwua.service.Impl;

import com.hwua.mapper.UserMapper;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import com.hwua.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
private UserMapper userMapper;
    @Override
    public Users login(Users user) throws Exception {
        return userMapper.queryUser(user);
    }

    @Override
    public List<Users> findAllUsers() throws Exception {
        return userMapper.queryAllUsers();
    }

    @Override
    public int addUser(Users user) throws Exception {
        user.setPassword(MD5Util.md5hash(user.getPassword(),user.getUsername()));
            return userMapper.addUser(user);
    }

    @Override
    public Users findUserById(String id) throws Exception {
        return userMapper.queryUserById(id);
    }

    @Override
    public Users queryUserByUserName(String username) throws Exception {
        return userMapper.queryUserByUserName(username);
    }
}
