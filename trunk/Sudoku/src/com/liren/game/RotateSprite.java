package com.liren.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;

public class RotateSprite extends AbstractSprite {

	public RotateSprite(Context context, int resource) {
		super(context);
		bitmap = BitmapFactory.decodeResource(context.getResources(), resource);
		this.Width = bitmap.getWidth();
		this.Height = bitmap.getHeight();
	}

	public float DegreeSpeed = 10f;
	private Paint paint = new Paint();
	private Matrix matrix = new Matrix();
	private float degree = 0f;
	public Bitmap bitmap = null;
	public boolean Play = false;
	public int Quan = 1;
	
	
	public void Draw(Canvas canvas) {
		if (Play) {
			degree += DegreeSpeed;
			if(Quan != 0){
				if(degree >= Quan * 360){
					Play = false;
				}
			}
			matrix.setRotate(degree, Width / 2, Height / 2);
			matrix.postTranslate(this.X, this.Y);
			canvas.drawBitmap(bitmap, matrix, paint);
		} else {
			canvas.drawBitmap(bitmap, this.X, this.Y, paint);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		if (GetRect().contains(event.getX(), event.getY())) {

		}
	}
}
