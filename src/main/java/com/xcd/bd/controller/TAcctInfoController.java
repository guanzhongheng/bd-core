package com.xcd.bd.controller;

import com.xcd.bd.entity.TAcctInfo;
import com.xcd.bd.service.IAcctInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@RestController
@RequestMapping(value = "/tAcctInfo")
public class TAcctInfoController {
    @Autowired
    private IAcctInfoService tAcctInfoService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<TAcctInfo> tAcctInfos = tAcctInfoService.findAllList();
        return tAcctInfos;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam String id) {
        TAcctInfo tAcctInfo = tAcctInfoService.get(id);
        return tAcctInfo;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody TAcctInfo tAcctInfo) {
        if (tAcctInfoService.insert(tAcctInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<TAcctInfo> tAcctInfos) {
        if (tAcctInfoService.insertBatch(tAcctInfos) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody TAcctInfo tAcctInfo) {
        if (tAcctInfoService.update(tAcctInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody TAcctInfo tAcctInfo) {
        if (tAcctInfoService.delete(tAcctInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
