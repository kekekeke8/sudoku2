package com.huiqu.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.utils.Huiqu;
import com.huiqu.work.LoginActivity;
import com.huiqu.work.R;

public class HuiquActivity extends NoTitleActivity {
	
	private static final int REQUEST_LOGIN = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(!Huiqu.I().checkLogin()){
			Intent intent = new Intent(this,LoginActivity.class);
			this.startActivityForResult(intent, REQUEST_LOGIN);
		}
	}
	public void initNavbar(String title) {
		((TextView)findViewById(R.id.txtNavTitle)).setText(title);
		this.findViewById(R.id.btnNavHome).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		this.findViewById(R.id.btnNavReturn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(getApplicationContext(), "return", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(REQUEST_LOGIN == requestCode){
			if(resultCode == RESULT_OK){
				
			}else{
				this.finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
