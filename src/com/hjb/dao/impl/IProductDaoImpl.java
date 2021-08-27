package com.hjb.dao.impl;

import com.hjb.dao.IProductDao;
import com.hjb.entity.Product;
import com.hjb.util.CommonUtils;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:22
 */
public class IProductDaoImpl implements IProductDao {
    @Override
    public int getTotalCountByTid(Integer tid) {

        String sql="select count(*) from product where t_id=?";

        return CommonUtils.count(sql, tid);
    }

    @Override
    public List<Product> getProductListPageByTid(Integer tid, int pageIndex, int pageSize) {

        String sql="select * from product where t_id=? limit ?,?";

        return CommonUtils.commonQuery(sql, Product.class,tid,pageIndex,pageSize);
    }

    @Override
    public Product getProductById(Integer pid) {

        String sql="select * from product where p_id =?";

        return CommonUtils.querySingleInstance(sql, Product.class,pid);
    }
}
