package com.liren.game;


public abstract interface SpriteAction {
	public interface OnSpriteClickListener {
		void onSpriteClick(Sprite v);
	}
	public interface OnSpriteStopListener {
		void onSpriteStop(Sprite v);
	}
	public interface OnSpriteStartListener {
		void onSpriteStart(Sprite v);
	}
	public interface OnSpritePlaying {
		void onSpritePlaying(Sprite v);
	}
}
