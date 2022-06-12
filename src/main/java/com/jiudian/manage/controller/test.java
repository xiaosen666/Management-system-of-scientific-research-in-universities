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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    UserMapper userMapper;
    @Autowired
    ToolMapper toolMapper;

    @RequestMapping("/getuname")
    public Map getUname(HttpServletRequest request)
    {
        String uname=request.getSession().getAttribute("uname").toString();
        StateSignal signal = new StateSignal();
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("uname",uname);
        return signal.getResult();
    }

    @RequestMapping("/test")
    public Map Test(@RequestParam String name, @RequestParam String phone, @RequestParam String num, @RequestParam String message,String backDate,String equiment,String tid,HttpServletRequest request) throws ParseException {
        System.out.println(backDate);
        StateSignal signal = new StateSignal();
        Lend lend=new Lend();
        lend.userid=(String)request.getSession().getAttribute("this_userid");
        lend.name=name;
        lend.num=num;
        lend.phone=phone;
        lend.message=message;
        lend.lendDate=new Date();
        SimpleDateFormat strformat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            lend.backDate=strformat.parse(backDate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        lend.equiment=equiment;

        lendMapper.Insert_a_record(lend);
        //设置租用后的设备库存量
        Integer oldAmount=Integer.parseInt(toolMapper.get_toolAmount_by_id(tid));
        Integer newAmount=oldAmount-Integer.parseInt(num);
        Tool updateTool=new Tool();
        updateTool.setId(Integer.parseInt(tid));
        updateTool.setTool(equiment);
        updateTool.setAmount(newAmount.toString());
        toolMapper.update_tool(updateTool);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }

    @RequestMapping("/getLendList")
    public Map getlendlist()
    {
        StateSignal signal = new StateSignal();
        List<Lend> lend_list=lendMapper.Get_all_lend_list();
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("lend_list",lend_list);
        return signal.getResult();
    }

    @RequestMapping("/set_check_name")
    public Map setCheckName(String lendid,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        String uname=request.getSession().getAttribute("uname").toString();
        lendMapper.Setcheckname(uname,Integer.parseInt(lendid));
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);

        return signal.getResult();
    }

    @RequestMapping("/getLendListbyUid")
    public Map getLendListbyUid(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        List<Lend> lend_list=lendMapper.Get_lend_list(request.getSession().getAttribute("this_userid").toString());
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("lend_list",lend_list);
        return signal.getResult();
    }

    @RequestMapping("/insert_tool")
    public Map insertTool(String equiment,String amount)
    {
        StateSignal signal = new StateSignal();
        Tool newTool=new Tool();
        newTool.setTool(equiment);
        newTool.setAmount(amount);
        toolMapper.InsertTool(newTool);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);

        return signal.getResult();
    }

    @RequestMapping("/delect_tool")
    public Map delectTool(String id)
    {

        StateSignal signal = new StateSignal();
        toolMapper.delete_tool(id);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);

        return signal.getResult();
    }

    @RequestMapping("/update_tool")
    public Map updateTool(String id,String equiment,String amount)
    {

        StateSignal signal = new StateSignal();
        Tool newTool=new Tool();
        newTool.setTool(equiment);
        newTool.setId(Integer.parseInt(id));
        newTool.setAmount(amount);
        toolMapper.update_tool(newTool);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);

        return signal.getResult();
    }

    @RequestMapping("/get_equiment_list")
    public Map getEquimentList()
    {
        StateSignal signal = new StateSignal();
        List<Tool> tool_list=toolMapper.get_all_tool();

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("tool_list",tool_list);
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
    public Map putnews(String anthor, String myclass, String date, MultipartFile photo, String title, HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        String fileName = UUID.randomUUID().toString()+photo.getOriginalFilename();

        String filePath = "webFile/";
        //String filePath = ".\\src\\main\\resources\\static\\File\\";
        //String RealfilePath = "File\\"+fileName;
        String RealfilePath = "webFile/"+fileName;

        boolean b = false;
        try {
            b = FileUtil.uploadFile(photo.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        News mynews=new News();
        mynews.setNews_anthor(anthor);
        mynews.setNews_class(myclass);
        mynews.setNews_date(date);
        mynews.setNews_photo(RealfilePath);
        mynews.setNews_title(title);

        newsMapper.put_news(mynews);

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

    @RequestMapping("/update_money_state")
    public Map update_checked_money_State(String state,String pid,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        String Uname=request.getSession().getAttribute("uname").toString();
        op_appMapper.Edit_money_State(state,pid,Uname,new Date());

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
        order.setMoney(Double.valueOf(checked.getMoney()));
        order.setUserid(Integer.parseInt(request.getSession().getAttribute("this_userid").toString()));
        order.setState(0);
        order.setApplyUid(checked.getUid());

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
        order.setApplyUid(checked.getUid());

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

    @RequestMapping("/get_one_op_app")
    public Map GetOneop_app(String opid)
    {
        StateSignal signal = new StateSignal();

        Opening_app op_app=op_appMapper.Get_checked(opid);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        signal.put("op_app",op_app);
        return signal.getResult();
    }

    @RequestMapping("/opening_application")
    public Map Opening_application(String p_name,String your_id,String phone,String p_type,String t_name,String money,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        String your_name=request.getSession().getAttribute("uname").toString();
        Opening_app new_op_app=new Opening_app();

        new_op_app.setP_name(p_name);
        new_op_app.setP_type(p_type);
        new_op_app.setYour_name(your_name);
        new_op_app.setYour_id(your_id);
        new_op_app.setPhone(phone);
        new_op_app.setT_name(t_name);
        new_op_app.setState("-1");//-1代表未做审核操作 0代表拒绝通过 1代表允许通过
        new_op_app.setMoney(money);
        new_op_app.setUid(request.getSession().getAttribute("this_userid").toString());
        op_appMapper.Insert_Op_app(new_op_app);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }

    @RequestMapping("/get_e_project")
    public Map getEndProject(HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();
        List<Project> ep_List = end_appMapper.get_all_checked_End_app();
        List<End_app> end_appList=new ArrayList<>();
        List<User> users=new ArrayList<>();
        for(Project i:ep_List)
        {
            end_appList.add(end_appMapper.get_End_checked(String.valueOf(i.getPid())));
            users.add(userMapper.selectByPrimaryKey(Integer.parseInt(i.getUserid())));
        }
        signal.put("ep_list",ep_List);
        signal.put("end_applist",end_appList);
        signal.put("check_uname",users);
        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }
    @RequestMapping("/backTool")
    public Map backtool(String lid,String tool,String num,HttpServletRequest request)
    {
        StateSignal signal = new StateSignal();

        toolMapper.back_tool(num,tool);
        lendMapper.delect_lend(lid);

        signal.put(State.SuccessCode);
        signal.put(State.SuccessMessage);
        return signal.getResult();
    }
}
