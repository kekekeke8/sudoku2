package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.Game;

public class ErrorShower extends AbstractSprite {

	public ErrorShower(Context context) {
		super(context);
	}

	int error = -1;
	RectF rect = new RectF(16, 407, 70, 425);

	public void Draw(Canvas canvas) {
		error = Game.sudoku.Model.error;
		
		if (error < 1)
			paint.setColor(Color.WHITE);
		else
			paint.setColor(Color.RED);
		canvas.drawText("Error:" + Integer.toString(error), 16, 420, paint);
	}

	public void onTouchEvent(MotionEvent event) {
	}
}
