package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.model.SudokuModel;

public class Sudoku extends AbstractSprite {
	public Sudoku(SudokuModel sudokuModel, int x, int y, int width, int height,
			Context context) {
		super(context);
		this.X = x;
		this.Y = x;
		this.Width = width;
		this.Height = height;
		this.Model = sudokuModel;
		
		mFace = Typeface.MONOSPACE;
		//Typeface.createFromAsset(context.getAssets(),"cafe.ttf"); 
		InitSudoku(x, y, width, height, context);
	}
	private Typeface mFace = null;

	public SudokuModel Model = null;

	private void InitSudoku(int x, int y, int width, int height, Context context) {

		String[] ss = Model.data.split(",");
		int count = 0;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				String s = ss[count];
				Cell cell = new Cell(x, y, width, height, context);
				cell.oValue = Integer.parseInt(s.substring(0, 1));
				cell.nValue = Integer.parseInt(s.substring(0, 1));
				cell.CellType = Integer.parseInt(s.substring(1, 2));
				if (cell.CellType == 0) cell.nValue = 0;
				cell.IDx = i;
				cell.IDy = j;
				cell.ShowError = Model.showError;
				cell.SetSelected(false);
				cell.parent = this;
				cell.mFace = this.mFace;
				cells[i][j] = cell;				
				count++;
			}
		}
	}

	public String GetSudokuDatas() {
		String result = "";
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (cells[i][j].nValue == 0)
					result += Integer.toString(cells[i][j].oValue) + "0,";
				else
					result += Integer.toString(cells[i][j].nValue)
							+ Integer.toString(cells[i][j].CellType) + ",";
			}
		}
		Log.d("D", "Save sudoku:" + result);
		return result;
	}

	public Cell[][] cells = new Cell[9][9];

	public void Draw(Canvas canvas) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].Draw(canvas);
			}
		}

		if (this.Success) {			
			paint.setColor(Color.RED);
			paint.setTextSize(44);
			canvas.drawText("Success!", 85, 170, paint);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		boolean needClearSelect = false;
		RectF rect = new RectF(X, Y, Width + X, Height + Y);
		Log.d("D", "Sudoku rect is:" + rect);
		needClearSelect = (rect.contains(event.getX(), event.getY()));
		if (needClearSelect) Log.d("D", "Map selected");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (needClearSelect) {
					cells[i][j].SetSelected(false);
					cells[i][j].onTouchEvent(event);
				}
			}
		}
	}

	public void onCellSelected(Cell cell) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].nValue != 0) {
					if (cells[i][j].nValue == cell.nValue) {
						cells[i][j].IsSame = true;
						Log.d("D", "Set selected value same " + cell.nValue
								+ "=" + cells[i][j].nValue);
					} else {
						cells[i][j].IsSame = false;
					}
				}
			}
		}
	}

	public void setValue(int value) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected()) {
					if (value != 0) {
						//cells[i][j].nValue = value;
						if (cells[i][j].nValue != cells[i][j].oValue) {
							Model.error++;
						}
					}
					cells[i][j].SetValue(value);
					this.checkSuccess(i, j);
				}
			}
		}
	}

	// 返回答案
	public int getValue() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected()) {
					return cells[i][j].oValue;
				}
			}
		}
		return 0;
	}

	public boolean Success = false;

	private void DoComplete(boolean sucess) {
		if (sucess) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					this.cells[i][j].Play = true;
				}
			}
		}
	}

	private boolean checkSuccess(int x, int y) {
		boolean success = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].oValue != cells[i][j].nValue)
					success = false;
			}
		}
		if (success) {
			Success = true;
			DoComplete(true);
			this.Model.finish = 2;
		} else {
			this.checkComplete(x, y);
		}
		Log.d("D", "Sucess status:" + success);
		return success;
	}

	private void checkComplete(int x, int y) {
		//
		int xx = x / 3;
		int yy = y / 3;
		Log.d("D","checkComplete " + xx + "" +yy);
		boolean complete = true;
		for (int i = xx * 3; i < (xx + 1) * 3; i++) {
			for (int j = yy * 3; j < (yy + 1) * 3; j++) {
				Log.d("D","checkComplete " + i +"," + j);
				if(this.cells[i][j].nValue == 0){
					complete = false;
				}
			}
		}
		if(complete){
			for (int i = xx * 3; i < (xx + 1) * 3; i++) {
				for (int j = yy * 3; j < (yy + 1) * 3; j++) {
					this.cells[i][j].Play = true;
					Log.d("D","set complete Play" + i +"," + j);
				}
			}
		}
	}

	public void setPencialValue(int value) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected())
					cells[i][j].SetPencialValue(value);
			}
		}
	}
}
