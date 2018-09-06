package com.jiea.bull.controller;

import com.jiea.bull.common.annotation.Log;
import com.jiea.bull.common.utils.JwtUtils;
import com.jiea.bull.common.utils.RedisLock;
import com.jiea.bull.common.utils.ValidationUtils;
import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import com.jiea.bull.vo.LoginReq;
import com.jiea.bull.vo.Resp;
import io.lettuce.core.output.ValueListOutput;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisLock redisLock;

    private static int i = 500;

    @ResponseBody
    @PostMapping("login")
    public Resp login() {

        String value = UUID.randomUUID().toString();

        try {
            if(redisLock.lockBlock("abc", value, 100L)){
                i--;
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisLock.unLock("abc", value);
        }


        /*ValidationUtils.validate(loginReq);

        Long id = userService.login(loginReq);

        // 生成 token
        String token = JwtUtils.generateToken(id);

        return Resp.ok("login success", token);*/
        return Resp.ok();
    }





}
