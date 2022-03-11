package com.jiudian.manage.model;

public class Lend {
    public String userid;
    public String equiment;
    public String num;
    public String phone;

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String user_id) {
        this.userid = user_id;
    }

    public String getEquiment() {
        return equiment;
    }

    public void setEquiment(String equiment) {
        this.equiment = equiment;
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

    @Override
    public String toString() {
        return "Lend{" +
                "user_id='" + userid + '\'' +
                ", equiment='" + equiment + '\'' +
                ", num='" + num + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
