package com.xcd.bd.controller;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@RestController
@RequestMapping(value = "/tUserInfo")
public class TUserInfoController {
    @Autowired
    private IUserInfoService tUserInfoService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<TUserInfo> tUserInfos = tUserInfoService.findAllList();
        return tUserInfos;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam String id) {
        TUserInfo tUserInfo = tUserInfoService.get(id);
        return tUserInfo;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody TUserInfo tUserInfo) {
        if (tUserInfoService.insert(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<TUserInfo> tUserInfos) {
        if (tUserInfoService.insertBatch(tUserInfos) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody TUserInfo tUserInfo) {
        if (tUserInfoService.update(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody TUserInfo tUserInfo) {
        if (tUserInfoService.delete(tUserInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
