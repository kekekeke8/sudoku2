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

import android.util.Log;

public class Service {
	private JSONObject parseString2JSON(String jsonString) {
		try {
			return new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.d("D", e.getMessage());
			return null;
		}
	}

	public void test() {
		String uriAPI = "http://192.168.1.103/1399/1399/server/s_login.php";
		HttpPost httpRequest = new HttpPost(uriAPI);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_id", "test@huiqu.com"));
		params.add(new BasicNameValuePair("login_password", "123456"));
		System.out.println(params.toString());
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				Log.d("D",strResult);
			} else {
				Log.d("D","Error Response" + httpResponse.getStatusLine().toString());
			}
		} catch (ClientProtocolException e) {
			Log.d("D",e.getMessage().toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			Log.d("D",e.getMessage().toString());
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("D",e.getMessage().toString());
			e.printStackTrace();
		}catch(Exception e){
			Log.d("D",e.getMessage().toString());
			e.printStackTrace();
		}

	}

	public JSONObject login(String login_id, String login_password) {
		Log.d("D","Hello World!");
		System.out.println("Hello Console.");
		test();
//		try {
//			return callService("login", login_id, login_password);
//		} catch (ClientProtocolException e) {
//			Log.d("D", e.getMessage());
//		} catch (IOException e) {
//			Log.d("D", "login exception:" + e.toString());
//		}
		return null;
	}

	public JSONObject callService(String method, String login_id, String login_password) throws ClientProtocolException, IOException {
		Map<String, String> parmas = new HashMap<String, String>();
		parmas.put("m", method);
		parmas.put("login_id", login_id);
		parmas.put("login_password", login_password);
		return parseString2JSON(dopost(parmas));
	}

	private String dopost(Map<String, String> parmas) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://huiqu.sinaapp.com/server/s_login.php");
		// Application.Instance().config.getService_url());
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		// if (parmas != null) {
		// for (Iterator<String> i = parmas.keySet().iterator(); i.hasNext();) {
		// String key = (String) i.next();
		// pairs.add(new BasicNameValuePair(key, parmas.get(key)));
		// }
		// }
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));

		HttpResponse response = new DefaultHttpClient().execute(httpPost);

		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();

		String returnConnection = convertStreamToString(content);
		return returnConnection;
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
