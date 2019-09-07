package com.xmbl.h5.web.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class AuthUtil {
	public static JSONObject doGetJson(String url) {
		JSONObject jsonObject = null;
		HttpGet httpGet = null;
		try {
			CloseableHttpClient client =  HttpClients.createDefault();
			httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity,"UTF-8");
				jsonObject = JSONObject.parseObject(result);
			}
			httpGet.releaseConnection();
			return jsonObject;
		} catch (Exception e) {
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
			return null;
		}
		
	}
}
