package com.xcd.bd.controller;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.AjaxResult;
import com.xcd.bd.mode.vo.UserVo;
import com.xcd.bd.service.IUserInfoService;
import com.xcd.bd.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@RestController
@RequestMapping(value = "/userInfo")
public class TUserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/registry")
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

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<TUserInfo> tUserInfos = userInfoService.findAllList();
        return tUserInfos;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody UserVo tUserInfo) {
        if (userInfoService.insert(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<TUserInfo> tUserInfos) {
        if (userInfoService.insertBatch(tUserInfos) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody TUserInfo tUserInfo) {
        if (userInfoService.update(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody TUserInfo tUserInfo) {
        if (userInfoService.delete(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
