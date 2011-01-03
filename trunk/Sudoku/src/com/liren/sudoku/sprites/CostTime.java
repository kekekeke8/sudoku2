package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.Game;

public class CostTime extends AbstractSprite {
	private long startTime = System.currentTimeMillis();

	public CostTime(Context context) {
		super(context);
		this.startTime = System.currentTimeMillis()
				- Game.sudoku.Model.timecost * 1000;
	}

	RectF rect = new RectF(140, 407, 175, 425);
	String timecostText = "";

	long timecost = 0;
	public void Draw(Canvas canvas) {
		if(!Game.sudoku.Success)
		{
			timecost = (System.currentTimeMillis() - startTime) / 1000;
			Game.sudoku.Model.timecost = timecost;
		}
		String text = (Long.toString(timecost / 60).length() == 1 ? "0"
				+ Long.toString(timecost / 60) : Long.toString(timecost / 60))
				+ ":"
				+ (Long.toString(timecost % 60).length() == 1 ? "0"
						+ Long.toString(timecost % 60) : Long
						.toString(timecost % 60));

		timecostText = text;
		paint.setColor(Color.argb(255, 94, 48, 16));
		canvas.drawText(timecostText, 143, 420, paint);
	}

	public void onTouchEvent(MotionEvent event) {

	}

}
