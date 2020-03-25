package com.hwua.controller;

import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
   Map<String,Object>map=new HashMap<>();

    @PostMapping("saveUser")
    @ResponseBody
    public Map<String,Object> saveUser(Users user){
        System.out.println(user);

    Integer integer = userService.saveUser(user);
        if(integer!=0){
        map.put("info","添加用户成功");
    }else {
        map.put("info","添加用户失败请重新添加");
    }
        return map;
}
    @PostMapping("login.do")
    public ModelAndView login(Users users)throws Exception {
        System.out.println("Controller启动");
        String info = null;
        ModelAndView mv = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();//创建一个用户（主题）
        Users user = userService.findUser(users.getUsername());
        System.out.println(user);
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
            try {
                //进行登录
                currentUser.login(token);

                //底层交给securityManager对象去调用注册得realm从文件或数据库中找到此登录用户的用户名和密码信息，拿到这些信息以后
                //和token中的用户名、密码进行比对。
            } catch (UnknownAccountException uae) {
                info = "此用户不存在";
            } catch (IncorrectCredentialsException ice) {
                info = "密码不正确";
            } catch (LockedAccountException lae) {
                info = "用户被锁定";
            } catch (AuthenticationException ae) {
                info = ae.getMessage();
            }
        }
        if (info == null) {
            mv.setViewName("main");
        } else {
            mv.setViewName("index");
            mv.addObject("info", info);
        }
        return mv;
    }
}
