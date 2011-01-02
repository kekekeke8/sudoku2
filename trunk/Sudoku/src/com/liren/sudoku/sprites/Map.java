package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;

public class Map extends AbstractSprite {
	public Map(int x,int y,int width,int height,Context context) {
		super(context);
		X = x;
		Y = y;
		Width = width;
		Height = height;
	}

// x,y        w/3*1,y     	   w/3*2,y          w,y 

	
// x,h/3*1    w/3*1,h/3*1      w/3*2,h/3*1	    w,h/3*1
	
	
	
// x,h/3*2    w/3*2,h/3*2      w/3*2,h/3*2      w,h/3*2
	
	
// x,h        w/3*1,h          w/3*2,h          w,h
	

	public void Draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		int w = Width + X;
		int h = Height + Y;
		canvas.drawLines(new float[] { //锟斤拷锟狡边匡拷
				X, Y, X, h, 
				X, h, w, h, 
				w, h, w, Y, 
				X, Y, w, Y }, paint);
		canvas.drawLines(new float[] { //锟斤拷锟狡达拷锟�
				X, Height / 3 * 1 + Y, w, Height / 3 * 1 + Y, 
				X, Height / 3 * 2 + Y, w, Height / 3 * 2 + Y,
				Width / 3 * 1 + X, Y, Width / 3 * 1 + X, h, 
				Width / 3 * 2 + X, Y, Width / 3 * 2 + X, h 
		}, paint);
//		
//		paint.setColor(Color.WHITE);
//		canvas.drawLines(new float[] { 
//				X, Height / 9 * 1 + Y, w, Height / 9 * 1 + Y, 
//				X, Height / 9 * 2 + Y, w, Height / 9 * 2 + Y,
//				X, Height / 9 * 4 + Y, w, Height / 9 * 4 + Y,
//				X, Height / 9 * 5 + Y, w, Height / 9 * 5 + Y,
//				X, Height / 9 * 7 + Y, w, Height / 9 * 7 + Y,
//				X, Height / 9 * 8 + Y, w, Height / 9 * 8 + Y,
//		 
//				Width / 9 * 1 + X, Y, Width / 9 * 1 + X, h, 
//				Width / 9 * 2 + X, Y, Width / 9 * 2 + X, h,
//				Width / 9 * 4 + X, Y, Width / 9 * 4 + X, h, 
//				Width / 9 * 5 + X, Y, Width / 9 * 5 + X, h,
//				Width / 9 * 7 + X, Y, Width / 9 * 7 + X, h, 
//				Width / 9 * 8 + X, Y, Width / 9 * 8 + X, h 
//		}, paint);
		
	}

	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
