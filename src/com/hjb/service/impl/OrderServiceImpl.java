package com.hjb.service.impl;

import com.hjb.dao.ICartDao;
import com.hjb.dao.IOrderDao;
import com.hjb.dao.impl.ICartDaoImpl;
import com.hjb.dao.impl.OrderDaoImpl;
import com.hjb.entity.Address;
import com.hjb.entity.Cart;
import com.hjb.entity.Item;
import com.hjb.entity.Orders;
import com.hjb.service.IOrderService;
import com.hjb.util.RandomUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 10:21
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao orderDao=new OrderDaoImpl();

    private ICartDao cartDao=new ICartDaoImpl();


    @Override
    public List<Cart> getShopCartListByUid(Integer uid) {
        return orderDao.getShopCartListByUid(uid);
    }

    /**
     * 通过uid返回Address集合
     * @param uid
     * @return
     */
    @Override
    public List<Address> getAddressListByUid(Integer uid) {
        return orderDao.getAddressListByUid(uid);
    }

    /**
     * 把订单添加到页面
     * @param uid
     * @param sum
     * @param aid
     */
    @Override
    public void addOrder(Integer uid, Integer sum, Integer aid) {

        //创建随机数的工具类,createAc

        String active = RandomUtils.createActive();
        //实现订单实体类
        Orders orders=new Orders();
        //set存入各种数据
        //订单oid设置为随机数的参数
        //aid直接存入
        orders.setOid(active);
        orders.setAid(aid);
        orders.setOcount(new BigDecimal(sum));
        //总数的话使用bigDecimal 存入Sum
        orders.setUid(uid);
        //uid直接存入
        orders.setOstate(1);
        //订单状态设置为1  支付状态 1 未支付
        //订单时间直接Date
        orders.setOtime(new Date());
        //然后直接使用业务层的添加方法直接存入订单
        orderDao.addOrder(orders);
        //查询当前用户的购物车信息
        List<Cart> cartList = cartDao.showCart(uid);
        List<Item> itemList=new ArrayList<>();

        //判断，购物车集合不等于空的话
        //循环遍历购物车信息 简而言之就是把购物车跟订单关联起来
            if(cartList!=null){

                for (Cart cart : cartList) {
                    //实现订单实体类
                    //订单存入信息
                    Item item=new Item();
                    //oid存入随机数的参数
                    item.setOid(active);
                    //pid存入购物车的pid
                    item.setPid(cart.getPid());
                    //总数存入购物车的总数
                    item.setIcount(cart.getCcount());
                    //编号存入购物车的编号
                    item.setInum(cart.getCnum());

                    itemList.add(item);
                }

            }
        //添加订单项信息
        //业务层调用添加订单方法
        orderDao.addItems(itemList);
        //清空购物车
        //业务层调用清除方法
        cartDao.clear(uid);
    }

    /**
     * 通过uid返回order集合
     * @param uid
     * @return
     */
    @Override
    public List<Orders> queryOrderListByUid(Integer uid) {
        //通过dao层调用方法
        return orderDao.queryOrderListByUid(uid);
    }

    @Override
    public Orders getOrderDetail(String oid) {

        //查询订单信息 信息只包含订单和地址的
        Orders orders=orderDao.queryOrdersByOid(oid);
        //查询订单项集合信息，订单项中的商品也要有数据
        List<Item> itemList= orderDao.queryItemListByOid(oid);
        //再把订单项集合信息加到订单中

        orders.setItemList(itemList);

        return orders;
    }

    @Override
    public Orders getOrdersByOid(String oid) {
        return orderDao.getOrdersByOid(oid);
    }

    @Override
    public void updateOrderStateByOid(String oid) {
        orderDao.updateOrderStateByOid(oid);
    }
}
