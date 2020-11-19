package com.hhuc.webdesign.controller;


import com.hhuc.webdesign.entity.PasswordPkg;
import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.service.SecurityService;
import com.hhuc.webdesign.service.UserService;
import com.hhuc.webdesign.util.ReturnPkg;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public ReturnPkg register(@RequestBody User user){
        return userService.register(user);
    }

    @PutMapping("/my/user/changePwd")
    public ReturnPkg changePwd(@RequestBody PasswordPkg passwordPkg){
        return userService.changePwd(passwordPkg);
    }

    @GetMapping("/my/user/detail")
    public ReturnPkg getUser(){
        String userName = securityService.getUserNameBySecurity();
        return ReturnPkg.success(userService.selectUserByUserName(userName));
    }

    @PutMapping("/my/user/detail")
    public ReturnPkg updateUser(@RequestBody User user){
        String userName = securityService.getUserNameBySecurity();
        Integer id = userService.selectUserByUserName(userName).getId();
        user.setId(id);
        return userService.updateUser(user);
    }
}
