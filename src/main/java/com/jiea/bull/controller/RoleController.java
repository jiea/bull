package com.jiea.bull.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @RequestMapping("role/ss")
    @RequiresPermissions("role:test:ss")
    public String test(){
        return "ss";
    }
}
