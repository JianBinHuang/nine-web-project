package com.hjb.dao;

import com.hjb.entity.Cart;
import com.hjb.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:43
 */
public interface ICartDao {
    Cart hasCart(int uid, Integer pid);

    void updateCartInfo(Cart cart);
    void addCart(Cart newCart);

    List<Cart> showCart(int uid);

    void delete(Integer cid);

    void update(Integer cid, Integer cnum, BigDecimal count);

    void clear(Integer uid);

}
