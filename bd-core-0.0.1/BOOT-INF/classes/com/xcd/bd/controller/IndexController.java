package com.xcd.bd.controller;

import com.xcd.bd.dao.TAcctInfoMapper;
import com.xcd.bd.dao.TUserInfoMapper;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.AccountVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private TAcctInfoMapper acctInfoMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;

    @RequestMapping("mainIndex")
    public String mainIndex(Model model){
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        AccountVo acct = acctInfoMapper.findByUserId(userBo.getUserId());
        model.addAttribute("acc",acct);
        TUserInfo us = userInfoMapper.findByUserName(userBo.getUserName());
        model.addAttribute("us",us);
        return "index";
    }
}
