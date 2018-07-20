package com.jiea.bull.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test/hello")
    public String test(){
        System.out.println("hello wrold");
        return "s";
    }
}
