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
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.huiqu.common.HuiquActivity;
import com.huiqu.common.ItemOptionPerformed;
import com.huiqu.common.VoiceAdapter;
import com.huiqu.utils.Huiqu;
import com.huiqu.utils.Utils;
import com.huiqu.work.R;

public class RecordActivity extends HuiquActivity implements OnClickListener, ItemOptionPerformed, OnCompletionListener {
	private ListView listview;
	private MediaPlayer player = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		initNavbar(getString(R.string.label_voice));
		this.findViewById(R.id.btnRecord).setOnClickListener(this);
		this.findViewById(R.id.btnSelect).setOnClickListener(this);
		showfile();
	}

	private void showfile() {
		final ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
		listview = (ListView) findViewById(R.id.listview);
		prog.setVisibility(View.VISIBLE);
		listview.setVisibility(View.INVISIBLE);

		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				VoiceAdapter adapter = (VoiceAdapter) message.obj;
				listview.setAdapter(adapter);
				prog.setVisibility(View.INVISIBLE);
				listview.setVisibility(View.VISIBLE);
			}
		};

		new Thread() {
			@Override
			public void run() {
				Message message = handler.obtainMessage(0, new VoiceAdapter(RecordActivity.this, listfile(), RecordActivity.this));
				handler.sendMessage(message);
			}
		}.start();
	}

	public List<Map<String, Object>> listfile() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		File[] files = Utils.getVoiceList();
		for (File file : files) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", file.getName());
			map.put("info", "[" + new Date(file.lastModified()).toLocaleString() + "   " + Long.toString(file.length() / 1000) + "k]");
			map.put("img", android.R.drawable.presence_audio_away);
			map.put("file", file);
			list.add(map);
		}
		return list;
	}

	private boolean showUI = false;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
				}
			}
			break;
		case 1:
			if (resultCode == RESULT_OK) {
				showfile();
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnRecord){
			doRecord();
		}else if(v.getId() == R.id.btnSelect){
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("audio/*");
			startActivityForResult(intent, 0);
		}
	}

	public void doRecord() {
		Intent intent = new Intent();
		intent.setClass(RecordActivity.this, VoiceNoteActivity.class);
		startActivityForResult(intent,1);
	}

	private Map<String, Object> selectedItem = null;

	@Override
	public void onCompletion(MediaPlayer arg0) {
		this.selectedItem = null;
		getString(R.string.label_play);
	}

	private Button lastButton = null;

	@Override
	public void itemOptionOnClick(Map<String, Object> item, int position, Button button) {
		try {
			listview.setSelection(position);
			listview.requestFocus();
			if (this.selectedItem != item) {
				if (item != null) {
					File file = (File) item.get("file");
					if (file.exists()) {
						if (player != null) {
							if (player.isPlaying())
								player.stop();
							player.release();
							player = null;
						}
						player = new MediaPlayer();
						player.setOnCompletionListener(this);
						player.setDataSource(file.getAbsolutePath());
						player.prepare();
						player.start();
						if (lastButton != null)
							lastButton.setText(getString(R.string.label_play));
						button.setText(getString(R.string.label_stop));
					}
					this.selectedItem = item;
				}
			} else {
				if (player.isPlaying())
					player.stop();
				this.selectedItem = null;
				button.setText(getString(R.string.label_play));
			}
			lastButton = button;
		} catch (Exception e) {
			e.printStackTrace();
			getString(R.string.label_play);
		}
	}
}