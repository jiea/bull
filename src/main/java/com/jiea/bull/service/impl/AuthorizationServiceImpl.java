package com.jiea.bull.service.impl;

import com.google.common.collect.Sets;
import com.jiea.bull.common.consts.SysConst;
import com.jiea.bull.dao.MenuMapper;
import com.jiea.bull.dao.RoleMapper;
import com.jiea.bull.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getUserPermissions(Long userId) {
        Set<String> perms;
        if(SysConst.ADMIN_ID == userId){
            perms = menuMapper.getAllPerms();
        }else{
            perms = menuMapper.getPermsByUserId(userId);
        }

        return perms;
    }

    @Override
    public Set<String> getUserRoles(Long userId) {
        Set<String> roles;
        if(SysConst.ADMIN_ID == userId){
            roles= roleMapper.getAllRoles();
        }else{
            roles = menuMapper.getRolesByUserId(userId);
        }

        return roles;
    }

}
