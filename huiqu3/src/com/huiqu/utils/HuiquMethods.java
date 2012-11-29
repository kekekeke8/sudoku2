package com.huiqu.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Handler;


public class HuiquMethods {
	private HuiquService service;
	private HuiquConfig config;
	
	public HuiquMethods(HuiquService service,HuiquConfig config){
		this.service = service;
		this.config = config;
	}
	public void login(String login_id, String login_password, final HuiquServiceHandler  callBackHandler) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("m", "login"));
		params.add(new BasicNameValuePair("login_id", login_id));
		params.add(new BasicNameValuePair("login_password", login_password));
		service.call(params,callBackHandler);
	}
	
	public void getschools(final HuiquServiceHandler  callBackHandler) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("m", "schools"));
		service.call(params,callBackHandler);
	}
	public void regist(String regist_email,String regist_password,String regist_school,String work_no,String work_pass, final HuiquServiceHandler  callBackHandler){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("m", "regist"));
		params.add(new BasicNameValuePair("regist_email", regist_email));
		params.add(new BasicNameValuePair("regist_password", regist_password));
		params.add(new BasicNameValuePair("regist_school", regist_school));
		params.add(new BasicNameValuePair("work_no", work_no));
		params.add(new BasicNameValuePair("work_pass", work_pass));
		service.call(params,callBackHandler);
	}
	//notes&page_index=1&page_size=12&login_id=test@huiqu.com&request_type=addnote&note=helloworld!!!!!!!!!!
	public void getNotes(String login_id,int page_index,int page_size,final HuiquServiceHandler callBackhandler){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("m", "notes"));
		params.add(new BasicNameValuePair("regist_id", login_id));
		params.add(new BasicNameValuePair("page_index", Integer.toString(page_index)));
		params.add(new BasicNameValuePair("page_size", Integer.toString(page_size)));
		service.call(params,callBackhandler);
	}
}
