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
    public int add(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User get(int i) {
        return userMapper.selectByPrimaryKey(Long.valueOf(i));
    }
}
