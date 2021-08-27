package com.hjb.dao.impl;

import com.hjb.dao.IOrderDao;
import com.hjb.entity.*;
import com.hjb.util.C3P0Utils;
import com.hjb.util.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.core.annotation.Order;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 10:21
 */
public class OrderDaoImpl implements IOrderDao {

    @Override
    public List<Cart> getShopCartListByUid(Integer uid) {

        List<Cart> cartList=new ArrayList<>();
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select c.c_id as cid,c.p_id as pid,c.u_id as uid, c.c_num as cnum,c.c_count as ccount,p.t_id as tid ,p.p_image as pimage,p.p_info as pinfo,p.p_name as pname,p.p_price as pprice,p.p_state as pstate,p.p_time as ptime from cart c inner join product p on c.p_id = p.p_id where c.u_id=?";
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);
            if(list!=null){
                for (Map<String, Object> map : list) {
                    Cart cart=new Cart();
                    Product product=new Product();

                    //赋值到BeanUtils的populate里
                    BeanUtils.populate(cart, map);
                    BeanUtils.populate(product, map);

                    //把product设置到cart对象中
                    cart.setProduct(product);

                    cartList.add(cart);
                }
            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    @Override
    public List<Address> getAddressListByUid(Integer uid) {
        String sql="select * from address where u_id=?";

        return CommonUtils.commonQuery(sql, Address.class, uid);
    }

    @Override
    public void addOrder(Orders orders) {
        String sql="insert into orders values(?,?,?,?,?,?)";

        CommonUtils.commonUpdate(sql,orders.getOid(),orders.getUid(),orders.getAid(),orders.getOcount(),orders.getOtime(),orders.getOstate());

    }

    @Override
    public void addItems(List<Item> itemList) {

        for (Item item : itemList) {
            String sql="insert into item values(null,?,?,?,?)";

            CommonUtils.commonUpdate(sql, item.getOid(),item.getPid(),item.getIcount(),item.getInum());
        }
    }

    @Override
    public List<Orders> queryOrderListByUid(Integer uid) {

        List<Orders> ordersList=new ArrayList<>();

        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());

        String sql="select o.a_id as aid , o.o_count as ocount,o.o_id as oid,o.o_state as ostate,o.o_time as otime," +
                " a.a_detail as adetail,a.a_name as aname,a.a_phone as aphone, a.a_state as astate " +
                " from orders o inner JOIN address a on o.a_id = a.a_id where o.u_id=?";

        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);

            if(list!=null){
                for (Map<String, Object> map : list) {
                    Orders orders=new Orders();
                    Address address=new Address();

                    //存入
                    BeanUtils.populate(orders, map);
                    BeanUtils.populate(address, map);

                    //把地址存入order订单里面
                    orders.setAddress(address);
                    //然后把值都存入集合里
                    ordersList.add(orders);

                }
            }

        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    @Override
    public Orders queryOrdersByOid(String oid) {
        //QR 调用c3p0
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql= "select o.a_id as aid , o.o_count as ocount, o.o_id as oid,o.o_state as ostate,o.o_time as otime," +
                "a.a_detail as adetail,a.a_name as aname,a.a_phone as aphone,a.a_state as astate" +
                " from orders o inner join address a on o.a_id = a.a_id where  o.o_id=?";

        try {
            //调用qr 放入sql,调用map处理 ,oid

            Map<String, Object> query = queryRunner.query(sql, new MapHandler(), oid);

            //判断 qr 不等于空的话
            if(query!=null){
                //订单实体类
                Orders orders=new Orders();
                //地址实体类
                Address address=new Address();
                //BeanUtils.放入参数

                BeanUtils.populate(orders, query);
                BeanUtils.populate(address, query);

                //把address对象加入到orders中
                orders.setAddress(address);
                //返回orders
                return orders;
            }

        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> queryItemListByOid(String oid) {
        //我们需要返回一个item集合
        //所以创建集合
        List<Item> itemList=new ArrayList<>();
        //qr c3p0
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        //写sql
        String sql="select i.i_id as iid,i.i_count as icount,i.i_num as inum,i.o_id as oid," +
                "p.p_id as pid,p.p_image as pimage,p.p_info as pinfo,p.p_name as pname,p.p_price as pprice,p.p_state as pstate,p.p_time as ptime" +
                " from item i inner join product p on i.p_id = p.p_id where i.o_id=?";

        try {
            //qr放入sql,MapList，oid
            List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), oid);
            //判断qr结果集
            //不等于空的话，遍历它
            if(query!=null){

                for (Map<String, Object> map : query) {
                    //实现item实体类
                    Item item=new Item();
                    //实例商品实体类
                    Product product=new Product();
                    //BeanUtils放入

                    BeanUtils.populate(item, map);
                    BeanUtils.populate(product, map);

                    //设置item的商品属性，然后把商品放入item
                    item.setProduct(product);
                    //把订单加入集合里

                    itemList.add(item);
                }
            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //把集合返回出去
        return itemList;
    }

    @Override
    public Orders getOrdersByOid(String oid) {
        //qr c3p0
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        //写sql
        String sql="select o_id as oid,u_id as uid,a_id as id ,o_count as ocount,o_time as otime ,o_state as ostate  from orders where o_id=?";

        try {
            //调用qr 放入sql,调用map处理 ,oid

            Map<String, Object> query = queryRunner.query(sql, new MapHandler(), oid);

            //判断 qr 不等于空的话
            if(query!=null){
                //订单实体类
                Orders orders=new Orders();
                BeanUtils.populate(orders, query);

                return orders;
            }

        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderStateByOid(String oid) {

        String sql="update orders set o_state=2 where o_id=?";

        CommonUtils.commonUpdate(sql, oid);
    }
}
