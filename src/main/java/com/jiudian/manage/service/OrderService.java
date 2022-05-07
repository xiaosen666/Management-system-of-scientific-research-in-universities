package com.jiudian.manage.service;

import com.jiudian.manage.model.Order;

import java.util.List;

public interface OrderService {
    /**
     * 增加订单
     * @param householdname
     * @param id
     * @param starttime
     * @param endtime
     * @param t_name
     * @param userid
     * @return
     */
    public boolean addOrder(String householdname, String id, String starttime, String endtime, String t_name, int userid);

    /**
     * 删除订单
     * @param orderid
     * @return
     */
    public boolean delOrder(int orderid);

    /**
     * 修改订单状态
     * @param orderid
     * @param state
     * @return
     */
    public boolean updateOrderState(int orderid,int state);

    /**
     *获取所有开题项目
     * @return
     */
    public List<Order> getAllOrder(int pageNum, int pageSize);

    /**
     *获取所有结题项目
     * @return
     */
    public List<Order> get_e_AllOrder(int pageNum, int pageSize);
}
