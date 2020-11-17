package com.hhuc.webdesign.controller;


import com.hhuc.webdesign.entity.User;
import com.hhuc.webdesign.service.UserService;
import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ReturnPkg register(@RequestBody User user){
        return userService.Insert(user);
    }

    @PutMapping("/update")
    public ReturnPkg update(@RequestBody User user){
        return userService.Update(user);
    }
}
