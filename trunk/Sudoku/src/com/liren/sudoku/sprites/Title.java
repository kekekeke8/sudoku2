package com.liren.sudoku.sprites;

import android.content.Context;

import com.liren.game.Sprite;
import com.liren.sudoku.Resource;

public class Title extends Sprite {
	public Title(Context context) {
		super(context);
	}

	public static Sprite create(Context context) {		
		Sprite tip = new Sprite(context, 310, 100, Resource.getInstance().rTitle);		
		tip.setPosition(10,30);
		tip.FPS = 10;
		return tip;
	}
}
