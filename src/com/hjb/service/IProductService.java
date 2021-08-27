package com.hjb.service;

import com.hjb.entity.PageBean;
import com.hjb.entity.Product;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:21
 */
public interface IProductService {

    /**
     * getPageBean 获取页数
     * @param tid
     * @param current
     * @param pageSize
     * @return
     */
    PageBean getPageBean(Integer tid,int current,int pageSize);

    Product getProductById(Integer pid);
}
