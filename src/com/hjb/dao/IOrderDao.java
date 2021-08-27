package com.hjb.dao;

import com.hjb.entity.Address;
import com.hjb.entity.Cart;
import com.hjb.entity.Item;
import com.hjb.entity.Orders;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 10:21
 */
public interface IOrderDao {

    List<Cart> getShopCartListByUid(Integer uid);

    List<Address> getAddressListByUid(Integer uid);

    void addOrder(Orders orders);

    void addItems(List<Item> itemList);

    List<Orders> queryOrderListByUid(Integer uid);

    Orders queryOrdersByOid(String oid);

    List<Item> queryItemListByOid(String oid);

    Orders getOrdersByOid(String oid);

    void updateOrderStateByOid(String oid);
}
