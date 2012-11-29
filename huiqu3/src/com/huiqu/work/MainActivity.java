package com.huiqu.work;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.huiqu.common.HuiquActivity;
import com.huiqu.life.NoteActivity;
import com.huiqu.life.PhotoActivity;
import com.huiqu.life.RecordActivity;
import com.huiqu.utils.Utils;

public class MainActivity extends HuiquActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Utils.addShortcut(this, R.string.app_name, R.drawable.ic_launcher);
		findViewById(R.id.btnNote).setOnClickListener(this);
		findViewById(R.id.btnRecord).setOnClickListener(this);
		findViewById(R.id.btnPhoto).setOnClickListener(this);
		//findViewById(R.id.btnRecord).setOnClickListener(this);
		findViewById(R.id.btnWork).setOnClickListener(this);
		//findViewById(R.id.btnAbout).setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnRecord:
			Intent intent = new Intent();
	        intent.putExtra("mode","ui");
	        intent.setClass(MainActivity.this, RecordActivity.class);
	        this.startActivity(intent);
			break;
		case R.id.btnPhoto:
			Intent iPhoto = new Intent();
			iPhoto.putExtra("mode","ui");
			iPhoto.setClass(MainActivity.this, PhotoActivity.class);
	        this.startActivity(iPhoto);
			break;
		case R.id.btnNote:
			Intent iNote = new Intent();
			iNote.putExtra("mode","ui");
			iNote.setClass(MainActivity.this, NoteActivity.class);
	        this.startActivity(iNote);
			break;
		case R.id.btnWork:
			Intent iwork = new Intent();
			iwork.putExtra("mode","ui");
			iwork.setClass(MainActivity.this, KaoQinActivity.class);
	        this.startActivity(iwork);
			break;
		}
	}
	private void exit(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(this.getResources().getString(R.string.confirm_exit));
		builder.setTitle(this.getResources().getString(R.string.confirm));
		
		builder.setPositiveButton(this.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
			}
		});
		builder.setNegativeButton(this.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if(keyCode == KeyEvent.KEYCODE_BACK) {exit(); return false;}
		return super.onKeyDown(keyCode, event);
	}	
	
	

}
