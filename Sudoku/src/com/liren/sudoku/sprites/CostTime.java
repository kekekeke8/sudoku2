package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.Game;

public class CostTime extends AbstractSprite {
	private long startTime = System.currentTimeMillis();
	public CostTime(Context context) {
		super(context);
		this.startTime = System.currentTimeMillis() - Game.sudoku.Model.timecost * 1000;
	}

	RectF rect = new RectF(140,407,175,425);
	String timecostText = "";
	public void Draw(Canvas canvas) {
		long timecost = (System.currentTimeMillis() - startTime) / 1000;	
		Game.sudoku.Model.timecost = timecost;
		String text = 
			(Long.toString(timecost / 60).length() == 1?"0" + Long.toString(timecost / 60): Long.toString(timecost / 60)) + ":" 
		  + (Long.toString(timecost % 60).length() == 1?"0" + Long.toString(timecost % 60):Long.toString(timecost % 60));
		
		//if(!timecostText.equals(text)){			
			Paint paint = new Paint();
			//paint.setColor(Color.BLACK);
			//canvas.drawRect(rect, paint);
			
			timecostText = text;
			paint.setColor(Color.WHITE);
			canvas.drawText(timecostText, 143, 420, paint);
		//}		
	}

	public void onTouchEvent(MotionEvent event) {

	}

}
