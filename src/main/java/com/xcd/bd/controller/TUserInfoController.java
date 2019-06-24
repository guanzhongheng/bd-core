package com.xcd.bd.controller;

import com.xcd.bd.entity.BaseTable;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.AjaxResult;
import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.mode.vo.UserVo;
import com.xcd.bd.service.IExtendService;
import com.xcd.bd.service.IUserInfoService;
import com.xcd.bd.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@Controller
public class TUserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IExtendService service;

    @RequestMapping("/user/userInfo")
    public ModelAndView userInfo() {
        ModelAndView mode = new ModelAndView("views/userManage");
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        TUserInfo userInfo = userInfoService.findByUserName(userBo.getUserName());
        mode.addObject("user", userInfo);
        return mode;
    }

    @RequestMapping("/user/rewardInfo")
    public ModelAndView rewardInfo() {
        return new ModelAndView("views/rewardList");
    }

    @RequestMapping("/user/userList")
    public ModelAndView userList() {
        return new ModelAndView("views/userList");
    }

    @RequestMapping("/user/transferList")
    public ModelAndView transferList() {
        return new ModelAndView("views/transferList");
    }


    @RequestMapping("/user/transferInfo")
    @ResponseBody
    public BaseTable selectUserStatus(String status){
        BaseTable table = new BaseTable();
        List<RewardDetailVo> list = service.selectListByUserStatus(status);
        table.setRows(list);
        return table;
    }


    @RequestMapping("/user/register")
    @ResponseBody
    public AjaxResult doReregistry(UserVo userVo) {
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


    @RequestMapping("/user/updatePassword")
    @ResponseBody
    public AjaxResult doUpdatePassword(String password) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        UserVo userVo = new UserVo();
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        userVo.setPassword(Md5Util.encryptionPassWord(userBo.getUserName(), password));
        userVo.setUserId(userBo.getUserId());
        int res = userInfoService.update(userVo);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setMsg("系统异常，修改密码失败！");
        }
        return result;
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public AjaxResult doUpdate(String realName,String attachUrl,String phone) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        UserVo userVo = new UserVo();
        userVo.setUserId(userBo.getUserId());
        userVo.setRealName(realName);
        userVo.setAttachUrl(attachUrl);
        userVo.setPhone(phone);
        int res = userInfoService.update(userVo);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setMsg("系统异常，修改用户失败！");
        }
        return result;
    }

    @RequestMapping("/user/updateRecieverInfo")
    @ResponseBody
    public AjaxResult doUpdateReciever(String recieverName,String recieverPhone,String recieverAddress) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        UserVo userVo = new UserVo();
        userVo.setUserId(userBo.getUserId());
        userVo.setRecieverName(recieverName);
        userVo.setRecieverPhone(recieverPhone);
        userVo.setRecieverAddress(recieverAddress);
        int res = userInfoService.update(userVo);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setMsg("系统异常，修改密码失败！");
        }
        return result;
    }

    @RequestMapping("/user/searchRecomInfo")
    @ResponseBody
    public BaseTable findRecommInfoByUserId() {
        BaseTable table = new BaseTable();
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        List<RecommRelVo> list = service.findRecommInfoByUserId(userBo.getUserId());
        table.setRows(list);
        return table;
    }

    @RequestMapping("/user/updateUserReward")
    @ResponseBody
    public AjaxResult updateUserReward(Long id,Long userId,Double amount) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        int res = service.dealTransaction(id,amount,userId);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setMsg("系统异常，修改密码失败！");
        }
        return result;
    }

}
