package com.jiea.bull.controller;

import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/add")
    public String add() {
        User user = new User();
        user.setUsername("张三丰");
        user.setMobile("1212121212");
        user.setPassword("121212");
        user.setSalt("12121");
        int i = userService.add(user);
        return String.valueOf(i);
    }

    @ResponseBody
    @RequestMapping("/user/get")
    public User get() {
        User user = userService.get(1);
        return user;
    }
}
