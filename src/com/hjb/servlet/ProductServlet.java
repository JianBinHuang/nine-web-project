package com.hjb.servlet;

import com.hjb.entity.PageBean;
import com.hjb.entity.Product;
import com.hjb.service.IProductService;
import com.hjb.service.impl.IProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:11
 */
@WebServlet(urlPatterns = "/product")
public class ProductServlet extends BaseServlet{

    /**
    * @param
    * @Description 调用产品业务层
    */
    private IProductService productService=new IProductServiceImpl();

    /**
     * @Author JianBinHuang
     * @Date 2021/8/25 19:11
     * @param
     * @return
     * @Description 查询该类别下的商品分页信息
     */
    public String queryProductByTypeId(String currentPage, Integer tid, HttpServletRequest request){

        //设置默认的当前页
        int current=1;
        //设置默认显示多少条
        int pageSize=5;

        //判断，当前页不等于空
        if(currentPage!=null){
            //把currentPage包装成Int包装类赋值给默认当前页
            current=Integer.parseInt(currentPage);
        }
        //获取业务层的pagebean方法 把tid , 当前页，设置默认显示页数
        PageBean pageBean = productService.getPageBean(tid, current, pageSize);
        //请求获取属性把pagebean包装起来
        request.setAttribute("pageBean", pageBean);

        //转发到页面goodsList.jsp
        return "forward:goodsList.jsp";
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 15:16
     * @param
     * @return
     * @Description 根据商品id查询商品信息，跳转到商品详情页进行展示
     */
    public String getProductById(Integer pid,HttpServletRequest request){

        Product product=productService.getProductById(pid);
        request.setAttribute("goods", product);

        return "forward:goodsDetail.jsp";
    }
}
