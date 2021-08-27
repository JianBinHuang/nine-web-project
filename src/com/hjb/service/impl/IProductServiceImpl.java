package com.hjb.service.impl;

import com.hjb.dao.IProductDao;
import com.hjb.dao.impl.IProductDaoImpl;
import com.hjb.entity.PageBean;
import com.hjb.entity.Product;
import com.hjb.service.IProductService;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:21
 */
public class IProductServiceImpl implements IProductService {

    /**
     * dao层
     */
    private IProductDao productDao=new IProductDaoImpl();

    @Override
    public PageBean getPageBean(Integer tid, int current, int pageSize) {

        //查询类别下的商品有多少个(总条数)
        int totalCountByTid = productDao.getTotalCountByTid(tid);
        //获取dao层的总条数方法;
        //算出分页的其实下标 默认当前页数-1 * 总页数
        int pageIndex=(current-1)*pageSize;
        //查询分页的集合
        //通过dao层获取查询分页的集合数据里面传入三个参数(t_id,pageindex,pagesize)
        List<Product> productListPageByTid = productDao.getProductListPageByTid(tid, pageIndex, pageSize);

        //返回 new一个pageBean实体类把值放进去
        return new PageBean(productListPageByTid,current,pageSize,totalCountByTid);
    }

    @Override
    public Product getProductById(Integer pid) {
        return productDao.getProductById(pid);
    }
}
