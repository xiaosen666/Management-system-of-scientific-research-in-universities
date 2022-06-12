package com.jiudian.manage.model;

public class End_app {
    int application_id;
    String p_name;
    String your_name;
    String your_id;
    String phone;
    String p_type;
    String t_name;
    String state;
    String money;
    String file_name;
    String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getYour_name() {
        return your_name;
    }

    public void setYour_name(String your_name) {
        this.your_name = your_name;
    }

    public String getYour_id() {
        return your_id;
    }

    public void setYour_id(String your_id) {
        this.your_id = your_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @Override
    public String toString() {
        return "End_app{" +
                "application_id='" + application_id + '\'' +
                ", p_name='" + p_name + '\'' +
                ", your_name='" + your_name + '\'' +
                ", your_id='" + your_id + '\'' +
                ", phone='" + phone + '\'' +
                ", p_type='" + p_type + '\'' +
                ", t_name='" + t_name + '\'' +
                ", state='" + state + '\'' +
                ", money='" + money + '\'' +
                ", file_name='" + file_name + '\'' +
                '}';
    }
}
