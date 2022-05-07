package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.*;
import com.jiudian.manage.model.*;
import com.jiudian.manage.until.FileUtil;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import com.jiudian.manage.until.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class test {
    @Autowired
    LendMapper lendMapper;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    Op_appMapper op_appMapper;
    @Autowired
    End_appMapper end_appMapper;
    @Autowired
    OrderMapper orderMapper;

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
//        String uid="";
//        uid=(String)request.getSession().getAttribute("this_userid");
//        List<Lend> lend_list=lendMapper.Get_lend_list(uid);
        List<Lend> lend_list=lendMapper.Get_all_lend_list();
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

    @RequestMapping("/putNews")
    public Map putnews(String anthor, String myclass, String date, String photo, String title, HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        News mynews=new News();
        mynews.setNews_anthor(anthor);
        mynews.setNews_class(myclass);
        mynews.setNews_date(date);
        mynews.setNews_photo(photo);
        mynews.setNews_title(title);

        newsMapper.put_news(mynews);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);

        return signal.getResult();
    }

    @RequestMapping("/getUserLogin")
    public Map getuserlogin(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        try
        {
            String uid=request.getSession().getAttribute("this_userid").toString();
            String power=request.getSession().getAttribute("this_userpower").toString();
            signal.put("userid",uid);
            signal.put("power",power);
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }
        catch (Exception e){
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
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

    @RequestMapping("/get_end_app_List")
    public Map Getend_app_list(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        List<End_app> end_list=end_appMapper.get_End_app();
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("end_list",end_list);
        return signal.getResult();
    }

    @RequestMapping("/out_login")
    public Map Out_Login(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        request.getSession().removeAttribute("this_userid");
        request.getSession().removeAttribute("this_userpower");
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
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

    @RequestMapping("/get_checked")
    public Map Get_checked(String p_id,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        Opening_app checked = op_appMapper.Get_checked(p_id);

        Order order=new Order();
        order.setPid(Integer.parseInt(p_id));
        order.setName(checked.getYour_name());
        order.setId(checked.getYour_id());
        order.setT_name(checked.getT_name());
        order.setMoney(0.0);
        order.setUserid(Integer.parseInt(request.getSession().getAttribute("this_userid").toString()));
        order.setState(0);

        orderMapper.insert(order);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }

    @RequestMapping("/get_end_checked")
    public Map Get_End_checked(String p_id,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        End_app checked = end_appMapper.get_End_checked(p_id);

        Order order=new Order();
        order.setPid(Integer.parseInt(p_id));
        order.setName(checked.getYour_name());
        order.setId(checked.getYour_id());
        order.setT_name(checked.getT_name());
        order.setMoney(Double.valueOf(checked.getMoney()));
        order.setUserid(Integer.parseInt(request.getSession().getAttribute("this_userid").toString()));
        order.setState(0);

        orderMapper.insert_e_project(order);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }

    @RequestMapping("/update_endapp_state")
    public Map update_endapp_State(String state,String p_id,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        end_appMapper.Edit_end_State(state,p_id);
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
