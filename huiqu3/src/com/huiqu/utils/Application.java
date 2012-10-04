package com.huiqu.utils;

public class Application {
	private static Application _application = null;
	private Application(){}
	public static Application Instance(){
		if(_application == null)
			_application = new Application();
		return _application;
	}
	
	
	public Config config = new Config();
	public Service service = new Service();
}
