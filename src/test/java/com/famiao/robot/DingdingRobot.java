package com.famiao.robot;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

public class DingdingRobot {
    
    /**
     * 生成sign签名
     * @throws Exception
     */
    public String sign(Long timestamp) throws Exception {
        String secret = "SEC2357b8c2bfd76787652a1f38c1daf919e331a790bdde236940375e0d7ce332f2";
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return sign;
    }
    
    public OapiRobotSendResponse push() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String sign = sign(timestamp);
        String requestUrl = "https://oapi.dingtalk.com/robot/send?access_token=27a496bb370ebb6daf8c99f7612741e492e6be7d0876688951bb30679cc17a6e&timestamp="+ timestamp +"&sign=" + sign;
        DingTalkClient client = new DefaultDingTalkClient(requestUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        /*        
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("我是来自法苗网的机器人哦，请大家时刻留意我的消息哦【监控报警】");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("18867102687"));
        at.setIsAtAll(true);
        request.setAt(at);
*/
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("http://www.famiaowang.com/");
        link.setPicUrl("");
        link.setTitle("法苗网监控");
        link.setText("我是来自法苗网的机器人哦，请大家时刻留意我的消息哦【监控报警】");
        request.setLink(link);

/*
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("法苗网监控");
        markdown.setText("#### 线上服务器监控报警 @曾斌\n" +
                "> 服务器47.115.34.118系统异常\n\n" +
                "> ![screenshot](http://www.famiaowang.com/img/Contact-logo.c1c16387.png)\n"  +
                "> ###### NullPointerException [空指针](https://m.famiaowang.com/) \n");
        request.setMarkdown(markdown);
*/

        return client.execute(request);
    }

    public static void main(String[] args) throws Exception {
        DingdingRobot robot = new DingdingRobot();
        OapiRobotSendResponse response = robot.push();
        StringBuffer sb = new StringBuffer();
        sb.append("code=" + response.getCode());
        sb.append(",body=" + response.getBody());
        sb.append(",errmsg=" + response.getErrmsg());
        sb.append(",errorCode=" + response.getErrorCode());
        sb.append(",message=" + response.getMessage());
        sb.append(",msg=" + response.getMsg());
        System.out.println(sb.toString());
    }

}
