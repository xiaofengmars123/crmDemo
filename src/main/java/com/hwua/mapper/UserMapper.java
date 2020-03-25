package com.hwua.mapper;

import com.hwua.pojo.Users;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    public Integer saveUser(Users user);
    public Users findUser(String userName);
}
