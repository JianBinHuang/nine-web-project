package com.hjb.servlet;

import com.google.gson.Gson;
import com.hjb.entity.Type;
import com.hjb.service.ITypeService;
import com.hjb.service.impl.TypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 16:18
 */
@WebServlet(urlPatterns = "/type")
public class TypeServlet extends BaseServlet{

    /**
     * @Author JianBinHuang
     * @Date 2021/8/25 16:24
     * @param
     * @return
     * @Description    //调用类别业务层
     */
    private ITypeService typeService=new TypeServiceImpl();

    /**
     * @Author JianBinHuang
     * @Date 2021/8/25 16:24
     * @param
     * @return
     * @Description 查询所有的商品类别信息
     */
    public void queryAllTypes(HttpServletResponse response) throws IOException {
        //通过类别业务层来调用方法
        List<Type> types = typeService.queryAllType();
        //然后通过Gson把业务层的属性转换成json数据
        String json=new Gson().toJson(types);
        //然后响应获取写入json数据
        response.getWriter().write(json);
    }
}
