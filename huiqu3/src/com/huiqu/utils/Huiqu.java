package com.huiqu.utils;

import com.huiqu.model.UserEntity;


public class Huiqu {
	private static Huiqu _huiqu = null;
	
	private Huiqu(){}
	public static Huiqu I(){
		if(_huiqu == null)
			_huiqu = new Huiqu();
		return _huiqu;
	}
	
	public boolean checkLogin(){
		return user != null;
	}
	
	public HuiquConfig config = new HuiquConfig();
	public HuiquService service = new HuiquService();
	public HuiquMethods methods = new HuiquMethods(service,config);
	public UserEntity user = null;

	public static String TAG_DEBUG  = "HUIQU_DEBUG";
	public static String TAG_ERROR = "HUIQU_ERROR";
}
