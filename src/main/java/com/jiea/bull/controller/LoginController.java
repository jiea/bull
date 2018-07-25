package com.jiea.bull.controller;

import com.jiea.bull.common.utils.JwtUtils;
import com.jiea.bull.common.utils.ValidationUtils;
import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import com.jiea.bull.vo.LoginReq;
import com.jiea.bull.vo.Resp;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("login")
    public Resp login(@RequestBody LoginReq loginReq){

        ValidationUtils.validate(loginReq);

        Long id = userService.login(loginReq);

        // 生成 token
        String token = JwtUtils.generateToken(id);

        return Resp.ok("login success", token);
    }

}
