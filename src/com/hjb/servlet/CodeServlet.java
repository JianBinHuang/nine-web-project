package com.hjb.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 11:33
 */
@WebServlet(urlPatterns = "/code")
public class CodeServlet extends BaseServlet{

    /**
     *
     * 		//2.点击验证码 跟新验证码
     * 		$("#pagecode").click(function(){
     * 			$("#pagecode").attr("src","code?method=createCode&r="+Math.random());
     *                });
     *
     *                这里能get到
     * 生成验证码，返回给前端页面
     *
     * 因为这个是响应到前端页面，所以这个不需要返回值
     */
    public void createCode(HttpServletRequest request, HttpServletResponse response)throws IOException {

        //通过Validatecode 工具集创建一个对象  给4个参数 100,35,4,10
        ValidateCode validateCode=new ValidateCode(100,35,4,10);
        //得到验证码，getcode
        String code = validateCode.getCode();
        //把验证码存起来，通过request
        request.getSession().setAttribute("code", code);
        //响应给前端页面，outputstream
        validateCode.write(response.getOutputStream());

    }
}
