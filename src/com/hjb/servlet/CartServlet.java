package com.hjb.servlet;

import com.hjb.dao.impl.ICartDaoImpl;
import com.hjb.entity.Cart;
import com.hjb.entity.User;
import com.hjb.service.ICartService;
import com.hjb.service.impl.CartServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:42
 */
@WebServlet(urlPatterns = "/cart")
public class CartServlet extends BaseServlet{


    ICartService cartService=new CartServiceImpl();

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 15:44
     * @param
     * @return
     * @Description 添加商品到购物车
     */
    public String addCart(Integer pid, HttpServletRequest request){

        //判断是否已经登录了
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {

            //没有登录转发到Login.jsp
            return "forward:login.jsp";
        }
        //已经登录了，得到登录用户的Id
        int uid = loginUser.getUid();

        //添加购物车事件

        cartService.addCart(uid,pid);
        return "forward:cartSuccess.jsp";
    }


    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 15:58
     * @param
     * @return
     * @Description 查看购物车信息
     */
    public String showCart(HttpServletRequest request){

        //获取loginUser的session属性 type User
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        //判断是否已经登录 为空则未登录
        if(loginUser==null) {
            //没有登录的话转发到login.jsp
        return "forward:login.jsp";
        }
        //已经登录了，得到登录用户的id(uid)
        int uid = loginUser.getUid();
        //查询当前登录用户的购物车信息
        //通过业务层获取uid 通过list
        List<Cart> cartList = cartService.showCart(uid);
        //通过请求把业务层获取的信息存进来
        request.setAttribute("cartList",cartList);
        //然后返回到cart.jsp
     return "forward:cart.jsp";
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 16:01
     * @param
     * @return
     * @Description 删除购物车信息
     */
    public String delete(Integer cid){
        //直接通过业务层拿到删除的
        cartService.delete(cid);
        //直接访问cart?method=showCart 然后前端的javascript 的location跳转带这个参数
        return "forward:cart?method=showCart";
    }


    public String update(Integer cid,Integer cnum,Integer price){

        cartService.update(cid, cnum, price);
        //前端调用这个方法，然后返回这个参数
        return "forward:cart?method=showCart";
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 19:17
     * @param
     * @return
     * @Description 情况购物车信息
     */
    public String clear(HttpServletRequest request){

        //请求获取session的属性loginUser
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //然后判断是否有登录，为空没登录
            if(loginUser==null) {
                //然后直接转发到Login.jsp
                return "forward:login.jsp";
            }
        //登录的话，getuid
        int uid = loginUser.getUid();
        //然后直接调用业务层的清除方法
        cartService.clear(uid);
        //然后直接转发到cart?method=showCart
        return "forward:cart?method=showCart";
    }

}
