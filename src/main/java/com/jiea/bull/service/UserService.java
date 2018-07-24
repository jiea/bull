package com.jiea.bull.service;

import com.jiea.bull.domain.User;

public interface UserService {

    User getByUsername(String username);
}
