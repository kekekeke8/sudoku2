package com.liren.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.widget.TextView;

import com.liren.game.FullScreenActivity;

public class InfoActivity extends FullScreenActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		TextView text = (TextView)this.findViewById(R.id.about_text);
		int info = this.getIntent().getIntExtra("info", R.string.game_about);
		text.setText(info);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		Intent intent = new Intent(this, StartActivity.class);
		this.startActivity(intent);
		this.finish();
	}
}
