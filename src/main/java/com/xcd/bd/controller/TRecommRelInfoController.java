package com.xcd.bd.controller;

import com.xcd.bd.entity.TRecommRelInfo;
import com.xcd.bd.service.IRecommRelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@RestController
@RequestMapping(value = "/tRecommRelInfo")
public class TRecommRelInfoController {
    @Autowired
    private IRecommRelInfoService tRecommRelInfoService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<TRecommRelInfo> tRecommRelInfos = tRecommRelInfoService.findAllList();
        return tRecommRelInfos;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam String id) {
        TRecommRelInfo tRecommRelInfo = tRecommRelInfoService.get(id);
        return tRecommRelInfo;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody TRecommRelInfo tRecommRelInfo) {
        if (tRecommRelInfoService.insert(tRecommRelInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<TRecommRelInfo> tRecommRelInfos) {
        if (tRecommRelInfoService.insertBatch(tRecommRelInfos) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody TRecommRelInfo tRecommRelInfo) {
        if (tRecommRelInfoService.update(tRecommRelInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody TRecommRelInfo tRecommRelInfo) {
        if (tRecommRelInfoService.delete(tRecommRelInfo) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

}
