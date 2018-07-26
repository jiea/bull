package com.jiea.bull.service.impl;

import com.jiea.bull.common.exception.BullException;
import com.jiea.bull.dao.UserMapper;
import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import com.jiea.bull.vo.LoginReq;
import com.jiea.bull.vo.Resp;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long login(LoginReq loginReq) {
        User user = userMapper.getByUsername(loginReq.getUsername());
        if (Objects.isNull(user) || !user.getPassword().equals(new Sha256Hash(loginReq.getPassword(), user.getSalt()).toString())) {
            throw new BullException("用户名或密码不正确");
        }

        if (user.getStatus() == 0) {
            throw new BullException("用户被禁用, 请联系管理员.");
        }

        return user.getId();
    }

    @Override
    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
