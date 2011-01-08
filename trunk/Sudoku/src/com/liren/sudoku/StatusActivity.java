package com.liren.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class StatusActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(this,StartActivity.class);
			this.startActivity(intent);
			this.finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
