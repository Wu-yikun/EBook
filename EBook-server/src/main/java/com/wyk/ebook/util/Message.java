package com.wyk.ebook.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 阿里云短信发送服务：需要aliyun-java-sdk-core依赖
 */
public class Message {
    public static String sendSMS(String phoneNumber) throws ServerException, ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FgaN63SWd6spjCp81WK", "kiD9siQpy3wt3v1QrKVKsg4WZQa4yQ");
        IAcsClient client = new DefaultAcsClient(profile);

        String code = RandomNumber.generate(6);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "LBook");
        request.putQueryParameter("TemplateCode", "SMS_173343759");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());

        JSONObject json = JSONObject.parseObject(response.getData());
        String Code = json.getString("Code");

        if (Code.equals("OK")) {
            return code;
        } else {
            return "发送失败";
        }
    }
}
