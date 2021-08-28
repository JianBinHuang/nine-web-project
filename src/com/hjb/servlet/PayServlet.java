package com.hjb.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hjb.entity.Orders;
import com.hjb.service.IOrderService;
import com.hjb.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/27 17:21
 */

@WebServlet(urlPatterns = "/pay")
public class PayServlet extends BaseServlet {

    /**
     * 业务层
     */
    private IOrderService orderService = new OrderServiceImpl();


    /**
     * 支付宝支付
     * @param oid
     * @param httpResponse
     * @throws IOException
     */
    public void aliPay(String oid,HttpServletResponse httpResponse) throws IOException {
        //获得初始化的AlipayClient

        Orders orders= orderService.getOrdersByOid(oid);
        /**
         * @Author JianBinHuang
         * @Date 2021/8/27 19:04
         * @param
         * @return
         * @Description 支付宝支付的核心类
         */
        AlipayClient alipayClient =  new  DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do"
                , "2021000118609569"
                ,"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXqPZkqq5ECHQ5UPM8Hq5IaDmy9PVOwPGB06Cl/BtgP4XcthZKe73pGyrc1xX/dW2jwCjlREk2bErp2QOFPkzXGbzaJf4PZPbFOiPqPs0UR/fzGrpaep7iqtYhOLFmwTI/VWa3rT0aak8yKS1QDx5wfzRKTa8TaK0TBDy6VD/6v2hULh6wdhPJd6FSR/GIXGZ5lOUkIibVbvnDVy4pCd5aMt319B/7Dq/5kYCPxTt8yaM35vDB9SbaybXZac/RW4v9IS5Ty/uDtuhiPnmyMxA7MsKydVqlINlayEb+/iNzpJEViLeEjCOAqPVooKfcdzvKFitwzWJQkGGt3gyKu9kxAgMBAAECggEAMW3wYH8ArUvUFZo8rKuTQiY2KA2OpkxcamP/SBoTEhuiFX2DZmNOUlt4bOHtWYV8ZpKKwa6v7fz9rZn9e8ACa1kIJFGdrIbLpxx7+Czfymxqc+GSO0sb3eSwRcOkjkkJh3reL59W+vULOt4Q1rcWxLOw50bKeRj0z+3hnRk4gRDABe2fGt5UIceNTx5xOsIOHxxE7+vsXkR6R74JnRLfaVHDpy6K6rHkEJk9D4L2Yc0/ZpW9tg4MXpzWy3IQs8giqwwOdSdWXmakiyKV/mQr2WYLt7HfZY7cFZJQjT1/mjxLpAPvzxraDssv0YWy+jQbBqfqbnPtFZAZxfXGccVXwQKBgQDfs5vI6WvQXAlIV3ZCEZOCyZrBr6fv4tdS4XjwhxRMn80cSizgnNKvDU3xr8xIm+RWdKZpYxNIEHs8eB1SLc+Vnxs+HOfdFc2EQOyT/r2r6pftAlomOpOSO1uDTjmnBy/G5T/yL5DM8/nj/6yQYMsZxIPhi5xcf4oS+/gFZ7HzLwKBgQCtjpMKso1wfWYc4Pt8J1tJsJ6ezXGhQKDyGP7S4XlZx51nPqPwQIy/4uZkAenruezDm7AU8KvDysV+EyVq99C6Hcx887SdsH0C4umQ7mYevCjeJwbLDP5ljz2Em1BRL1V2Yrjnnt+dYqAgmTYA20ahB6VCiDUaabMXZTMomr9hnwKBgGzzfklRHniBiufX6Tnuc4i8hgoVclaMhdDRDeUf9QwIvI4ttkPDPX2Snka7F2S5wHE3KSGk5mwv0sW1vpoaw8KcllkfHPJ5vvnN4SuHY3uKq2n8vklmOB99l2mRPNUT0/osB4P+FKktnqSkDrjW27R2GMFODN6kI2xxme+P/IRzAoGAa7w3VsqjthKBkqeuNNGRf1rd/CH3yWt+/Qm7FVjVpX+eG/rsdTT8qhjFf4sYlDWj3YEFetozJaT3l113OsF+lintSBtYIRLvZx1Ic6kpRybyD4UWWHFGcfDK9UgcQWkqmgSKV21tzhrs34970GhNnaRLV9OJc03t2DX/3dLBvm0CgYEAoKo7l7Qqe3AajRty695U2udbi9XoZeSwhaNcGqLOnZOQT9urg07vbFZuCje1BwO4RizjSZ4KHH2dhtNn4Ul5pCuskeR8ACIIKrzr1cjzuQIYLc7z19NgOHHW29KQbtDu0i6AMpeiuOl5QMVmJj+NeigAq6ljqTPQ8GtoXGoN/WU="
                , "json"
                , "utf-8"
                ,"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAif2q6TjM/3WnRVed3qwcCUFRqt8dvI7bvcUZSZ0P99vjZTeeJD1af0iTiLGbwzdCPMLp0mdtxVzM4vNEQ4GScrsR7SvCkpG1xqNHF6FDj54cMF9+tvmxN7DsurLN2Xygq2R0GnQD/qFcv+5YT/bnc/I9MFq3DSqwtmmrAUSnnqc1guosZgOesRmwkO3D6ahTmaWDj/7zoGeMbDvwJ3ckj9dj4PIyW8E1Mys3hljyV9jv/I+JowR9RxzobDsEOY/wdFaLEtTyj+13Qv8Dt/qGc5UDXyh/hzDy/H2LOqJMe3IKOSa+/+T0p4+IJf+Z2f0y3/jqV2rTMMyhUY33ycFN8QIDAQAB"
                , "RSA2");
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest();
        //用户支付完成后，支付宝同步通知结果的地址
        alipayRequest.setReturnUrl( "http://localhost:8080/pay?method=payCallback&oid="+orders.getOid()+"&uid="+orders.getUid() );
        //在公共参数中设置回跳和通知地址
        //用户支付完成后，支付宝异步通知结果的地址
//        alipayRequest.setNotifyUrl( "http://www.baidu.com" );
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+orders.getOid()+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+orders.getOcount().doubleValue()+","  +
                "    \"subject\":\""+orders.getOid()+"\","  +
                "    \"body\":\""+orders.getOid()+"\"}"); //填充业务参数
        String form= "" ;
        try  {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        //直接将完整的表单html输出到页面
        httpResponse.setContentType( "text/html;charset=utf-8"  );
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    public String payCallback(String oid,Integer uid){

        //2.已支付， 未发货
        orderService.updateOrderStateByOid(oid);

        return "forward:order?method=showList&uid="+uid;
    }
}