package com.hhuc.webdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan
@SpringBootApplication
@EnableWebSecurity
public class WebDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDesignApplication.class, args);
    }

}
