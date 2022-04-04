package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.LendMapper;
import com.jiudian.manage.mapper.NewsMapper;
import com.jiudian.manage.mapper.Op_appMapper;
import com.jiudian.manage.model.Lend;
import com.jiudian.manage.model.News;
import com.jiudian.manage.model.Opening_app;
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
public class test {
    @Autowired
    LendMapper lendMapper;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    Op_appMapper op_appMapper;

    @RequestMapping("/test")
    public Map Test(@RequestParam String name, @RequestParam String phone, @RequestParam String num, @RequestParam String message,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        Lend lend=new Lend();
        lend.userid=(String)request.getSession().getAttribute("this_userid");
        lend.name=name;
        lend.num=num;
        lend.phone=phone;
        lend.message=message;
        lendMapper.Insert_a_record(lend);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }
    @RequestMapping("/getLendList")
    public Map getlendlist(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        String uid="";
        uid=(String)request.getSession().getAttribute("this_userid");
        List<Lend> lend_list=lendMapper.Get_lend_list(uid);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("lend_list",lend_list);
        return signal.getResult();
    }
    @RequestMapping("/getNewsList")
    public Map getnewslist(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        List<News> news_list=newsMapper.Get_news_list();
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("news_list",news_list);
        return signal.getResult();
    }
    @RequestMapping("/get_op_app_List")
    public Map Getop_app_list(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        List<Opening_app> op_list=op_appMapper.get_Op_app();
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("op_list",op_list);
        return signal.getResult();
    }

    @RequestMapping("/update_state")
    public Map update_State(String state,String p_id,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        op_appMapper.Edit_State(state,p_id);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }

    @RequestMapping("/opening_application")
    public Map Opening_application(String p_name,String your_name,String your_id,String phone,String p_type,String t_name,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        Opening_app new_op_app=new Opening_app();

        new_op_app.setP_name(p_name);
        new_op_app.setP_type(p_type);
        new_op_app.setYour_name(your_name);
        new_op_app.setYour_id(your_id);
        new_op_app.setPhone(phone);
        new_op_app.setT_name(t_name);
        new_op_app.setState("-1");//-1代表未做审核操作 0代表拒绝通过 1代表允许通过

        op_appMapper.Insert_Op_app(new_op_app);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }
}
