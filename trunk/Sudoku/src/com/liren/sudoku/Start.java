package com.liren.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Start extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPlay:
			Intent i = new Intent(this,Play.class);
			startActivity(i);
			break;
		case R.id.btnExit:
			this.finish();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if(keyCode == KeyEvent.KEYCODE_BACK) return false;
		return super.onKeyDown(keyCode, event);
	}
	
}