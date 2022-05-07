package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.End_appMapper;
import com.jiudian.manage.model.Config;
import com.jiudian.manage.model.End_app;
import com.jiudian.manage.service.ConfigService;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    @Autowired
    ConfigService configService;
    @Autowired
    End_appMapper end_appMapper;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getConfig.do")
    public Map getConfig(HttpServletRequest request){
        List<End_app> end_app_list = end_appMapper.get_End_app();
        StateSignal signal = new StateSignal();
        if(end_app_list!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("config",end_app_list);
            signal.put("ad_name",request.getSession().getAttribute("this_userid"));
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    /**
     * 修改配置
     * @param managesalary  经理薪水百分比
     * @param staffsalary   员工薪水百分比
     * @param cleanerssalary    保洁员薪水百分比
     * @param manage    经理底薪
     * @param staff     员工底薪
     * @param cleaner   保洁员底薪
     * @param totalmoney    总营业额
     * @param totalroom     总卖出房数
     * @return
     */
    @RequestMapping(value = "/updateConfig.do")
    public Map updateConfig(@RequestParam double managesalary,@RequestParam double  staffsalary,@RequestParam double cleanerssalary,@RequestParam double manage,@RequestParam double staff,@RequestParam double cleaner,@RequestParam(required = false,defaultValue = "-1") double totalmoney,@RequestParam(required = false,defaultValue = "-1") double totalroom){
        boolean update = configService.update(managesalary, staffsalary, cleanerssalary, manage, staff, cleaner, totalmoney, totalroom);
        StateSignal signal = new StateSignal();
        if(update){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }
}
