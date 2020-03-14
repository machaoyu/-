package com.hwua.mapper;


import com.hwua.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    public Users queryUser(Users user)throws  Exception;
    public List<Users> queryAllUsers()throws Exception;
    public int addUser(Users user)throws Exception;
    public Users queryUserById(String id)throws Exception;
    public Users queryUserByUserName(String username)throws Exception;
}
