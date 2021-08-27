package com.hjb.service;

import com.hjb.entity.Address;
import com.hjb.entity.Cart;
import com.hjb.entity.Orders;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 10:21
 */
public interface IOrderService {

    List<Cart> getShopCartListByUid(Integer uid);

    List<Address> getAddressListByUid(Integer uid);

    void addOrder(Integer uid, Integer sum, Integer aid);

    List<Orders> queryOrderListByUid(Integer uid);

    Orders getOrderDetail(String oid);

    Orders getOrdersByOid(String oid);

    void updateOrderStateByOid(String oid);
}
