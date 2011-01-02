package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;

public class KeyBoard extends AbstractSprite {

	public boolean IsPen = false;
	public Key[][] keys = new Key[2][10];

	public KeyBoard(int x, int y, int width, int height, Context context) {
		super(context);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				Key p = new Key(x, y, width, height, context);

				p.Value = j == 9 ? 0 : j + 1;

				p.IsPen = (i == 0);
				p.IsPencial = (i == 1);
				p.IDx = j;
				p.IDy = i;
				keys[i][j] = p;
			}
		}
	}

	public void Draw(Canvas canvas) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				{
					keys[i][j].Draw(canvas);
				}
			}
		}
	}

	public void onTouchEvent(MotionEvent event) {

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				{
					keys[i][j].onTouchEvent(event);
				}
			}
		}
	}

}
