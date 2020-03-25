package com.hwua.service;

import com.hwua.pojo.Users;

public interface UserService {
    public Integer saveUser(Users user);
    public Users findUser(String userName);
}
