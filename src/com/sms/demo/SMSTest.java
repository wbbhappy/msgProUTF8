package com.sms.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSTest {
    private static final String addr = "http://api.sms.cn/sms/";
    private static final String userId = "test";
    /*
     * 如uid是：test，登录密码是：123123
     * pwd=md5(123123),即pwd=4297f44b13955235245b2497399d7a93
     * pwd=md5(123123test),即pwd=b9887c5ebb23ebb294acab183ecf0769
     */
    private static final String pwd = "4297f44b13955235245b2497399d7a93";
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
            send("您好！短信测试！", "15100317880");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
