package com.shuitianyun.sms;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;


public class SmsSupport {
    private final SmsConfig smsConfig;
    private final SmsClient smsClient;


    public SmsSupport(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;

        // ak、sk等config
        SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(smsConfig.accessKeyId, smsConfig.secretAccessKey));
        config.setEndpoint(smsConfig.endPoint);

        // 实例化发送客户端
        smsClient = new SmsClient(config);
    }

    public boolean send(String mobile, String code){
        // 定义请求参数
        String invokeId = smsConfig.invokeId; // 发送使用签名的调用ID
        String phoneNumber = mobile; // 要发送的手机号码(只能填写一个手机号)
        String templateCode = smsConfig.templateCode; // 本次发送使用的模板Code
        Map<String, String> vars =
                new HashMap<String, String>(); // 若模板内容为：您的验证码是${code},在${time}分钟内输入有效
        vars.put("code", code);
        vars.put("time", "10");

        //实例化请求对象
        SendMessageV2Request request = new SendMessageV2Request();
        request.withInvokeId(invokeId)
                .withPhoneNumber(phoneNumber)
                .withTemplateCode(templateCode)
                .withContentVar(vars);

        // 发送请求
        //#com.baidubce.BceClientException: Unable to execute HTTP request
        //#Caused by: org.apache.http.conn.ConnectTimeoutException: Connect to sms.bj.baidubce.com:80 [sms.bj.baidubce.com/61.135.162.182] failed: connect timed out
        SendMessageV2Response response = smsClient.sendMessage(request);

        // 解析请求响应 response.isSuccess()为true 表示成功
        if (response != null && response.isSuccess()) {
            //  submit success
            return true;
        } else {
            // fail
            return false;
        }
    }

}
