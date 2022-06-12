package com.jiudian.manage.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Lend {
    public int lendid;
    public String userid;
    public String name;
    public String num;
    public String phone;
    public String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date lendDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date backDate;
    public String equiment;
    public String checkUname;

    public int getLendid() {
        return lendid;
    }

    public void setLendid(int lendid) {
        this.lendid = lendid;
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String user_id) {
        this.userid = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public String getEquiment() {
        return equiment;
    }

    public void setEquiment(String equiment) {
        this.equiment = equiment;
    }

    public String getCheckUname() {
        return checkUname;
    }

    public void setCheckUname(String checkUname) {
        this.checkUname = checkUname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
