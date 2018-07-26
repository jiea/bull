package com.jiea.bull.service;

import com.jiea.bull.domain.User;
import com.jiea.bull.vo.LoginReq;

public interface UserService {

    Long login(LoginReq loginReq);

    User getById(Long userId);
}
