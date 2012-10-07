package com.huiqu.utils;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HuiquService {
	public void call(final String service_url,final List<NameValuePair> params,final Handler callBackHandler) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpPost httpRequest = new HttpPost(service_url);
				Message msg = new Message();
				Bundle data  = new Bundle();
				try {
					httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String strResult = EntityUtils.toString(httpResponse.getEntity());
						data.putInt("ret", 0);
						data.putString("result", strResult);
					} else {
						data.putInt("ret", httpResponse.getStatusLine().getStatusCode());
						data.putString("result", httpResponse.getStatusLine().toString());
					}
				} catch (Exception e) {
					data.putInt("ret", 9999);
					data.putString("result", e.toString());
				}
				msg.setData(data);
				callBackHandler.sendMessage(msg);
			}
		}).start();
	}
}