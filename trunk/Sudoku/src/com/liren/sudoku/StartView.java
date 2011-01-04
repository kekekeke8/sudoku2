package com.liren.sudoku;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.liren.game.ISprite;
import com.liren.game.ISpriteAction;
import com.liren.game.Sprite;
import com.liren.sudoku.sprites.Title;

public class StartView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {
	public StartView(Context context) {
		super(context);
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		setFocusable(true);
		mbLoop = true;
		Sprites.add(Title.create(context));
		Sprites.add(new StartMenuSprite(context,185,180,300,400));	
	}

	private boolean mbLoop = false;
	private SurfaceHolder mSurfaceHolder = null;
	public List<ISprite> Sprites = new ArrayList<ISprite>();
	private Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.start_back);

	public void Draw() {
		Canvas canvas = null;
		try {
			canvas = mSurfaceHolder.lockCanvas();
			if (mSurfaceHolder == null || canvas == null) {
				return;
			}

			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			canvas.drawBitmap(background, new Rect(0, 0, 320, 480), new Rect(0,
					0, 320, 480), paint );
			for (ISprite o : Sprites) {
				o.Draw(canvas);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mSurfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		for (ISprite o : Sprites) {
			o.onTouchEvent(event);
		}
		return false;
	}

	@Override
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

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mbLoop = false;
	}
}
