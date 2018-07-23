package com.jiea.bull;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jie.bull.dao")
public class BullApplication {

    public static void main(String[] args) {
        SpringApplication.run(BullApplication.class, args);
    }
}
