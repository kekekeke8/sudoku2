package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.sudoku.GameView;
import com.liren.sudoku.R;

public class Tip extends AbstractSprite {

	public static Tip create(Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.tip);
		Tip tip = new Tip(context, 50, 50, bitmap);
		tip.X = 300;
		tip.Y = 20;
		return tip;
	}

	@Override
	public RectF GetRect() {
		int left = this.X - 15;
		int top = this.Y - 15;
		return new RectF(left, top, left + 30, top + 30);
	}

	public Tip(Context context, int width, int height, Bitmap bitmap) {
		super(context);
		this.Height = height;
		this.Width = height;
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

	private Paint paint = new Paint();
	private Bitmap[] images = null;

	public int curFrameIndex = 0;
	public boolean LoopPlay = true;


	public void Draw(Canvas canvas) {
		if(!GameView.sudoku.Success){
			if(GameView.sudoku.Model.getTipcount() > 0){
				canvas.drawBitmap(images[curFrameIndex], new Rect(0, 0, 60, 60), this.GetRect(), paint);
				nextFrame();
			}else{
				canvas.drawBitmap(images[0], new Rect(0, 0, 60, 60), this.GetRect(), paint);
			}
		}else{
			canvas.drawBitmap(images[0], new Rect(0, 0, 60, 60), this.GetRect(), paint);
		}
	}

	private void nextFrame() {
		curFrameIndex++;
		if (curFrameIndex >= images.length)
			curFrameIndex = 0;
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		if (GetRect().contains(x, y)) {
			int value = GameView.sudoku.getValue();
			if (value != 0) {
				if (GameView.sudoku.Model.getTipcount() > 0) {
					if(GameView.sudoku.getSelected() != null){
						GameView.sudoku.setValue(value);
						GameView.sudoku.Model.setTipcount(GameView.sudoku.Model.getTipcount() - 1);
					}
				}
			}
		}
	}
}
