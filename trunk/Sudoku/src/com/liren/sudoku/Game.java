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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.liren.game.ISprite;
import com.liren.game.RotateSprite;
import com.liren.sudoku.model.SudokuModel;
import com.liren.sudoku.sprites.CostTime;
import com.liren.sudoku.sprites.ErrorShower;
import com.liren.sudoku.sprites.KeyBoard;
import com.liren.sudoku.sprites.Status;
import com.liren.sudoku.sprites.Sudoku;
import com.liren.sudoku.sprites.Tip;

public class Game extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {

	boolean mbLoop = false;
	SurfaceHolder mSurfaceHolder = null;

	public List<ISprite> Sprites = new ArrayList<ISprite>();

	public static Sudoku sudoku = null;
	public static KeyBoard keyboard = null;
	private Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.back1);
	public Game(Context context,SudokuModel sudokumodel) {
		super(context);
		Log.d("D",sudokumodel.data);
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		setFocusable(true);
		mbLoop = true;
		int x = 25, y = 30, width = 270, height = 270;
		
		sudoku = new Sudoku(sudokumodel,x, y, width, height, context);
		Sprites.add(sudoku);
		keyboard = new KeyBoard(x - 18, y + width + y, width, height, context);
		Sprites.add(keyboard);
		Sprites.add(new Status(context));
		Sprites.add(new CostTime(context));		
		Sprites.add(new ErrorShower(context));
		Tip tip = Tip.create(context);
		Sprites.add(tip);
		
		RotateSprite rs = new RotateSprite(context,R.drawable.point1);
		rs.X = 15;
		rs.Y = 15;
		rs.Play = true;
		
		Sprites.add(rs);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mbLoop = false;
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

	public void Draw() {
		Canvas canvas = null;
		try {
			canvas = mSurfaceHolder.lockCanvas();
			if (mSurfaceHolder == null || canvas == null) {
				return;
			}
			
			canvas.drawBitmap(background, new Rect(0,0,320,480), new Rect(0,0,320,480), new Paint());
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
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		Log.d("d", "touch at " + event.getX() + "," + event.getY());
		for (ISprite o : Sprites) {
			o.onTouchEvent(event);
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);

		Log.d("DEBUG", "key down" + Integer.toString(keyCode));
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return super.onKeyUp(keyCode, event);
	}
}