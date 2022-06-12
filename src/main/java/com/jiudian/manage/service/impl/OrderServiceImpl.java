package com.jiudian.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.jiudian.manage.mapper.ConfigMapper;
import com.jiudian.manage.mapper.OrderMapper;
import com.jiudian.manage.mapper.RoomMapper;
import com.jiudian.manage.model.Config;
import com.jiudian.manage.model.Order;
import com.jiudian.manage.model.Room;
import com.jiudian.manage.service.OrderService;
import com.jiudian.manage.until.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ConfigMapper configMapper;

    @Override
    public boolean addOrder(String householdname, String id, String starttime, String endtime, String t_name, int userid) {
        //Room room = roomMapper.selectByPrimaryKey(roomid);
//        if(t_name.getState()!=1){
//            return false;
//        }
        Order order = new Order();
        order.setName(householdname);
        order.setId(id);

        order.setT_name(t_name);
        order.setUserid(userid);
        order.setState(0);
        //double money = TimeUtil.getBetweenDay(starttime,endtime)*room.getMoney();
        //order.setMoney(money);

        int insert = orderMapper.insertSelective(order);
        if(insert>0){
            Room room1 = new Room();
            //room1.setRoomid(roomid);
            room1.setState(2);
            int i = roomMapper.updateByPrimaryKeySelective(room1);
            if(i>0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean delOrder(int orderid) {
        Order order = orderMapper.selectByPrimaryKey(orderid);
        String t_name = order.getT_name();
        Room room = new Room();
        //room.setRoomid(t_name);
        room.setState(1);
        int i = roomMapper.updateByPrimaryKeySelective(room);
        if(i>0){
            int i1 = orderMapper.deleteByPrimaryKey(orderid);
            if(i1>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateOrderState(int orderid, int state) {
        Order order = orderMapper.selectByPrimaryKey(orderid);
        if(order==null){
            return false;
        }
        String t_name = order.getT_name();
        Room room = new Room();
        //room.setRoomid(roomid);
        int i = 1;
        if(state==2){
            room.setState(3);
            i = roomMapper.updateByPrimaryKeySelective(room);
        }
        if(state==3){
            room.setState(1);
            i = roomMapper.updateByPrimaryKeySelective(room);
        }
        order.setState(state);
        if(i>0){
            int i1 = orderMapper.updateByPrimaryKeySelective(order);
            if(i1>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Order> getAllOrder(int pageNum, int pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum,pageSize);
        return orderMapper.getAllUser(request.getSession().getAttribute("this_userid").toString());
    }

    @Override
    public List<Order> get_e_AllOrder(int pageNum, int pageSize,HttpServletRequest request) {
        PageHelper.startPage(pageNum,pageSize);
        return orderMapper.get_e_AllUser(request.getSession().getAttribute("this_userid").toString());
    }
}
