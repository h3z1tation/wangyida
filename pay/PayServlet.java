package org.YaoLeGouDB.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.YaoLeGouDB.aliconfig.AlipayConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/pay.do")
public class PayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);


        //商户订单号，商户网站订单系统中唯一订单号，必填
        // String out_trade_no = ddxq.getDingDanNumber();

              //付款金额，必填
//        String total_amount =dd.getPrice()*ddxq.getCount()+"元";
//        //订单名称，必填

//       String subject =sif.getShopName();
//        //商品描述，可空
        //String body = sif.getShopMiaoShu();
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =req.getParameter("out_trade_no");
        System.out.println("out_trade_no=" + out_trade_no);
        //付款金额，必填
        String total_amount =req.getParameter("total_amount").substring(1);
        System.out.println("total_amount=" + total_amount);
        //订单名称，必填
        String subject =req.getParameter("subject");
        System.out.println("subject=" + subject);
        //商品描述，可空
        String body =req.getParameter("subject");
        System.out.println("body=" + body);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        //输出
        out.println(result);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}


