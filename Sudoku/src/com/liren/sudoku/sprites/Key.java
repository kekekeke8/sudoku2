package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.GameView;
import com.liren.sudoku.Resource;

public class Key extends AbstractSprite {

	public Key(int x, int y, int width, int height, Context context) {
		super(context);
		X = x;
		Y = y;
		Width = width;
		Height = height;
	}

	public int Value;
	public int IDx;
	public int IDy;

	public boolean IsPen = false;
	public boolean IsPencial = false;;

	public RectF Rect = new RectF();
	
	
	public void Draw(Canvas canvas) {
		int x = X + 30 * (IDx);
		int y = Y + 30 * (IDy);
		Rect.left = x + 1;
		Rect.right = 30 + x - 1;
		Rect.top = y + 1;
		Rect.bottom = y + 30 - 1;

		Rect rs = new Rect(0, 0, 30, 30);

		if (this.IsPen) 
		{
			canvas.drawBitmap(Resource.getInstance().rCell_Background, rs, Rect, paint);
		} 
		else if (this.IsPencial) 
		{
			canvas.drawBitmap(Resource.getInstance().rCell_Background_empty, rs, Rect, paint);
		}
		if(this.Value != 0){
			paint.setColor(Color.argb(255, 94, 48, 16));
			paint.setTextSize(22);
			canvas.drawText(Integer.toString(Value), x + 9, y + 23, paint);
		}else{
			canvas.drawBitmap(Resource.getInstance().rCell_Select, x + 1, y + 1, paint);
		}
	}


	public void onTouchEvent(MotionEvent event) {
		if (Rect.contains(event.getX(), event.getY())) {
			if (this.IsPen) {
				GameView.sudoku.setValue(this.Value);
			} else if (this.IsPencial) {
				GameView.sudoku.setPencialValue(this.Value);
			}
		}		
	}

}
