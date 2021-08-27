package com.hjb.dao.impl;

import com.hjb.dao.ICartDao;
import com.hjb.entity.Cart;
import com.hjb.entity.Product;
import com.hjb.util.C3P0Utils;
import com.hjb.util.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:43
 */
public class ICartDaoImpl implements ICartDao {
    @Override
    public Cart hasCart(int uid, Integer pid) {

        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());

        String sql="select c.c_id as cid,c.p_id as pid,c.u_id as uid, c.c_num as" +
                "    cnum,c.c_count as ccount, p.t_id as tid ,p.p_image as pimage,p.p_info as pinfo,p.p_name as pname" +
                ",p.p_price as pprice,p.p_state as pstate,p.p_time as ptime " +
                "from cart c inner join product p on c.p_id = p.p_id where c.u_id=? and c.p_id=?;";
        //得到查询的结果，结果会放到map中
        try {
            Map<String,Object> map=queryRunner.query(sql,new MapHandler(),uid,pid);
            if(map!=null){
                //创建两个空的对象
                Cart cart=new Cart();
                Product product=new Product();
                //赋值
                BeanUtils.populate(cart, map);
                BeanUtils.populate(product, map);

                cart.setProduct(product);

                return cart;
            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateCartInfo(Cart cart) {

        String sql="update cart set c_count=?,c_num=? where u_id=? and p_id=?";

        CommonUtils.commonUpdate(sql,cart.getCcount(),cart.getCnum(),cart.getUid(),cart.getPid());
    }

    @Override
    public void addCart(Cart newCart) {
        String sql="insert into cart values(null,?,?,?,?)";
        CommonUtils.commonUpdate(sql,newCart.getUid(),newCart.getPid(),newCart.getCcount(),newCart.getCnum());
    }

    @Override
    public List<Cart> showCart(int uid) {

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
    public void delete(Integer cid) {

        String sql="delete from cart where c_id=?";

        CommonUtils.commonUpdate(sql, cid);
    }

    @Override
    public void update(Integer cid, Integer cnum, BigDecimal count) {
        String sql="update cart set c_num=?,c_count=? where c_id=?";

        CommonUtils.commonUpdate(sql,cnum,count,cid);
    }

    @Override
    public void clear(Integer uid) {
        String sql="delete from cart where u_id=?";

        CommonUtils.commonUpdate(sql, uid);
    }
}
