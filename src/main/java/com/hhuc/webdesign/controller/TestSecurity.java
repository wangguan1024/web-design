package com.hhuc.webdesign.controller;


import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestSecurity {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public ReturnPkg user(){
        return ReturnPkg.success("has logged");
    }
}
