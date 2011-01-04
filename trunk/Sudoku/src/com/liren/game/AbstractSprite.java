package com.liren.game;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class AbstractSprite implements ISprite {
	public int X = 0, Y = 0, Width = 0, Height = 0;

	public ISprite setPosition(int x, int y) {
		this.X = x;
		this.Y = y;
		return this;
	}
	protected Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	public Context context = null;
	
	public RectF GetRect(){
		return new RectF(X, Y, X + Width, Y + Height);
	}

	public AbstractSprite(Context context) {
		this.context = context;
	}

	public AbstractSprite(Context context, int x, int y, int width, int height) {
		this.context = context;
		X = x;
		Y = y;
		Width = width;
		Height = height;
	}

	protected boolean isTouchedIn(float x, float y) {
		return GetRect().contains(x, y);
	}
}
