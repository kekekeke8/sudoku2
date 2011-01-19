package com.liren.sudoku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liren.game.ResourceLoad;

public class Resource {
	private ResourceLoad.LoadEventListener _startListenter = null;

	public void setOnStartListener(ResourceLoad.LoadEventListener listener) {
		_startListenter = listener;
	}

	private ResourceLoad.LoadEventListener _processListenter = null;

	public void setOnProcessListener(ResourceLoad.LoadEventListener listener) {
		_processListenter = listener;
	}

	private ResourceLoad.LoadEventListener _finishListenter = null;

	public void setOnFinishListener(ResourceLoad.LoadEventListener listener) {
		_finishListenter = listener;
	}

	private static Resource resource = null;

	public static Resource getInstance() {
		if (resource == null)
			return new Resource();
		else
			return resource;
	}

	public Bitmap rBackground1 = null;
	public Bitmap rBackButton = null;
	public Bitmap rStartBackground = null;
	public Bitmap rPlay = null;
	public Bitmap rStatus = null;
	public Bitmap rOptions = null;
	public Bitmap rHelp = null;
	public Bitmap rAbout = null;
	public Bitmap rExit = null;

	public Bitmap rFlash = null;
	public Bitmap rEasy = null;
	public Bitmap rMedium = null;
	public Bitmap rHard = null;
	public Bitmap rExpert = null;
	public Bitmap rResume = null;

	public Bitmap rCell_Background = null;
	public Bitmap rCell_Background_empty = null;
	public Bitmap rCell_Select = null;
	public Bitmap rCell_Clean = null;

	public Bitmap rSucess = null;
	public Bitmap rTip = null;
	public Bitmap rTitle = null;

	public void Load(Context context) {
		rBackground1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.back1);
		rBackButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
		rStartBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.start_back);
		rPlay = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_play);
		rStatus = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_status);
		rOptions = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_options);
		rHelp = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_help);
		rAbout = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_about);
		rExit = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_exit);
		rFlash = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_flash);
		rEasy = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_easy);
		rMedium = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_medium);
		rHard = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_hard);
		rExpert = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_expert);
		rResume = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_resume);
		rCell_Background = BitmapFactory.decodeResource(context.getResources(), R.drawable.point1);
		rCell_Background_empty = BitmapFactory.decodeResource(context.getResources(), R.drawable.point_empty2);
		rCell_Select = BitmapFactory.decodeResource(context.getResources(), R.drawable.select);
		rCell_Clean = BitmapFactory.decodeResource(context.getResources(), R.drawable.xp);
		rSucess = BitmapFactory.decodeResource(context.getResources(), R.drawable.sucess);
		rTip = BitmapFactory.decodeResource(context.getResources(), R.drawable.tip);
		rTitle = BitmapFactory.decodeResource(context.getResources(), R.drawable.sudoku);
	}
}
