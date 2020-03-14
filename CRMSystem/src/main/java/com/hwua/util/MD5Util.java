package com.hwua.util;

import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MD5Util {

    public static String md5hash(String password, String username) {
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 1024).toHex();
    }

}
