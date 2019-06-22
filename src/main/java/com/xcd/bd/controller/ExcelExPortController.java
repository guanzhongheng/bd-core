package com.xcd.bd.controller;

import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.service.IExtendService;
import com.xcd.bd.utils.DateUtil;
import com.xcd.bd.utils.JxlsExcelView;
import org.jxls.common.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/22  2:25 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/22    create
 */
@Controller
public class ExcelExPortController {
    private static final String TEMPLATE_PATH = "jxl/template/";
    @Autowired
    private IExtendService extendService;

    @RequestMapping(value = "/export/bscList",method = RequestMethod.GET)
    public ModelAndView productDesinExport(HttpServletResponse response){
        List<RewardDetailVo> bscList = extendService.selectRewadsByGtZero();
        Context context = new Context();
        context.putVar("bscList", bscList);
        return new ModelAndView(
                new JxlsExcelView(TEMPLATE_PATH + "BSC_EXPORT.xlsx", "BSC导入清单-"+ DateUtil.date2Str(new Date(),"yyyy-MM-dd"), context));
    }
}