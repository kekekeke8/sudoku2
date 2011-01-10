package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.GameView;
import com.liren.sudoku.R;

public class ErrorShower extends AbstractSprite {

	private Bitmap bitmap = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.error);

	public ErrorShower(Context context) {
		super(context);
	}

	int error = -1;
	RectF rect = new RectF(16, 407, 70, 425);

	public void Draw(Canvas canvas) {
		error = GameView.sudoku.Model.getError();

		if (error < 1)
			paint.setColor(Color.WHITE);
		else
			paint.setColor(Color.RED);
		if (error > 3)
			
			canvas.drawText(context.getResources().getString(R.string.error) + ":" + Integer.toString(error), 16, 420, paint);
		else {
			for (int i = 0; i < error; i++) {
				canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(),
						bitmap.getHeight()), 
						new Rect(i * 19 + 6, 405,i * 19 + 32, 425), paint);
			}
		}
	}

	public void onTouchEvent(MotionEvent event) {
	}
}
