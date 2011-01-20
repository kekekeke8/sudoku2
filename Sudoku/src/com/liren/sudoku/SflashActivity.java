package com.liren.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.liren.game.ResourceLoad.LoadEventListener;

public class SflashActivity extends Activity implements Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sflash);
//		int sflash = this.getIntent().getIntExtra("image", R.drawable.sflash);
//		View view = new View(this);
//		view.setBackgroundDrawable(getResources().getDrawable(sflash));
//		this.setContentView(view);
		new Handler().postDelayed(this, 1000);
		
	}
	
	public void run() {
		Resource.getInstance().setOnProcessListener(new LoadEventListener() {
			@Override public void Process(int process) {
				System.out.println("Load resource Process:" + process);
			}
		});
		SoundPlayer.getInstance(this);
		Resource.getInstance().Load(this);	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startGame();
	}

	private void startGame() {
		Intent intent = new Intent(this, StartActivity.class);
		this.startActivity(intent);
		this.finish();
	}
}
