package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.End_appMapper;
import com.jiudian.manage.mapper.Op_appMapper;
import com.jiudian.manage.model.End_app;
import com.jiudian.manage.model.Opening_app;
import com.jiudian.manage.model.Room;
import com.jiudian.manage.service.RoomService;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    Op_appMapper op_appMapper;
    @Autowired
    End_appMapper end_appMapper;

    @RequestMapping("/addRoom.do")
    public Map addRoom(@RequestParam String local,@RequestParam double money,@RequestParam int state,@RequestParam int type){
        boolean b = roomService.addRoom(local, money, state, type);
        StateSignal signal = new StateSignal();
        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }

    @RequestMapping("/delRoom.do")
    public Map delRoom(@RequestParam int roomid){
        boolean b = roomService.delRoom(roomid);
        StateSignal signal = new StateSignal();
        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }
    @RequestMapping("/getRoom.do")
    public Map selectRoom(@RequestParam int state,@RequestParam int type,@RequestParam int pageNum,@RequestParam int pageSize){
        List<Room> roomByState = roomService.getRoomByState(state, type,pageNum,pageSize);
        StateSignal signal = new StateSignal();
        if(roomByState!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("List",roomByState);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }

    @RequestMapping("/updateRoom.do")
    public Map updateRoom(@RequestParam int roomid,@RequestParam(required = false,defaultValue = "null") String local,@RequestParam(required = false,defaultValue = "-1") double money,@RequestParam(required = false,defaultValue = "-1") int state,@RequestParam(required = false,defaultValue = "-1") int type){
        boolean b = roomService.updateRoom(roomid, local, money, state, type);
        StateSignal signal = new StateSignal();
        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }
    @RequestMapping("/getProjectById.do")
    public Map getRoomById(@RequestParam String pid){
        Opening_app opening_app = op_appMapper.Get_checked(pid);
        StateSignal signal = new StateSignal();
        if(opening_app!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("project",opening_app);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }

    @RequestMapping("/get_e_ProjectById.do")
    public Map get_e_ById(@RequestParam String pid){
        End_app end_app = end_appMapper.get_End_checked(pid);
        StateSignal signal = new StateSignal();
        if(end_app!=null){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
            signal.put("project",end_app);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return  signal.getResult();
    }


}
