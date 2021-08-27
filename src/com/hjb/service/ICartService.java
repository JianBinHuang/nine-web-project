package com.hjb.service;

import com.hjb.entity.Cart;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:42
 */
public interface ICartService {
    void addCart(int uid, Integer pid);

    List<Cart> showCart(int uid);

    void delete(Integer cid);

    void update(Integer cid, Integer cnum, Integer price);

    void clear(Integer uid);
}
