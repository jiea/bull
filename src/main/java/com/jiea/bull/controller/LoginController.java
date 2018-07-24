package com.jiea.bull.controller;

import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import com.jiea.bull.vo.LoginReq;
import com.jiea.bull.vo.Resp;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("login")
    public Resp login(@RequestBody LoginReq loginReq){



        User user = userService.getByUsername(loginReq.getUsername());

        if(Objects.isNull(user) || !user.getPassword().equals(new Sha256Hash(loginReq.getPassword(), user.getSalt()))){
            return Resp.error("用户名或密码不正确");
        }

        if(user.getStatus() == 0){
            return Resp.error("用户被禁用, 请联系管理员.");
        }


        return null;
    }

}
