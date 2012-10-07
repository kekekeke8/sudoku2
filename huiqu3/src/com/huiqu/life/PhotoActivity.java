package com.huiqu.life;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.huiqu.utils.Huiqu;
import com.huiqu.utils.Utils;
import com.huiqu.work.R;

public class PhotoActivity extends Activity implements OnClickListener {

	private boolean showUI = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String mode = this.getIntent().getStringExtra("mode");
		if (mode != null) {
			if (mode.equals("ui")) {
				showUI = true;
				setContentView(R.layout.activity_photo);
				this.findViewById(R.id.btnCapture).setOnClickListener(this);
				this.findViewById(R.id.btnSelectPhoto).setOnClickListener(this);
			} else {
				cameraMethod();
			}
		} else {
			cameraMethod();
		}
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
				Toast.makeText(this, "照片保存完毕", Toast.LENGTH_SHORT).show();
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
					if (!this.showUI) this.finish();
				}
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCapture:
			cameraMethod();
			break;

		case R.id.btnSelectPhoto:
			selectPhoto();
			break;
		}
	}
}
