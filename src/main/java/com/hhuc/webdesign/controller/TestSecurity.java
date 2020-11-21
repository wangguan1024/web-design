package com.hhuc.webdesign.controller;


import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.service.SecurityService;
import com.hhuc.webdesign.service.UserService;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestSecurity {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public ReturnPkg user(){
        String testName = securityService.getUserNameBySecurity();
        if(testName.equals("anonymousUser")){
            return ReturnPkg.notLogin();
        }else{
            User user = userService.selectUserByUserName(testName);
            return ReturnPkg.success(user);
        }

    }
}
