package com.xcd.bd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LoginController {


    @RequestMapping("/login")
    public String firstLogin(){
        return "sys/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }

    @RequestMapping("/loginSucess")
    public String loginSuce(Map<String,Object> map){
        return "sys/index";
    }


    @RequestMapping("/admin/register")
    public String register(){
        return "sys/register";
    }


}


