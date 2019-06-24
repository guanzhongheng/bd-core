package com.xcd.bd.controller;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String firstLogin(){
        return "sys/login";
    }

    @RequestMapping("/")
    public String firstIndex(){
        return "/main";
    }

    @RequestMapping("/index")
    public String index(Model model){
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        model.addAttribute("user",userBo);
        return "main";
    }


    @RequestMapping(value="/check" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object check(String userName,String password)throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, Md5Util.encryptionPassWord(userName,password));
        Subject subject = SecurityUtils.getSubject();
        String message = null;
        try {
            subject.login(usernamePasswordToken); // 完成登录
        } catch (UnknownAccountException e) {
            logger.error(e.getMessage());
            errInfo = "无效的账户";
        } catch (LockedAccountException e) {
            logger.error(e.getMessage());
            errInfo = "账户被禁用(锁定),请联系管理员";
        } catch (IncorrectCredentialsException e) {
            logger.error(e.getMessage());
            errInfo = "账户/密码错误";
        } catch (Exception e) {
            logger.error(e.getMessage());
            errInfo = "系统异常请联系管理员";
        }
        if(!subject.isAuthenticated()){
            usernamePasswordToken.clear();
            errInfo = "usererror";
        }
        map.put("result",errInfo);
        return map;
    }


    @RequestMapping("/register")
    public String register(){
        return "sys/register";
    }


}


