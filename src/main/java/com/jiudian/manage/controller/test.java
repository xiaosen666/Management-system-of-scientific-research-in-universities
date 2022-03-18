package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.LendMapper;
import com.jiudian.manage.mapper.NewsMapper;
import com.jiudian.manage.model.Lend;
import com.jiudian.manage.model.News;
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

    @RequestMapping("/test")
    public Map Test(@RequestParam String name, @RequestParam String phone, @RequestParam String num, @RequestParam String equiment, HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        Lend lend=new Lend();
        lend.userid=(String)request.getSession().getAttribute("this_userid");
        lend.equiment=equiment;
        lend.num=num;
        lend.phone=phone;
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
}
