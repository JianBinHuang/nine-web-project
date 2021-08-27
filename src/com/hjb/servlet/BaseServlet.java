package com.hjb.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 11:36
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.得到method请求参数的值 login
        String methodName = request.getParameter("method");

        //判断methodName 如果不为空的话
        if(methodName!=null){
            //找到login方法对应的method对象
            //通过返回值method 写一个getMethodByName 来获取methodName
            Method methodByName = getMethodByName(methodName);
            //判断获取到的返回值方法如果不为空的话
            if(methodByName!=null){
                commonOper(methodByName, request, response);
                //做一些调用方法前的相关操作
                //创建方法commonOper();
            }else{
                System.out.println("类中没有这个方法"+ methodByName.getName());
            }

        }else{
            System.out.println("没有携带method参数值"+methodName);
        }


    }

    private void commonOper(Method method,HttpServletRequest request,HttpServletResponse response){

        //得到方法参数个数
        //调用method 的paramcount
        int parameterCount = method.getParameterCount();
        //定义一个object数组来装方法参数值
        Object[] paramValueArray=new Object[parameterCount];
        //参数的操作，准备好方法参数值，以便后面调用时给方法参数赋值
        //创建paramOper();
        paramOper(method, paramValueArray, request, response);

        try {
            //method 调用方法
            Object result = method.invoke(this, paramValueArray);
            //方法的内容不空
            if (result != null) {
                //跳转视图
                responseToView(result.toString(), request, response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    private void responseToView(String result,HttpServletRequest request,HttpServletResponse response){
        Map<String,String> strMap=strSplit(result);
        String type=strMap.get("type");
        String view=strMap.get("view");

        if("forward".equals(type)){
            //转发的跳转方式
            try {
                request.getRequestDispatcher(view).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if("redirect".equals(type)){
            //重定向跳转
            try {
                response.sendRedirect(view);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String,String> strSplit(String result){

        //创建一个Map
        Map<String,String> strMap=new HashMap<>();

        //切割字符串
        String[] strings=result.split(":");
        //从键值对的  键进行:分隔
        strMap.put("type", strings[0]);
        //从键值对的 值 进行:分隔
        strMap.put("view", strings[1]);
        return strMap;
    }



    private void paramOper(Method method,Object[] paramValueArray,HttpServletRequest request,HttpServletResponse response){

        //得到方法的所有参数
        //通过method，调用方法里面的所有参数s，为一个参数数组
        Parameter[] methodParameters = method.getParameters();
        //通过spring内核的一个localVaariableTableParameterNameDiscoverer
        //查找局部变量用的
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer=new LocalVariableTableParameterNameDiscoverer();
        //然后通过spring内核调用getParam的name数组放入第一个参数列表
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        //遍历所有方法的参数

        for (int i = 0; i < methodParameters.length; i++) {
            //通过变量了所有方法的参数获取type再获取simplename
            String simpleName=methodParameters[i].getType().getSimpleName();
            //判断HttpServiceRequest，HttpServletResponse,String是否都有在simpleName里面
            if("HttpServletRequest".equals(simpleName)){
                paramValueArray[i]=request;
            }else if("HttpServletResponse".equals(simpleName)){
                paramValueArray[i]=response;
            }else if("String".equals(simpleName)){
                //String判断的话，需要得到当前下标的形参名称 (把从spring那边的获取局部变量的方法以数组的形式存入parameterName)
                String parameterName=parameterNames[i];
                //通过形参名称得到同样名称的请求参数的值
                String value = request.getParameter(parameterName);
                //把参数值放在数组当前下标下
                paramValueArray[i]=value;
            }else if("Integer".equals(simpleName)) {
                //integer判断simpleName是否有integer值的存在 (把从spring那边的获取局部变量的方法以数组的形式存入parameterName)
                String parameterName=parameterNames[i];
                //得到当前下标的形参的名称
                //通过形参名称得到同样名称的请求参数的值
                String value=request.getParameter(parameterName);
                //把参数值放在数组当前下标下  把value数组转换成integer
                paramValueArray[i]=Integer.parseInt(value);
            }else{
                //else
                //形参是对象
                try {
                    Object instance = methodParameters[i].getType().newInstance();
                    //这个map中放着请求参数的名称和值，name=zs&age=19
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    BeanUtils.populate(instance,parameterMap);
                    //把对象放到数组中
                    paramValueArray[i]=instance;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Author JianBinHuang
     * @Date 2021/8/24 11:41
     * @Description 找到login方法对应的method对象
     */
    private Method getMethodByName(String methodName){

        //得到子类中所有的方法
        //this代表子类
        Method[] methods = this.getClass().getMethods();
        //然后遍历获取
        for (Method method : methods) {
            //比较名称，如果一直返回method对象

            if(method.getName().equalsIgnoreCase(methodName)){
                return method;
            }
        }
        return null;
    }
}
