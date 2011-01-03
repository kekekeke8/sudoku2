package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.R;

public class BackButton extends AbstractSprite {
	private Bitmap bitmap = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.back);

	public BackButton(Context context) {
		super(context, 10, 10, 40, 15);
		paint.setAntiAlias(true);
	}

	public void onTouchEvent(MotionEvent event) {
		if (this.isTouchedIn(event.getX(), event.getY())) {
			
		}
	}

	public void Draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()), this.GetRect(), paint);
	}
}
