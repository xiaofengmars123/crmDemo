package com.hwua.service.impl;

import com.hwua.mapper.UserMapper;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import com.hwua.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer saveUser(Users user) {
        user.setPassword(MD5Util.md5hash(user.getUsername(),user.getPassword()));
        return userMapper.saveUser(user);
    }

    @Override
    public Users findUser(String userName) {
        System.out.println("service启动");
        Users user = userMapper.findUser(userName);
        System.out.println(user);
        return user;
    }
}
