package com.huiqu.model;

import org.json.JSONObject;

public class UserEntity extends AbstractEntity{

	public UserEntity(JSONObject data) {
		super(data);
	}

	public String getName(){
		return getValue("name");
	}
	public String getEmail(){
		return getValue("email");
	}
	public String getDisplayName(){
		return getValue("display_name");
	}
}
