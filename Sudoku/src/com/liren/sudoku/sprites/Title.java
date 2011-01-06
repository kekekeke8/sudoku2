package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liren.game.Sprite;
import com.liren.sudoku.R;

public class Title extends Sprite {
	public Title(Context context) {
		super(context);
	}

	public static Sprite create(Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.sudoku);
		Sprite tip = new Sprite(context, 310, 100, bitmap);		
		tip.setPosition(10,30);
		tip.FPS = 10;
		return tip;
	}
}
