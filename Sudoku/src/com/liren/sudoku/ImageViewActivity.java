package com.liren.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.liren.game.FullScreenActivity;

public class ImageViewActivity extends FullScreenActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int backgroundImage = this.getIntent().getIntExtra("image", R.drawable.back1);
		View view = new View(this);
		view.setBackgroundDrawable(getResources().getDrawable(backgroundImage));
		this.setContentView(view);
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
