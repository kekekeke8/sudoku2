package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.GameView;
import com.liren.sudoku.Resource;
import com.liren.sudoku.model.SudokuModel;

public class Sudoku extends AbstractSprite {
	public Sudoku(SudokuModel sudokuModel, int x, int y, int width, int height, Context context) {
		super(context);
		this.X = x;
		this.Y = x;
		this.Width = width;
		this.Height = height;
		this.Model = sudokuModel;

		mFace = Typeface.MONOSPACE;
		InitSudoku(x, y, width, height, context);
	}

	private Typeface mFace = null;

	public SudokuModel Model = null;

	private void InitSudoku(int x, int y, int width, int height, Context context) {

		String[] ss = Model.getData().split(",");
		int count = 0;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				Log.d("D", "InitSudoku count :" + count);
				String s = ss[count];
				Cell cell = new Cell(x, y, width, height, context);

				cell.oValue = Integer.parseInt(s.substring(0, 1));
				cell.nValue = cell.oValue;
				cell.CellType = Integer.parseInt(s.substring(1, 2)); // 0,1,2

				if (cell.CellType == 0)
					cell.nValue = 0;
				cell.IDx = i;
				cell.IDy = j;
				cell.ShowError = Model.isShowError();
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
					result += Integer.toString(cells[i][j].nValue) + Integer.toString(cells[i][j].CellType) + ",";
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
			canvas.drawBitmap(Resource.getInstance().rSucess, 60, 120, paint);
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

	public void onCellSelected(Cell cell) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].nValue != 0) {
					if (cell.nValue != 0) {
						if (cells[i][j].nValue == cell.nValue) {
							cells[i][j].IsSame = true;
						} else {
							cells[i][j].IsSame = false;
						}
					} else {
						cells[i][j].IsSame = false;
					}
				} else {
					cells[i][j].IsSame = false;
				}
			}
		}
	}

	public Cell getSelected() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected()) {
					return cells[i][j];
				}
			}
		}
		return null;
	}

	public void setValue(int value) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected()) {
					cells[i][j].SetValue(value);
					if (value != 0) {
						if (!checkCellValue(cells[i][j])) {
							this.Model.setError(this.Model.getError() + 1);
							GameView.soundPlayer.playError();
						} else {
							GameView.soundPlayer.playRight();
						}
					}
					onCellSelected(cells[i][j]);
					checkSuccess(i, j);
				}
			}
		}
	}

	private boolean checkCellValue(Cell cell) {
		if (this.Model.getType() == 0) {
			return (cell.oValue == cell.nValue);
		} else {
			return true;
			//
		}
	}

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

	private void doComplete() {
		if (this.Success) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					this.cells[i][j].Play = true;
				}
			}
			GameView.soundPlayer.playLevelComplete();
		}
	}

	private void checkSuccess(int x, int y) {
		// boolean success = true;
		// if (this.Model.getType() == 0) {
		// for (int i = 0; i < 9; i++) {
		// for (int j = 0; j < 9; j++) {
		// if (cells[i][j].oValue != cells[i][j].nValue)
		// success = false;
		// }
		// }
		// if (success) {
		// Success = true;
		// doComplete();
		// this.Model.setFinish(2);
		// } else {
		// this.checkComplete(x, y);
		// }
		// return success;
		// } else {
		// success = false;
		// this.checkComplete(x, y);
		// return success;
		// }
		if(!checkSucess()){
			checkComplete( x,  y);
		}else{
			Success = true;
			doComplete();
			this.Model.setFinish(2);
		}
	}

	private void checkComplete(int x, int y) {
		int xx = x / 3;
		int yy = y / 3;
		boolean complete = true;
		// squre
		int[] temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // 用来判断重复，无任何意义
		for (int i = xx * 3; i < (xx + 1) * 3; i++) {
			for (int j = yy * 3; j < (yy + 1) * 3; j++) {
				if ((cells[i][j].nValue != 0) && (temp[cells[i][j].nValue] == 0))
					temp[cells[i][j].nValue] = 99;
				else {
					complete = false;
				}
			}
		}
		if (complete) {
			for (int i = xx * 3; i < (xx + 1) * 3; i++) {
				for (int j = yy * 3; j < (yy + 1) * 3; j++) {
					this.cells[i][j].Play = true;
				}
			}
			GameView.soundPlayer.playExplode();
		}
		Log.d("D", "check squre complete:" + complete);

		// col
		complete = true;
		temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // 用来判断重复，无任何意义
		for (int j = 0; j < 9; j++) {
			if ((cells[x][j].nValue != 0) && (temp[cells[x][j].nValue] == 0))
				temp[cells[x][j].nValue] = 99;
			else {
				complete = false;
			}
		}
		if (complete) {
			for (int j = 0; j < 9; j++) {
				this.cells[x][j].Play = true;
			}
			GameView.soundPlayer.playExplode();
		}

		// row
		complete = true;
		temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // 用来判断重复，无任何意义
		for (int i = 0; i < 9; i++) {
			if ((cells[i][y].nValue != 0) && (temp[cells[i][y].nValue] == 0))
				temp[cells[i][y].nValue] = 99;
			else {
				complete = false;
			}
		}
		if (complete) {
			for (int i = 0; i < 9; i++) {
				this.cells[i][y].Play = true;
			}
			GameView.soundPlayer.playExplode();
		}
	}
	
	private boolean checkSucess() {
		boolean sucess = true;
		for(int x = 0;x < 9; x++){
			for(int y = 0;y < 9; y++){
				int xx = x / 3;
				int yy = y / 3;

				int[] temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				if(x % 3 == 0){
					for (int i = xx * 3; i < (xx + 1) * 3; i++) {
						for (int j = yy * 3; j < (yy + 1) * 3; j++) {
							if ((cells[i][j].nValue != 0) && (temp[cells[i][j].nValue] == 0))
								temp[cells[i][j].nValue] = 99;
							else {
								return false;
							}
						}
					}
				}
				temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; 
				for (int j = 0; j < 9; j++) {
					if ((cells[x][j].nValue != 0) && (temp[cells[x][j].nValue] == 0))
						temp[cells[x][j].nValue] = 99;
					else {
						return false;
					}
				}

				temp = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				for (int i = 0; i < 9; i++) {
					if ((cells[i][y].nValue != 0) && (temp[cells[i][y].nValue] == 0))
						temp[cells[i][y].nValue] = 99;
					else {
						return false;
					}
				}
			}
		}
		return sucess;
	}

	// private void checkComplete(int x, int y) {
	// int xx = x / 3;
	// int yy = y / 3;
	// Log.d("D", "checkComplete " + xx + "" + yy);
	// boolean complete = true;
	// for (int i = xx * 3; i < (xx + 1) * 3; i++) {
	// for (int j = yy * 3; j < (yy + 1) * 3; j++) {
	// if (cells[i][j].oValue != cells[i][j].nValue) {
	// complete = false;
	// }
	// }
	// }
	// if (complete) {
	// for (int i = xx * 3; i < (xx + 1) * 3; i++) {
	// for (int j = yy * 3; j < (yy + 1) * 3; j++) {
	// this.cells[i][j].Play = true;
	// }
	// }
	// GameView.soundPlayer.playExplode();
	// }
	//
	// // lie
	// complete = true;
	// for (int j = 0; j < 9; j++) {
	// if (cells[x][j].oValue != cells[x][j].nValue) {
	// complete = false;
	// }
	// }
	// if (complete) {
	// for (int j = 0; j < 9; j++) {
	// this.cells[x][j].Play = true;
	// }
	// GameView.soundPlayer.playExplode();
	// }
	//
	// // hang
	// complete = true;
	// for (int i = 0; i < 9; i++) {
	// if (cells[i][y].oValue != cells[i][y].nValue) {
	// complete = false;
	// }
	// }
	// if (complete) {
	// for (int i = 0; i < 9; i++) {
	// this.cells[i][y].Play = true;
	// }
	// GameView.soundPlayer.playExplode();
	// }
	// }

	public void setPencialValue(int value) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].GetSelected())
					cells[i][j].SetPencialValue(value);
			}
		}
	}
}
