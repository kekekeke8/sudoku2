package com.liren.sudoku.model;

public class SudokuModel {
	private int id;
	private int level;
	private String data; //30,0 hide锛�锛宻how,2 user
	private long timecost;
	private int tipcount;
	private int error;
	private int finish; //0 new ,1 resume,2 finish
	private int type;
	private boolean showError = true;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimecost() {
		return timecost;
	}

	public void setTimecost(long timecost) {
		this.timecost = timecost;
	}

	public int getTipcount() {
		return tipcount;
	}

	public void setTipcount(int tipcount) {
		this.tipcount = tipcount;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isShowError() {
		return showError;
	}

	public void setShowError(boolean showError) {
		this.showError = showError;
	}

	@Override
	public String toString() {
		return String.format("id:%s,level:%d,timecost:%d,tipcount:%d,error:%d,finish:%d,data:%s", id,level,timecost,tipcount,error,finish,data);
	}
}
