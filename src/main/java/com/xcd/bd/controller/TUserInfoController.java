package com.xcd.bd.controller;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.AjaxResult;
import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.UserVo;
import com.xcd.bd.service.IExtendService;
import com.xcd.bd.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ModelAndView userInfo(){
        ModelAndView mode = new ModelAndView("views/userManage");
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        TUserInfo userInfo = userInfoService.findByUserName(userBo.getUserName());
        mode.addObject("user",userInfo);
        return mode;
    }

    @RequestMapping("/user/rewardInfo")
    public ModelAndView rewardInfo(){
        return new ModelAndView("views/rewardList");
    }

    @RequestMapping("/user/userList")
    public ModelAndView userList(){
        return new ModelAndView("views/userList");
    }

    @RequestMapping("/user/transferList")
    public ModelAndView transferList(){
        return new ModelAndView("views/transferList");
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


    @RequestMapping("/update")
    public String doUpdate(@RequestBody UserVo userVo){
        int res = userInfoService.update(userVo);
        if(res>0){
            return "index";
        }
        return "index";
    }

    @RequestMapping("/user/searchRecomInfo")
    @ResponseBody
    public List<RecommRelVo> findRecommInfoByUserId(){
        Subject subject = SecurityUtils.getSubject();
        TUserInfo userBo = (TUserInfo) subject.getPrincipal();
        return service.findRecommInfoByUserId(userBo.getUserId());
    }

}
