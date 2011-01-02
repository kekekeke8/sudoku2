package com.liren.sudoku.model;

public class SudokuModel {
	public int id;
	public int level;
	public String data; //30,0 hide，1，show,2 user
	public long timecost;
	public int tipcount;
	public int error;
	public int finish; //0 new ,1 resume,2 finish
	public boolean showError = true;
	
	@Override
	public String toString() {
		return String.format("id:%s,level:%d,timecost:%d,tipcount:%d,error:%d,finish:%d,data:%s", id,level,timecost,tipcount,error,finish,data);
	}
}
