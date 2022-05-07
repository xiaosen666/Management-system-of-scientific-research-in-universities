package com.jiudian.manage.controller;

import com.jiudian.manage.mapper.End_appMapper;
import com.jiudian.manage.model.End_app;
import com.jiudian.manage.service.UserService;
import com.jiudian.manage.until.FileUtil;
import com.jiudian.manage.until.State;
import com.jiudian.manage.until.StateSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping(value = "/upFile")
public class FileController {
    @Autowired
    UserService userService;
    @Autowired
    End_appMapper end_appMapper;

    @RequestMapping("/upFilePhoto.do")
    public Map upFilePhoto(@RequestParam MultipartFile file,@RequestParam int userid){
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();

        String filePath = ".\\src\\main\\resources\\static\\File\\";
        String RealfilePath = "File\\"+fileName;
        boolean photo = userService.photo(userid, RealfilePath);
        boolean b = false;
        try {
           b = FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StateSignal signal = new StateSignal();
        if(b&&photo){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

    @RequestMapping("/upFile.do")
    public Map upMyFile(@RequestParam MultipartFile file,String p_name,String your_name,String your_id,String phone,String p_type,String t_name,String money){
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        String filePath = "..\\mytest_file\\";
        End_app end_app = new End_app();
        end_app.setFile_name(fileName);
        end_app.setYour_name(your_name);
        end_app.setYour_id(your_id);
        end_app.setP_name(p_name);
        end_app.setPhone(phone);
        end_app.setP_type(p_type);
        end_app.setT_name(t_name);
        end_app.setMoney(money);
        end_app.setState("-1");

        end_appMapper.Insert_End_app(end_app);

        boolean b = false;
        try {
            b = FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StateSignal signal = new StateSignal();
        if(b){
            signal.put(State.SuccessCode);
            signal.put(State.SuccessMessage);
        }else {
            signal.put(State.ErrorCode);
            signal.put(State.ErrorMessage);
        }
        return signal.getResult();
    }

}
