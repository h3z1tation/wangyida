package org.YaoLeGouDB.aliconfig;
import org.YaoLeGouDB.security.SecurityInfo;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    public static String app_id = SecurityInfo.ALIPAY_APP_ID ;
    public static String merchant_private_key =SecurityInfo.MERCHANT_PRIVATE_KEY;
    public static String alipay_public_key = SecurityInfo.ALIPAY_PUBLIC_KEY;
    public static String notify_url = "http://localhost:8080/Alipay_demo_war_exploded/notify_url.jsp";
    public static String return_url = "http://localhost:8080/Alipay_demo_war_exploded/return_url.jsp";
    public static String sign_type = "RSA2";
    public static String charset = "utf-8";
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    public static String log_path = "D:\\";
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

