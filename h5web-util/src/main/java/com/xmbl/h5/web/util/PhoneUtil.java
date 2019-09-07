package com.xmbl.h5.web.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class PhoneUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(PhoneUtil.class);
	
	private static final String encoding = "UTF-8";
	private static final String appId = "8aaf07085dcad420015dcff42f4f01e8";
	private static final String serviceUrl = "http://120.92.210.53:8082/api/sms/send";
	
	/**
	 * 短信发送工具
	 * @param mobiles 手机号列表
	 * @param templateId sms短信模板id
	 * @param params 模板参数数组
	 * @return bool 调用结果
	 */
	public static final boolean sendText(List<String> mobiles, int templateId, String... params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", appId);
        jsonObject.put("templateId", String.valueOf(templateId));
        
        StringBuilder moBuilder = new StringBuilder();
        if (Objects.nonNull(mobiles)) {
			for (String string : mobiles) {
				moBuilder.append(string+",");
			}
		}
        jsonObject.put("mobiles", moBuilder.toString());
        
        StringBuilder paramsBuilder = new StringBuilder();
        if (Objects.nonNull(params)) {
			for (String string : params) {
				paramsBuilder.append(string+",");
			}
		}
        jsonObject.put("params", paramsBuilder.toString());
        
        String content = jsonObject.toJSONString();
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("jsonData", content));
        
        HttpPost httpPost = new HttpPost(serviceUrl);
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvp, encoding));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
  
        CloseableHttpClient client = HttpClients.createDefault();  
		try {
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
	        if (Objects.nonNull(entity)) {
	        	String body = EntityUtils.toString(entity, "UTF-8");
	        	LOGGER.info("短信服务调用完成:{}", body);
	        	EntityUtils.consume(entity); 
		        response.close();
	        	JSONObject result = JSONObject.parseObject(body);
	        	int status = result.getIntValue("status");
	        	if (status == 0) {
					return true;
				}
	        }
		} catch (Exception e) {
			LOGGER.error("调用短信服务失败", e);
		}
		return false;
	}
}
