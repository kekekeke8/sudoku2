package com.huiqu.model;

import org.json.JSONObject;

public abstract class AbstractEntity {
	// "id":"1","login_id":"lirenzhao@gmail.com","name":"Liren","display_name":"Liren Zhao",
	// "login_password":"123456","sex":"Male","mobile":"13911111111","no":"123123123","duty":"Manager",
	// "status":"","birthday":"19770606","photo":"","create_date":"2012-07-13 00:00:00",
	// "last_login":"2012-07-13 00:00:00","no_pass":null,"school":null
	private JSONObject data = null;

	public AbstractEntity(JSONObject data) {
		this.data = data;
	}
	public JSONObject getData(){
		return this.data;
	}
	public String getValue(String key) {
		try {
			return this.data.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
}