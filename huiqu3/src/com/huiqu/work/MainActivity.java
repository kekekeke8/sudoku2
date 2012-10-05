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
		
		Huiqu.I();
		
//		btnRecord = (Button) this.findViewById(R.id.btnRecord);
//		btnAccount = (Button) this.findViewById(R.id.btnAccount);
//		btnPhoto = (Button) this.findViewById(R.id.btnPhoto);
//		btnNote = (Button) this.findViewById(R.id.btnNote);
//		btnAbout = (Button) this.findViewById(R.id.btnAbout);
//		btnWork = (Button) this.findViewById(R.id.btnWork);
//		
//		btnRecord.setOnClickListener(this);
	}
//	private Button btnRecord = null;
//	private Button btnAbout = null;
//	private Button btnNote = null;
//	private Button btnPhoto = null;
//	private Button btnAccount = null;
//	private Button btnWork = null;
	
	@SuppressLint("HandlerLeak")
	Handler callBackHandler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        JSONObject ret = null;
			try {
				ret = new JSONObject(msg.getData().getString("result"));
				Toast.makeText(getApplicationContext(), msg.what + ":[" + ret.getString("message") + "]", Toast.LENGTH_LONG).show();
			} catch (JSONException e) {
				e.printStackTrace();
			}
	        
	    }
	};

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
		}
	}
}
