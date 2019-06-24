package com.xcd.bd.controller;

import com.xcd.bd.entity.BaseTable;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.*;
import com.xcd.bd.service.IExtendService;
import com.xcd.bd.service.IUserInfoService;
import com.xcd.bd.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<TUserInfo> list = userInfoService.findListByStatus(status);
        table.setRows(list);
        return table;
    }

    @RequestMapping("/user/register")
    public String doReregistry(UserVo userVo, Model model) {
        if (userVo == null || StringUtils.isEmpty(userVo.getUserName()) || StringUtils.isEmpty(userVo.getPassword())) {
            model.addAttribute("errMsg","用户名和密码不能为空！");
            return "redirect:/register";
        }
        TUserInfo dbUser = userInfoService.findByUserName(userVo.getUserName());
        if (dbUser != null) {
            model.addAttribute("errMsg","用户名已存在！");
            return "redirect:/register";
        }
        int res = userInfoService.insert(userVo);
        if (res < 1) {
            model.addAttribute("errMsg","系统异常！");
            return "redirect:/register";
        }else{
            return "redirect:login";
        }
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
            result.setMsg("系统异常，转账失败！");
        }
        return result;
    }

    @RequestMapping("/user/updateStatus")
    @ResponseBody
    public AjaxResult updateUserStatus(Long userId) {
        AjaxResult result = new AjaxResult();
        result.setSuccess(false);
        UserVo userVo = new UserVo();
        userVo.setUserId(userId);
        userVo.setStatus('1');
        int res = userInfoService.update(userVo);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setMsg("系统异常，修改密码失败！");
        }
        return result;
    }

    @RequestMapping("/user/doFindUndealRewads")
    @ResponseBody
    public BaseTable findUndealRewards(){
        BaseTable table = new BaseTable();
        List<RewardVo> list = service.findUndealRewards();
        table.setRows(list);
        table.setTotal(list.size());
        return table;
    }

}
