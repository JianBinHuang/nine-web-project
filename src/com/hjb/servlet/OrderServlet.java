package com.hjb.servlet;

import com.hjb.dao.IOrderDao;
import com.hjb.entity.Address;
import com.hjb.entity.Cart;
import com.hjb.entity.Orders;
import com.hjb.service.IOrderService;
import com.hjb.service.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 10:08
 */
@WebServlet("/order")
public class OrderServlet extends BaseServlet{

    private IOrderService orderService=new OrderServiceImpl();

    public String showOrders(Integer uid, HttpServletRequest request){
        //通过用户id查询购物车集合信息
        List<Cart> cartList= orderService.getShopCartListByUid(uid);

        //通过用户id查询用户地址集合信息
        List<Address> addressList=orderService.getAddressListByUid(uid);

        //请求存入
        request.setAttribute("cartList",cartList);
        request.setAttribute("addressList",addressList);
        //转发页面
        return "forward:order.jsp";
    }


    /**
     * 提交订单
     * @param uid
     * @return
     */
    public String commitOrder(Integer uid,Integer sum,Integer aid){

        orderService.addOrder(uid,sum,aid);

        return "forward:order?method=showList&uid="+uid;
    }

    /**
     * 查看订单列表
     */
    public String showList(Integer uid,HttpServletRequest request){

        //查询订单集合数据 queryOrderListByUid()

        List<Orders> orderList=orderService.queryOrderListByUid(uid);

        //存起来
        request.setAttribute("orderList", orderList);

        return "forward:orderList.jsp";
    }


    /**
     * 显示订单详情信息
     * @param oid
     * @return
     */
    public String getOrderDetail(String oid,HttpServletRequest request){

       Orders orders= orderService.getOrderDetail(oid);

        //请求存入信息
        request.setAttribute("orders", orders);

        return "forward:orderDetail.jsp";
    }
}
