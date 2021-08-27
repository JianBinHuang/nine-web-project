package com.hjb.servlet;

import com.google.gson.Gson;
import com.hjb.entity.User;
import com.hjb.service.IUserService;
import com.hjb.service.impl.UserServiceImpl;
import com.hjb.util.Base64Utils;
import com.hjb.util.Constants;
import com.hjb.util.MD5Utils;
import com.hjb.util.RandomUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 11:37
 */
@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet{

    private IUserService userService=new UserServiceImpl();

    public void show(HttpServletResponse response)throws Exception{
        //查询到所有的用户信息
        User user = userService.getUserById();
        //把集合转成json
        String json=new Gson().toJson(user);
        //响应到浏览器
        response.getWriter().write(json);
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/24 16:01
     * @param
     * @return
     * @Description 注册用户的用户名唯一检验
     */
    public void checkNameIsExits(String username,HttpServletResponse response)throws IOException {
        if(username==null){
            response.getWriter().write(Constants.USERNAME_EXISTS);
        }
        //查询数据库，判断是否有该用户名
        boolean isExits = userService.checkUserName(username);

        if(isExits){
            //有这个用户名
            response.getWriter().write(Constants.USERNAME_EXISTS);
        }else{
            //没有这个用户名
            response.getWriter().write(Constants.USERNAME_NOT_EXISTS);
        }

    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/24 17:41
     * @param
     * @return
     * @Description 用来接收表单提交的数据
     */
    public String regist(User user){

        /**
         * 用户名 密码 邮箱 性别
         * 给装填 激活码，角色赋默认值
         */
        /*
        0是未激活，1是已激活
         */
        user.setUstatus(Constants.USER_NOT_ACTIVE);

        user.setUcode(RandomUtils.createActive());

        /*
        0是普通用户，1是管理员
         */
        user.setUrole(Constants.USER_CUSTOMER);

        //吧密码加密
        user.setUpassword(MD5Utils.md5(user.getUpassword()));

        //注册，把数据加到数据库
        int register = userService.register(user);
        if(register>0){
            //注册成功
            return "forward:registerSuccess.jsp";
        }else{
            //注册失败
            return  "forward:register.jsp";
        }
    }
    public String activate(String code,HttpServletRequest request){
        //激活首先要对激活码进行解码
        String activeCode = Base64Utils.decode(code);

        //激活  0表示激活失败 1表示已经激活过了，2表示激活成功
        int active = userService.active(activeCode);
        //提示信息
        if(active==Constants.ACTIVE_FAIL){
            request.setAttribute("msg", "激活失败");
        }else if(active==Constants.ACTIVE_ALREADY){
            request.setAttribute("msg", "已经激活过了");
        }else if(active==Constants.ACTIVE_SUCCESS){
            request.setAttribute("msg", "激活成功");
        }

        return "forward:message.jsp";
    }

    /**
     * 用户的登录
     * @param user
     * @param code
     * @param request
     * @return
     */
    public String login(User user,String code,HttpServletRequest request,HttpServletResponse response){


        //获取自动登录的前端项
        String auto = request.getParameter("auto");

        //得到生成的验证码，从codeServlet获取Attribute
        String checkCode = (String) request.getSession().getAttribute("code");
        //比较验证码是否一致
        //判断 生成的验证码跟code 不一致则
        //直接返回给前端验证码不正确的msg
        //retuen 转发给login.jsp
        if(!checkCode.equals(code)){
            request.setAttribute("msg", "验证码不一致");
            return "forward:login.jsp";
        }

        //登录
        //通过业务层调用检查登录的方法 然后检查user
        User loginUser = userService.checkLogin(user);
        //判断，如果user为空，则通过请求msg响应到前端页面错误信息
        //然后转发到login.jsp
        if(loginUser==null){
            request.setAttribute("msg", "用户名或密码错误");
            return "forward:login.jsp";
        }

        //判断用户是否已经激活了
        //通过登录的业务层调用status和常量区的未激活状态码判断,
        //如果为true，则已经激活
        //激活则通过请求msg响应信息
        //然后直接转发到login.jsp
        if(loginUser.getUstatus().equals(Constants.USER_NOT_ACTIVE)){
            request.setAttribute("msg", "未激活");
            return "forward:login.jsp";
        }

        //保存登录用户
        //请求获取session 设置属性
        //转发到index.jsp
        request.getSession().setAttribute("loginUser", loginUser);


        //自动登录
        if(auto!=null){

            //勾选了自动登录，获取用户名密码, 通过形参列表获取password跟name
            String content=user.getUname()+":"+user.getUpassword();
            //然后进行编码
            String encode = Base64Utils.encode(content);
            //创建cookie
            Cookie cookie=new Cookie("autoUser",encode);
            //创建cookie的生命周期 为14天
            cookie.setMaxAge(60*60*24*14);
            //设置cookie路径为/
            cookie.setPath("/");
            //通过响应添加cookie
            response.addCookie(cookie);
        }
        return "forward:index.jsp";
    }
    /**
     * 写一个 logOut方法
     *
     *
     */
    public String logOut(HttpServletRequest request,HttpServletResponse response){

        //1.清除session中保存的用户信息 loginUser
        request.getSession().removeAttribute("loginUser");
        //2.清除cookie
        Cookie cookie=new Cookie("autoUser", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //返回转发到Login.jsp
        return "forward:login.jsp";
    }



}
