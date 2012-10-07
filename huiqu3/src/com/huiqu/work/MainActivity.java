package com.huiqu.work;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.life.NoteActivity;
import com.huiqu.life.PhotoActivity;
import com.huiqu.life.RecordActivity;
import com.huiqu.utils.Huiqu;

public class MainActivity extends NoTitleActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnNote).setOnClickListener(this);
		findViewById(R.id.btnRecord).setOnClickListener(this);
		findViewById(R.id.btnPhoto).setOnClickListener(this);
		findViewById(R.id.btnRecord).setOnClickListener(this);
		findViewById(R.id.btnWork).setOnClickListener(this);
		findViewById(R.id.btnAbout).setOnClickListener(this);
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
		}
	}
}
