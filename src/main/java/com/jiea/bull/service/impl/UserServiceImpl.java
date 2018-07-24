package com.jiea.bull.service.impl;

import com.jiea.bull.dao.UserMapper;
import com.jiea.bull.domain.User;
import com.jiea.bull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user;
    }
}
