package com.hjb.filter;

import com.hjb.entity.User;
import com.hjb.service.IUserService;
import com.hjb.service.impl.UserServiceImpl;
import com.hjb.util.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 15:41
 */
@WebFilter(urlPatterns = "/login.jsp")
public class AutoLoginFilter implements Filter {

    private IUserService iUserService=new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //把两个原属于filter的请求跟响应转成servlet的请求跟响应
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //然后通过一个字符串变量来保存cookie值
        String value=null;

        //得到浏览器保存的所有cookie
        //通过请求获取所有，然后判断
        Cookie[] cookies = request.getCookies();

        //如果cookie不为空的话，那直接遍历所有cookie
        if(cookies!=null){

            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("autoUser")){
                    value=cookie.getValue();
                }
            }


            //对值进行解码(首先值得存在)
            //所以判断
            if(value!=null){
                //对值解码
                String userStr= Base64Utils.decode(value);

                //然后切割
                String[] strings = userStr.split(":");
                //获取name 和password
                String name=strings[0];
                String password=strings[1];
                //通过业务层查看name,password
                User user = iUserService.checkLogin(new User(name, password));
                //然后判断业务层的内容如果不为空的话
                    if(user!=null){
                        //请求session设置attr key 为loginUser
                        //响应重定向到index.jsp
                            request.getSession().setAttribute("loginUser", user);
                            response.sendRedirect("index.jsp");
                    }else{
                        //如果为空直接放行
                        filterChain.doFilter(request, response);
                    }
            }else{
                //如果为空直接放行
                filterChain.doFilter(request, response);
            }
        }else{

            //放行，访问的login.jsp
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
