package com.liren.sudoku;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.liren.game.ISprite;
import com.liren.game.SpriteAction;
import com.liren.game.Sprite;
import com.liren.sudoku.sprites.ExplosionSprite;
import com.liren.sudoku.sprites.Title;

public class MenuView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	public MenuView(Context context,MenuSprite.MENU_TYPE menutype) {
		super(context);
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		setFocusable(true);
		mbLoop = true;
		Sprites.add(Title.create(context));
		menu = new MenuSprite(context,menutype);
		menu.setOnClickListener(new SpriteAction.OnSpriteClickListener() {			
			public void onSpriteClick(Sprite v) {
				if(listener != null)
					listener.onSpriteClick(v);	
			}
		});
		Sprites.add(menu);	
		s = ExplosionSprite.create(context);
		s.setOnSpriteStopListner(new SpriteAction.OnSpriteStopListener() {			
			public void onSpriteStop(Sprite v) {
				Sprites.remove(s);
			}
		});
		Sprites.add(s);
	}
	ExplosionSprite s = null;
	private MenuSprite menu = null;
	private boolean mbLoop = false;
	private SurfaceHolder mSurfaceHolder = null;
	public List<ISprite> Sprites = new ArrayList<ISprite>();
	private Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.start_back);

	private SpriteAction.OnSpriteClickListener listener = null;
	public void setOnMenuItemClickListener(SpriteAction.OnSpriteClickListener listener){
		this.listener = listener;
	}
	
	public void Draw() {
		Canvas canvas = null;
		try {
			canvas = mSurfaceHolder.lockCanvas();
			if (mSurfaceHolder == null || canvas == null) {
				return;
			}

			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			canvas.drawBitmap(background, new Rect(0, 0, 320, 480), new Rect(0, 0, 320, 480), paint );
			for (ISprite o : Sprites) {
				o.Draw(canvas);
			}
			Path path = new Path();
	        path.lineTo(0,500);//定义字符路径

			canvas.drawTextOnPath("Copyright (C) 2011 Liren", path, 290, -5, paint);
			mSurfaceHolder.unlockCanvasAndPost(canvas);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		for (ISprite o : Sprites) {
			o.onTouchEvent(event);
		}
		return false;
	}

	public void run() {
		while (mbLoop) {
			try {
				Thread.sleep(30);
			} catch (Exception e) {
			}
			synchronized (mSurfaceHolder) {
				Draw();
			}
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		mbLoop = true;
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		mbLoop = false;
	}
	
	public void setResumeVisiable(boolean show){
		menu.setResumeVisiable(show);
	}
}
