package com.liren.sudoku;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class StartActivity extends Activity {
	private StartView startView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startView = new StartView(this);
        setContentView(startView);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if(keyCode == KeyEvent.KEYCODE_BACK) return false;
		return super.onKeyDown(keyCode, event);
	}
	
}