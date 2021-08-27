package com.hjb.service.impl;

import com.hjb.dao.ICartDao;
import com.hjb.dao.IProductDao;
import com.hjb.dao.impl.ICartDaoImpl;
import com.hjb.dao.impl.IProductDaoImpl;
import com.hjb.entity.Cart;
import com.hjb.entity.Product;
import com.hjb.service.ICartService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:42
 */
public class CartServiceImpl implements ICartService {

    private ICartDao cartDao=new ICartDaoImpl();
    private IProductDao productDao=new IProductDaoImpl();

    @Override
    public void addCart(int uid, Integer pid) {

        //判断购物车是否有该商品
        Cart cart =cartDao.hasCart(uid,pid);
        //如果有，则修改购物车商品数量
        if(cart!=null){
            //如果没有，则添加购物车信息
            cart.setCnum(cart.getCnum()+1);
            cartDao.updateCartInfo(cart);
        }else{
            //通过商品id查询商品对象信息
            Product product=productDao.getProductById(pid);
            //如果没有，则添加购物车信息
            Cart newCart=new Cart();
            //set Cart的实体类内容
            newCart.setUid(uid);
            newCart.setPid(pid);
            newCart.setCnum(1);
            newCart.setProduct(product);
            //然后直接添加
            cartDao.addCart(newCart);
        }

    }

    @Override
    public List<Cart> showCart(int uid) {
        return cartDao.showCart(uid);
    }

    @Override
    public void delete(Integer cid) {

        cartDao.delete(cid);
    }

    @Override
    public void update(Integer cid, Integer cnum, Integer price) {
        BigDecimal num=new BigDecimal(cnum);
        BigDecimal prices=new BigDecimal(price);

        //算出小计
        BigDecimal count=num.multiply(prices);

        cartDao.update(cid, cnum, count);
    }

    @Override
    public void clear(Integer uid) {
        cartDao.clear(uid);
    }
}
