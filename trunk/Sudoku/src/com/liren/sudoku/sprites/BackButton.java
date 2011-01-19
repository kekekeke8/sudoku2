package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.Resource;

public class BackButton extends AbstractSprite {
	private Bitmap bitmap = null;
	public BackButton(Context context) {
		super(context, 10, 10, 40, 15);
		bitmap = Resource.getInstance().rBackButton;
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
