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
import com.liren.game.SpriteAction;
import com.liren.game.Sprite;

public class MenuSprite extends AbstractSprite implements SpriteAction.OnSpriteClickListener {

	public enum MENU_TYPE{
		START,PLAY
	}
	public enum MENU_ITEM {
		PLAY, STATUS, OPTIONS, HELP, ABOUT, EXIT,
		FLASH, EASY, MEDIUM, HARD, EXPERT, RESUME
	}

	private List<ISprite> Sprites = new ArrayList<ISprite>();

	private MenuSprite.MENU_TYPE menu_type = MenuSprite.MENU_TYPE.START;
	public MenuSprite(Context context, MenuSprite.MENU_TYPE menu_type) {
		super(context, 0, 0, 0, 0);
		this.menu_type = menu_type;
		if(menu_type.ordinal() == 0)
			createStartMenu(context);
		else
			createPlayMenu(context);
	}

	public void setResumeVisiable(boolean show) {
		if (menu_type == MenuSprite.MENU_TYPE.PLAY) {
			if (resumeSprite != null) {
				if (show) {
					if (!Sprites.contains(Sprites))
						Sprites.add(resumeSprite);
				} else {
					if (Sprites.contains(Sprites))
						Sprites.remove(resumeSprite);
				}
			}
		}
	}
	
	private void createStartMenu(Context context) {		
		Sprites.add(createMenuItem(context,Resource.getInstance().rPlay, 180, 160, MENU_ITEM.PLAY));
		Sprites.add(createMenuItem(context, Resource.getInstance().rStatus, 180, 200, MENU_ITEM.STATUS));
		Sprites.add(createMenuItem(context, Resource.getInstance().rOptions, 180, 240, MENU_ITEM.OPTIONS));
		Sprites.add(createMenuItem(context, Resource.getInstance().rHelp, 180, 280, MENU_ITEM.HELP));
		Sprites.add(createMenuItem(context, Resource.getInstance().rAbout, 180, 320, MENU_ITEM.ABOUT));
		Sprites.add(createMenuItem(context, Resource.getInstance().rExit, 180, 360, MENU_ITEM.EXIT));
	}
	
	ISprite resumeSprite = null;
	private void createPlayMenu(Context context) {
		Sprites.add(createMenuItem(context, Resource.getInstance().rFlash, 180, 160, MENU_ITEM.FLASH));
		Sprites.add(createMenuItem(context, Resource.getInstance().rEasy, 180, 200, MENU_ITEM.EASY));
		Sprites.add(createMenuItem(context, Resource.getInstance().rMedium, 180, 240, MENU_ITEM.MEDIUM));
		Sprites.add(createMenuItem(context, Resource.getInstance().rHard, 180, 280, MENU_ITEM.HARD));
		Sprites.add(createMenuItem(context, Resource.getInstance().rExpert, 180, 320, MENU_ITEM.EXPERT));		
		resumeSprite = createMenuItem(context, Resource.getInstance().rResume, 180, 360, MENU_ITEM.RESUME);
	}

	private Sprite createMenuItem(Context context, Bitmap bmp, int x, int y,MENU_ITEM id) {
		Sprite item = new Sprite(context, 130, 40, bmp);
		item.setPosition(x, y);
		item.Play = false;
		item.setOnClickListener(this);
		item.id = id.ordinal();
		return item;
	}

	private SpriteAction.OnSpriteClickListener listener = null;
	public void setOnClickListener(SpriteAction.OnSpriteClickListener listener){
		this.listener = listener;
	}
	
	public void onSpriteClick(Sprite v) {
		v.curFrameIndex = 1;		
		try {
			Thread.sleep(200);
			if(this.listener != null) 
				listener.onSpriteClick(v);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		v.curFrameIndex = 0;
	}
	
	
	public void Draw(Canvas canvas) {
		for (ISprite o : Sprites) {			
			o.Draw(canvas);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		for (ISprite o : Sprites) {
			o.onTouchEvent(event);
		}
	}
}
