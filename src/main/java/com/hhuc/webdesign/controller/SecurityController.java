package com.hhuc.webdesign.controller;


import com.alibaba.fastjson.JSONObject;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/logout")
    public ReturnPkg logoutSuccess(){
        return ReturnPkg.success("logout");
    }
}
