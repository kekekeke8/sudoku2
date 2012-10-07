package com.huiqu.life;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.utils.Huiqu;
import com.huiqu.utils.Utils;
import com.huiqu.work.R;

public class RecordActivity extends NoTitleActivity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String mode = this.getIntent().getStringExtra("mode");
		if (mode != null) {
			if (mode.equals("ui")) {
				showUI = true;
				setContentView(R.layout.activity_record);
				this.findViewById(R.id.btnRecord).setOnClickListener(this);
				showfile();
			} else {
				this.onClick(null);
			}
		} else {
			this.onClick(null);
		}

	}

	private void showfile() {
		SimpleAdapter adapter = new SimpleAdapter(this, listfile(), R.layout.vlist, 
				new String[] { "title", "info", "img" }, 
				new int[] { R.id.title, R.id.info, R.id.img });
		ListView listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(adapter);
	}

	public List<Map<String, Object>> listfile() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		File[] files = Utils.getVoiceList();
		for (File file : files) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", file.getName());
			map.put("info", Long.toString(file.length()));
			map.put("img", android.R.drawable.presence_audio_away);
			list.add(map);
		}
		return list;
	}

	private boolean showUI = false;
	Handler callBackHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {

				// Toast.makeText(getApplicationContext(), "",
				// Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_record, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				Uri uriRecorder = data.getData();
				Cursor cursor = this.getContentResolver().query(uriRecorder, null, null, null, null);
				if (cursor.moveToNext()) {
					String source = cursor.getString(cursor.getColumnIndex("_data"));
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String target = Huiqu.I().config.voice_path + "/voice" + dateFormater.format(new Date()) + source.substring(source.lastIndexOf("."), source.length());
					Utils.movefile(source, target);
					showfile();
					if (!this.showUI)
						this.finish();
					
				}
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/*");
		startActivityForResult(intent, 0);
	}
}
