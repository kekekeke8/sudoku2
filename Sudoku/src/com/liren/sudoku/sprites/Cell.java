package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.GameView;
import com.liren.sudoku.Resource;

public class Cell extends AbstractSprite {

	public Cell(int x, int y, int width, int height, Context context) {
		super(context);
		X = x;
		Y = y;
		Width = width;
		Height = height;
	}

	public Sudoku parent = null;

	public int oValue; // 棰樼洰绛旀
	public int nValue; // 濡傛灉
						// ShowValue锛屽簲璇ュ拰oValue鐩哥瓑锛屽惁鍒欏簲璇ョ己鐪佷负0锛屽鏋滀笉鏄浂灏辨槸鐢ㄦ埛濉啓鐨勫�
	public int CellType; // 棰樼洰缁欏嚭鐨勬樉绀哄嚭鏉ョ殑鏁板瓧s
	public int IDx;
	public int IDy;

	public int[] pValue = new int[9];

	public boolean IsPen = false;
	public boolean IsPencial = false;

	public boolean ShowError = true; // 濡傛灉娣婚敊鏄惁鏄剧ず鎴愮孩鑹�
	public int fontColor = Color.argb(255, 94, 48, 16);

	public boolean IsSame = false;

	public Typeface mFace = null;

	public RectF _rect = new RectF();


	public boolean Play = false;
	public float DegreeSpeed = 10f;
	private Matrix matrix = new Matrix();
	private float degree = 0f;
	public int Quan = 1;

	public void Draw(Canvas canvas) {
		int x = X + 30 * (IDx);
		int y = Y + 30 * (IDy);
		_rect.left = x + 1;
		_rect.right = 30 + x - 1;
		_rect.top = y + 1;
		_rect.bottom = y + 30 - 1;

		// Rect rs = new Rect(0, 0, 30, 30);

		if (Play) {
			degree += DegreeSpeed;
			if (Quan != 0) {
				if (degree >= Quan * 180) {
					Play = false;
					degree = 0f;
				}
			}
			// canvas.drawBitmap(background, matrix, paint);
		}
		matrix.setRotate(degree, 14, 14);
		matrix.postTranslate(x, y);

		if (this.CellType == 1) {
			// canvas.drawBitmap(background, rs, _rect, paint);
			canvas.drawBitmap(Resource.getInstance().rCell_Background, matrix, paint);
		} else {
			canvas.drawBitmap(Resource.getInstance().rCell_Background_empty, matrix, paint);
		}

		if (!Play) {
			if (this.GetSelected()) {
				RectF rect = new RectF(_rect.left - 3, _rect.top - 3,
						_rect.right + 1, _rect.bottom + 1);
				canvas.drawBitmap(Resource.getInstance().rCell_Select, new Rect(0, 0, 60, 60), rect, paint);

			} else if (this.IsSame) {
				RectF rect = new RectF(_rect.left - 3, _rect.top - 3,
						_rect.right + 1, _rect.bottom + 1);
				canvas.drawBitmap(Resource.getInstance().rCell_Select, new Rect(0, 0, 60, 60), rect, paint);
			}
		}

		if (this.CellType == 1) {
			DrawValue(canvas, fontColor, nValue);
		} else {
			if (this.nValue != 0) {
				if (ShowError) {
					if (this.oValue != this.nValue) {
						DrawValue(canvas, Color.RED, nValue);
					} else {
						DrawValue(canvas, fontColor, nValue);
					}
				} else {
					DrawValue(canvas, fontColor, nValue);
				}
			}
		}
//		paint.setAlpha(100);
//		paint.setTextSize(9f);
//		paint.setColor(Color.BLUE);
//		canvas.drawText(Integer.toString(this.nValue), x + 1, y + 10, paint);
//		paint.setColor(Color.RED);
//		canvas.drawText(Integer.toString(this.oValue), x + 10, y + 10, paint);
//		paint.setColor(Color.GREEN);
//		canvas.drawText(Integer.toString(this.CellType), x + 1, y + 20, paint);

		if (this.CellType == 0) {
			int c = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
									
					int v = this.pValue[c];c++;
					if (v != 0) {
						paint.setTextSize(8f);
						canvas.drawText(Integer.toString(v), (i % 3 * 8) + 3
								+ x, (j % 3 * 8) + 9 + y, paint);
					}
				}
			}
		}
	}

	public void SetValue(int value) {
		if (this.CellType != 1) {
			if (value == 0) {
				this.CellType = 0;
			} else if (this.CellType != 1) {
				this.CellType = 2;
			}

			for (int i = 0; i < 9; i++) {
				this.pValue[i] = 0;
			}

			this.nValue = value;
			SetSelected(true);
		}
	}

	public void SetPencialValue(int value) {
		if (this.CellType != 1) {
			Log.d("D","Set pencial value:" + value);
			if (this.CellType == 0) {
				
			} else { //CellType = 2
				this.nValue = 0;
				this.CellType = 0;				
			}
			if (value == 0) {
				for (int i = 0; i < 9; i++) {
					this.pValue[i] = 0;
				}
			}
			if ((value > 0) && (value < 10)){
				if(this.pValue[value - 1] == value){
					this.pValue[value - 1] = 0;
				}else{
					this.pValue[value - 1] = value;
				}
			}
		}
	}

	private void DrawValue(Canvas canvas, int color, int value) {
		int x = X + 30 * (IDx);
		int y = Y + 30 * (IDy);
		paint.setTextSize(22);
		paint.setTypeface(this.mFace);
		paint.setColor(color);
		canvas.drawText(Integer.toString(value), x + 9, y + 23, paint);
		paint.setTypeface(null);
	}

	private boolean _selected = false;

	public boolean GetSelected() {
		return this._selected;
	}

	public void SetSelected(boolean value) {
		if (this._selected != value) {
			this._selected = value;
			if (value){
				this.parent.onCellSelected(this);
				GameView.soundPlayer.playSelect();
			}
		}
	}

	public void onTouchEvent(MotionEvent event) {
		if (_rect.contains(event.getX(), event.getY())) {
			SetSelected(true);
		}
	}
}
