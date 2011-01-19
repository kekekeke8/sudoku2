package com.liren.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.liren.game.FullScreenActivity;
import com.liren.game.ResourceLoad.LoadEventListener;

public class SflashActivity extends FullScreenActivity implements Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int sflash = this.getIntent().getIntExtra("image", R.drawable.sflash);
		View view = new View(this);
		view.setBackgroundDrawable(getResources().getDrawable(sflash));
		this.setContentView(view);
		//new Handler().post(this);
		Resource.getInstance().Load(this);		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Intent intent = new Intent(this, StartActivity.class);
		this.startActivity(intent);
		this.finish();
		return false;
	}

	public void run() {
		Resource.getInstance().setOnProcessListener(new LoadEventListener() {
			@Override
			public void Process(int process) {
				System.out.println("Load resource Process:" + process);
			}
		});
		Resource.getInstance().Load(this);
		Intent intent = new Intent(this, StartActivity.class);
		this.startActivity(intent);
		//this.finish();
	}
}
