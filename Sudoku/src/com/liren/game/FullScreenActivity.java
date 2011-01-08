package com.liren.game;

import com.liren.sudoku.SoundPlayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
    		  WindowManager.LayoutParams. FLAG_FULLSCREEN);
        soundPlayer = new SoundPlayer(this);
        
	}
	public SoundPlayer soundPlayer = null;
}
