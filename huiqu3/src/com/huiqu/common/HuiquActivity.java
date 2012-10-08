package com.huiqu.common;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.work.R;

public class HuiquActivity extends NoTitleActivity {
	
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
