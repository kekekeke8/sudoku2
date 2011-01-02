package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.R;

public class BackButton extends AbstractSprite {
	private Paint paint = new Paint();
	private Matrix matrix = new Matrix();
	private float rotate,trans = 0f;
	private Bitmap bitmap = BitmapFactory.decodeResource(
			context.getResources(), R.drawable.point1);

	public BackButton(Context context) {
		super(context, 10, 10, 40, 15);
		paint.setAntiAlias(true);
	}

	public void onTouchEvent(MotionEvent event) {
		if (this.isTouchedIn(event.getX(), event.getY())) {
		}
	}

	public void Draw(Canvas canvas) {
		
		if(rotate > 20) 
			rotate--;
		else
			rotate++;
		
		trans = 30;
		matrix.setSkew(15f, 15f);
		//matrix.setRotate(rotate, 15f, 15f);		
		matrix.postTranslate(trans, trans);
		canvas.drawBitmap(bitmap, matrix, paint);
	}
}
