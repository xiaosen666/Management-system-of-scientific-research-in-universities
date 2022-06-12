package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.End_appMapper;
import com.jiudian.manage.mapper.Op_appMapper;
import com.jiudian.manage.model.Config;
import com.jiudian.manage.model.End_app;
import com.jiudian.manage.model.Opening_app;
import com.jiudian.manage.model.Project;
import com.jiudian.manage.service.ConfigService;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    @Autowired
    ConfigService configService;
    @Autowired
    End_appMapper end_appMapper;
    @Autowired
    Op_appMapper op_appMapper;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getConfig.do")
    public Map getConfig(HttpServletRequest request){
        List<Project> p_List = op_appMapper.Get_all_checked();
        List<Opening_app> opening_appList=new ArrayList<>();
        for(Project i:p_List)
        {
            opening_appList.add(op_appMapper.Get_checked(String.valueOf(i.getPid())));
        }

        StateSignal signal = new StateSignal();
        if(opening_appList!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("p_List",p_List);
            signal.put("op_List",opening_appList);
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
