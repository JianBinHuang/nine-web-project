package com.hjb.servlet;

import com.hjb.entity.Address;
import com.hjb.entity.User;
import com.hjb.service.IAddressService;
import com.hjb.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 19:27
 */
@WebServlet("/address")
public class AddressServlet extends BaseServlet{


    private IAddressService addressService=new AddressServiceImpl();


    /**
     * 查询当前用户登录的所有收获地址
     */
    public String show(HttpServletRequest request){

        //请求调用session的loginUser属性

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        //然后判断loginUser是否为空，如果等于空
        if(loginUser==null) {
            //转发到login.jsp
            return "forward:login.jsp";
        }
        //得到用户id
        int uid = loginUser.getUid();
        //根据登录用户Id查询地址
        List<Address> addressList = addressService.queryAllAddressByUid(uid);
        //然后请求属性 addressList
        request.setAttribute("addressList",addressList);
        return  "forward:self_info.jsp";
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 20:01
     * @param
     * @return
     * @Description 添加用户收获地址
     */
    public String add(Address address){
        addressService.add(address);

        return "forward:address?method=show";
    }


    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 19:55
     * @param
     * @return
     * @Description 修改地址信息
     */
    public String update(Address address){
        addressService.update(address);
        return "forward:address?method=show";
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 20:31
     * @param
     * @return
     * @Description删除
     */
    public String delete(Integer aid){

        addressService.deleteById(aid);

        return "forward:address?method=show";

    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/26 20:40
     * @param
     * @return
     * @Description 设置默认地址
     */
    public String setDefault(Integer aid,HttpServletRequest request){

        //获取用户登录的session
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        //判断是否等于空，等于空的直接跳转到Login.jsp

        if(loginUser==null){
            return "forward:login.jsp";
        }
        //得到uid
        int uid = loginUser.getUid();
        //设置默认地址uid跟aid
        addressService.setDefault(uid, aid);
        //转发到address?method=show

        return "forward:address?method=show";
    }
}
