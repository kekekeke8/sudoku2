package com.huiqu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HuiquService {
//	private JSONObject parseString2JSON(String jsonString) {
//		try {
//			return new JSONObject(jsonString);
//		} catch (JSONException e) {
//			Log.d("D", e.getMessage());
//			return null;
//		}
//	}

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

//	
//
//	private String convertStreamToString(InputStream is) {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		StringBuilder sb = new StringBuilder();
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb.toString();
//	}
}
