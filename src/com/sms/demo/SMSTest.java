package com.sms.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSTest {
    //第三方平台接口：http://sms.cn/sms_api.html，云信
    private static final String addr = "http://api.sms.cn/sms/";
    private static final String userId = "w3378883500";
    /*
     * 如uid是：w3378883500，登录密码是：k26f24
     * pwd=md5(k26f24),即pwd=20b47d8276c762db74b7430e06bad173
     */
    private static final String pwd = "20b47d8276c762db74b7430e06bad173";
    private static final String encode = "utf8";

    public static void send(String msgContent, String mobile) throws Exception {
        //组建请求
        String straddr = addr +
                "?ac=send&uid=" + userId +
                "&pwd=" + pwd +
                "&mobile=" + mobile +
                "&encode=" + encode +
                "&content=" + msgContent;

        StringBuffer sb = new StringBuffer(straddr);
        System.out.println("URL:" + sb);
        //发送请求
        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        //返回结果
        String inputline = in.readLine();
        System.out.println("Response:" + inputline);
    }
    public static void main(String[] args) {
        try {
            //全文发送路径：http://api.sms.cn/sms/?ac=send&uid=w3378883500&pwd=20b47d8276c762db74b7430e06bad173
            // &mobile=15100317880&encode=utf8&content=你好！您的验证码：{8888}。如非本人操作，可不用理会！【河北汉佳】
            send("你好！您的验证码：{8888}。如非本人操作，可不用理会！【河北汉佳】", "15100317880");
            //json发送路径：http://api.sms.cn/sms/?ac=send&uid=w3378883500&pwd=20b47d8276c762db74b7430e06bad173
            // &template=492675&mobile=15100317880&content={"name":"test","code":"8888"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
