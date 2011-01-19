package com.liren.sudoku.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liren.game.Sprite;
import com.liren.game.SpriteAction;
import com.liren.sudoku.R;

public class ExplosionSprite extends Sprite {
	public ExplosionSprite(Context context, int frameWidth, int frameHeight,
			Bitmap bitmap) {
		super(context, frameWidth, frameHeight, bitmap);
	}

	public static ExplosionSprite create(Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.explode);
		ExplosionSprite tip = new ExplosionSprite(context, 64, 64, bitmap);		
		tip.setPosition(200,200);
		tip.FPS = 2;
		tip.LoopPlay = false;
		return tip;
	}

	private SpriteAction.OnSpriteStopListener listener = null;
	public void setOnSpriteStopListner(SpriteAction.OnSpriteStopListener listener){
		this.listener = listener;
	}
	
	@Override
	public void onStop() {		
		listener.onSpriteStop(this);
	}	
}
