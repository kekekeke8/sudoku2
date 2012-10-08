package com.huiqu.model;

import java.util.List;


public class UpdateModel {
	private int version = 0;
	private List<UpdateModel> huqu = null;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}	
	
	@Override
	public String toString(){
		return String.format("Huiqu version:%d huqu:{%s}",version, huqu.toString());
	}
}
