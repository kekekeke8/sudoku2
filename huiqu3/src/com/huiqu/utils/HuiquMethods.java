package com.huiqu.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;


public class HuiquMethods {
	private Huiqu huiqu;
	private HuiquService service;
	private HuiquConfig config;
	
	public HuiquMethods(Huiqu huiqu,HuiquService service,HuiquConfig config){
		this.huiqu = huiqu;
		this.service = service;
		this.config = config;
	}
	private JSONObject to_json(String jsonString) {
		try {
			return new JSONObject(jsonString);
		} catch (JSONException e) {
			return null;
		}
	}
	public void login(String login_id, String login_password, final Handler  callBackHandler) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_id", login_id));
		params.add(new BasicNameValuePair("login_password", login_password));
		service.call(config.getService_url(),params,callBackHandler);
	}
}
