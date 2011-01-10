package com.liren.sudoku.model;

import java.util.List;

public class SudokuUpdateModel {
	private int version = 0;
	private List<SudokuModel> sudokus = null;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<SudokuModel> getSudokus() {
		return sudokus;
	}
	public void setSudokus(List<SudokuModel> value) {
		this.sudokus = value;
	}	
	
	@Override
	public String toString(){
		return String.format("Sudoku version:%d sudokus:{%s}",version, sudokus.toString());
	}
}
