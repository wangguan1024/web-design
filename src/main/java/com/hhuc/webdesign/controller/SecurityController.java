package com.hhuc.webdesign.controller;


import com.hhuc.webdesign.util.ReturnPkg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/logout")
    public ReturnPkg logoutSuccess(){
        return ReturnPkg.success("logout");
    }
}
