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
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.huiqu.common.HuiquActivity;
import com.huiqu.common.PhotoAdapter;
import com.huiqu.common.VoiceAdapter;
import com.huiqu.utils.Huiqu;
import com.huiqu.utils.Utils;
import com.huiqu.work.R;

public class PhotoActivity extends HuiquActivity implements OnClickListener {

	private boolean showUI = false;
	private ListView listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String mode = this.getIntent().getStringExtra("mode");
		if (mode != null) {
			if (mode.equals("ui")) {
				showUI = true;
				setContentView(R.layout.activity_photo);
				initNavbar(getString(R.string.label_photo));
				this.findViewById(R.id.btnCapture).setOnClickListener(this);
				this.findViewById(R.id.btnSelect).setOnClickListener(this);
				showfile();
			} else {
				cameraMethod();
			}
		} else {
			cameraMethod();
		}
	}

	private void showfile() {
		final ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
		listview = (ListView) findViewById(R.id.listview);
		prog.setVisibility(View.VISIBLE);
		listview.setVisibility(View.INVISIBLE);
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				PhotoAdapter adapter = (PhotoAdapter) message.obj;
				listview.setAdapter(adapter);
				prog.setVisibility(View.INVISIBLE);
				listview.setVisibility(View.VISIBLE);
			}
		};
		new Thread() {
			@Override
			public void run() {
				Message message = handler.obtainMessage(0, new PhotoAdapter(PhotoActivity.this, listfile()));
				handler.sendMessage(message);
			}
		}.start();
	}

	public List<Map<String, Object>> listfile() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		File[] files = Utils.getPhotoList();
		if (files != null) {
			for (File file : files) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", file.getName());
				map.put("info", "[" + new Date(file.lastModified()).toLocaleString() + "   " + Long.toString(file.length() / 1000) + "k]");
				map.put("img", android.R.drawable.alert_dark_frame);
				map.put("file", file);
				list.add(map);
			}
		}
		return list;
	}

	private void cameraMethod() {
		Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String target = Huiqu.I().config.photo_path + "/image" + dateFormater.format(new Date()) + ".jpg";
		Uri uri = Uri.fromFile(new File(target));
		imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		imageCaptureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		startActivityForResult(imageCaptureIntent, 1);
	}

	private void selectPhoto() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_photo, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:// 拍照
				Toast.makeText(this, getString(R.string.info_take_photo_complete), Toast.LENGTH_SHORT).show();
				if (this.showUI)
					showfile();
				break;
			case 0:
				Uri uriRecorder = data.getData();
				Cursor cursor = this.getContentResolver().query(uriRecorder, null, null, null, null);
				if (cursor.moveToNext()) {
					String source = cursor.getString(cursor.getColumnIndex("_data"));
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String target = Huiqu.I().config.photo_path + "/image" + dateFormater.format(new Date()) + source.substring(source.lastIndexOf("."), source.length());
					Utils.movefile(source, target);
					Toast.makeText(getApplicationContext(), target, Toast.LENGTH_LONG).show();
					if (this.showUI)
						showfile();
				}
				break;
			}
		}
		if (!this.showUI)
			this.finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCapture:
			cameraMethod();
			break;

		case R.id.btnSelect:
			selectPhoto();
			break;
		}
	}
}
