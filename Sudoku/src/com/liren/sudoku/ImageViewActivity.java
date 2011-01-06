package com.liren.sudoku;

import android.os.Bundle;
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
	}
}
