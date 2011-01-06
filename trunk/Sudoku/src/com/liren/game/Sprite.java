package com.liren.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;


public class Sprite extends AbstractSprite {

	public Sprite(Context context) {
		super(context);
	}

	public Sprite(Context context, int frameWidth, int frameHeight,
			Bitmap bitmap) {
		super(context);
		this.Height = frameHeight;
		this.Width = frameWidth;
		images = new Bitmap[(bitmap.getWidth() / this.Width)
				* (bitmap.getHeight() / this.Height)];
		int counter = 0;
		for (int x = 0; x < (bitmap.getWidth() / this.Width); x++) {
			for (int y = 0; y < (bitmap.getHeight() / this.Height); y++) {
				images[counter] = Bitmap.createBitmap(bitmap, x * Width, y
						* Height, Width, Height);
				counter++;
			}
		}
	}

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Bitmap[] images = null;

	public int curFrameIndex = 0;
	public boolean LoopPlay = true;

	private SpriteAction.OnSpriteClickListener listener = null;
	public void setOnClickListener(SpriteAction.OnSpriteClickListener listener){
		this.listener = listener;
	}
	
	public void onStop() {

	}

	public void nextFrame() {
		curFrameIndex++;
		if (curFrameIndex >= images.length) {
			curFrameIndex = 0;
			if (!LoopPlay){
				onStop();
				Play = false;
			}
		}
	}

	private long tickCount = 0;
	public boolean Play = true;
	public int FPS = 20;


	public void Draw(Canvas canvas) {
		if (Play) {
			if (curFrameIndex < images.length) {
				canvas.drawBitmap(images[curFrameIndex], new Rect(0, 0, Width,
						Height), this.GetRect(), paint);
				if (System.currentTimeMillis() - tickCount > 1000 / FPS) {
					tickCount = System.currentTimeMillis();
					nextFrame();
				}
			}
		} else {
			canvas.drawBitmap(images[curFrameIndex], new Rect(0, 0, Width, Height),
					this.GetRect(), paint);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		if (GetRect().contains(event.getX(),event.getY())) {
			if(listener != null)
				listener.onSpriteClick(this);
		}
	}

	@Override
	public RectF GetRect() {
		RectF rect = new RectF(X, Y, X + Width, Y + Height);
		return rect;
	}
}
