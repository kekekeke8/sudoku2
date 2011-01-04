package com.liren.sudoku;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.liren.game.AbstractSprite;
import com.liren.game.ISprite;
import com.liren.game.ISpriteAction;
import com.liren.game.Sprite;

public class StartMenuSprite extends AbstractSprite {

	private Bitmap bmpPlay = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_play);
	private Bitmap bmpStatus = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_status);
	private Bitmap bmpOptions = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_options);
	private Bitmap bmpHelp = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_help);
	private Bitmap bmpAbout = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_about);
	private Bitmap bmpExit = BitmapFactory.decodeResource( context.getResources(), R.drawable.menu_exit);

	private List<ISprite> Sprites = new ArrayList<ISprite>();
	public StartMenuSprite(Context context, int x, int y, int width, int height) {
		super(context, x, y, width, height);		
		Sprites.add(CreateMenuItem(context, bmpPlay, 177, 170));
		Sprites.add(CreateMenuItem(context, bmpStatus, 177, 210));
		Sprites.add(CreateMenuItem(context, bmpOptions, 177, 250));
		Sprites.add(CreateMenuItem(context, bmpHelp, 177, 290));
		Sprites.add(CreateMenuItem(context, bmpAbout, 177, 330));
		Sprites.add(CreateMenuItem(context, bmpExit, 177, 370));
	}

	private Sprite CreateMenuItem(Context context, Bitmap bmp, int x, int y) {
		Sprite menu_play = new Sprite(context, 130, 40, bmp);
		menu_play.setPosition(x, y);
		menu_play.Play = false;
		menu_play.setOnClickListener(new ISpriteAction.OnClickListener() {			
			@Override
			public void onClick(Sprite v) {
				switch(v.Y){
				case 170:
					v.curFrameIndex = 1;
					break;
				case 210:
					v.curFrameIndex = 1;
					break;
				case 250:
					v.curFrameIndex = 1;
					break;
				case 290:
					v.curFrameIndex = 1;
					break;
				case 330:
					v.curFrameIndex = 1;
					break;
				case 370:
					v.curFrameIndex = 1;
					break;
				}
			}
		});
		return menu_play;
	}

	@Override
	public void Draw(Canvas canvas) {
		// canvas.drawBitmap(background, new Rect(0,0,320,480), new
		// Rect(0,0,320,480), new Paint());
		for (ISprite o : Sprites) {
			o.Draw(canvas);
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		for (ISprite o : Sprites) {
			o.onTouchEvent(event);
		}
	}

}
