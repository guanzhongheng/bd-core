package com.xcd.bd.controller;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.AjaxResult;
import com.xcd.bd.mode.vo.UserVo;
import com.xcd.bd.service.IUserInfoService;
import com.xcd.bd.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@Controller
public class TUserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/user/register")
    @ResponseBody
    public AjaxResult doReregistry(@RequestBody UserVo userVo) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        if (userVo == null || StringUtils.isEmpty(userVo.getUserName()) || StringUtils.isEmpty(userVo.getPassword())) {
            result.setErrorCode("-1");
            result.setMsg("用户名和密码不能为空！");
            return result;
        }

        TUserInfo dbUser = userInfoService.findByUserName(userVo.getUserName());
        if (dbUser != null) {
            result.setErrorCode("-2");
            result.setMsg("用户名已存在！");
            return result;
        }


        int res = userInfoService.insert(userVo);
        if (res < 1) {
            result.setErrorCode("-3");
            result.setMsg("系统异常！");
            return result;
        } else {
            result.setSuccess(true);
        }

        return result;
    }


    @RequestMapping("/update")
    public String doUpdate(@RequestBody UserVo userVo){
        int res = userInfoService.update(userVo);
        if(res>0){
            return "index";
        }
        return "index";
    }



}
