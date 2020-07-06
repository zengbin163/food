package com.chihuo.food.application;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

/**
 * 微信小程序应用层
 * 
 * @author zengbin
 *
 */
public class MiniProgramApplication {

    private static final String REQ_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APP_ID = "appid";
    private static final String SECRET = "secret";
    private static final String JS_CODE = "js_code";
    private static final String GRANT_TYPE = "grant_type";

    public void login(String code) {
        Map<String, Object> FORM_MAP = new HashMap<String, Object>();
        FORM_MAP.put(APP_ID, "wxc2c1f522f68a3014");
        FORM_MAP.put(SECRET, "7f6ffa8f5d0809382473baf9c95ae2f1");
        FORM_MAP.put(JS_CODE, code);
        FORM_MAP.put(GRANT_TYPE, "authorization_code");
        String result = HttpRequest.post(REQ_URL).header(Header.USER_AGENT, "Hutool http")// 头信息，多个头信息多次调用此方法即可
            .form(FORM_MAP)// 表单内容
            .timeout(20000)// 超时，毫秒
            .execute().body();
        Console.log(result);
    }
    
    public static void main(String[] args) {
        String jsCode = "123456";
        MiniProgramApplication application = new MiniProgramApplication();
        application.login(jsCode);
    }

}
