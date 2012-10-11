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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!Huiqu.I().checkLogin()){
			Intent intent = new Intent(this,LoginActivity.class);
			this.startActivity(intent);
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
				Toast.makeText(getApplicationContext(), "return", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
