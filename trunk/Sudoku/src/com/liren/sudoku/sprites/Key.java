package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.Game;
import com.liren.sudoku.R;

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
	private Bitmap background = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.point1);
	private Bitmap background_empty = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.point_empty2);

	private Bitmap xiangpi = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.xp);
	
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
			canvas.drawBitmap(background, rs, Rect, paint);
		} 
		else if (this.IsPencial) 
		{
			canvas.drawBitmap(background_empty, rs, Rect, paint);
		}
		if(this.Value != 0){
			paint.setColor(Color.argb(255, 94, 48, 16));
			paint.setTextSize(22);
			canvas.drawText(Integer.toString(Value), x + 9, y + 23, paint);
		}else{
			canvas.drawBitmap(xiangpi, x + 1, y + 1, paint);
		}
	}


	public void onTouchEvent(MotionEvent event) {
		if (Rect.contains(event.getX(), event.getY())) {
			Log.d("Debug", "Touch in Point ID:" + IDx + "," + IDy);
			if (this.IsPen) {
				Game.sudoku.setValue(this.Value);
			} else if (this.IsPencial) {
				Game.sudoku.setPencialValue(this.Value);
			}
		}		
	}

}
