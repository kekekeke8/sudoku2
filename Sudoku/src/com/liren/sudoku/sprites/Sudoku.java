package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.model.SudokuModel;

public class Sudoku extends AbstractSprite {
	public Sudoku(SudokuModel sudokuModel,int x, int y, int width, int height, Context context) {
		super(context);
		this.X = x;
		this.Y = x;
		this.Width = width;
		this.Height = height;
		this.Model = sudokuModel;
		InitSudoku(x, y, width, height, context);
	}
	public SudokuModel Model = null;
	private void InitSudoku( int x, int y, int width, int height, Context context) {
		
		String[] ss = Model.data.split(",");
		int count = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String s = ss[count];
				Cell p = new Cell(x, y, width, height, context);
				p.oValue = Integer.parseInt(s.substring(0, 1));
				p.nValue = Integer.parseInt(s.substring(0, 1));
				p.CellType = Integer.parseInt(s.substring(1, 2));
				if(p.CellType == 0)	p.nValue = 0;
				p.IDx = j;
				p.IDy = i;
				p.ShowError = Model.showError;
				p.SetSelected(false);
				p.parent = this;
				cells[j][i] = p;
				count++;
				Log.d("ddd", s.substring(1, 1));
			}
		}
	}

	public String GetSudokuDatas(){
		String result = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(cells[i][j].nValue == 0)
					result += Integer.toString(cells[i][j].oValue) +  "0,";
				else
					result += Integer.toString(cells[i][j].nValue) + Integer.toString(cells[i][j].CellType) + ",";
			}
		}
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
			Paint paint = new Paint();
			paint.setColor(Color.YELLOW);
			paint.setTextSize(44);
			canvas.drawText("Success!", 85, 170, paint);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		boolean needClearSelect = false;
		RectF rect = new RectF(X, Y, Width + X, Height + Y);
		Log.d("D", "Sudoku rect is:" + rect);
		needClearSelect = (rect.contains(event.getX(), event.getY()));
		if (needClearSelect)
			Log.d("D", "Map selected");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (needClearSelect) {
					cells[i][j].SetSelected(false);					
					cells[i][j].onTouchEvent(event);					
				}
			}
		}
	}
	
	public void onCellSelected(Cell cell){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(cells[i][j].nValue != 0){
					if(cells[i][j].nValue == cell.nValue){
						cells[i][j].IsSame = true;
						Log.d("D","Set selected value same " + cell.nValue + "=" + cells[i][j].nValue);
					}else{
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
					if(value != 0){
						cells[i][j].nValue = value;	
						if(cells[i][j].nValue != cells[i][j].oValue){
							Model.error++;						
						}
						Log.d("D","Sudoku state:" + Model.toString());
						this.checkSuccess();
					}
				}
			}
		}
	}

	//返回答案
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

	private boolean checkSuccess() {
		boolean success = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].oValue != cells[i][j].nValue)
					success = false;
			}
		}
		if (success) {
			Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show();
			Success = true;
			this.Model.finish = 2;
		}
		Log.d("D", "Sucess status:" + success);
		return success;
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
