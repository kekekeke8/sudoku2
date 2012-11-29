package com.huiqu.work;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.huiqu.common.HuiquActivity;
import com.huiqu.utils.Utils;

public class KaoQinActivity extends HuiquActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaoqin);
		initNavbar(getString(R.string.label_voice));
		initControls();
	}

	private void initControls() {
//		btnRecord = (Button) findViewById(R.id.btnRecord);
//		btnPlay = (Button) findViewById(R.id.btnPlay);
//		label_display = (TextView) findViewById(R.id.label_display);
//		btnRecord.setOnClickListener(this);
//		btnPlay.setOnClickListener(this);
	
	}
}
