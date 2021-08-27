package com.hjb.dao;

import com.hjb.entity.Product;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:22
 */
public interface IProductDao {

    /**
     * 通过id获取总页数
     * @param tid
     * @return
     */
    int getTotalCountByTid(Integer tid);

    /**
     * 通过id获取页数
     * @param tid
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Product> getProductListPageByTid(Integer tid,int pageIndex,int pageSize);

    Product getProductById(Integer pid);
}
