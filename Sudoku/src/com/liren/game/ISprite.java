package com.liren.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface ISprite {	
	void Draw(Canvas canvas);
	void onTouchEvent(MotionEvent event);
}
