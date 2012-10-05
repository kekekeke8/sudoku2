package com.huiqu.life;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
			if(mode.equals("ui"))
			{
				showUI = true;
				setContentView(R.layout.activity_record);
				this.findViewById(R.id.btnRecord).setOnClickListener(this);
			}else{
				this.onClick(null);
			}
		} else {
			this.onClick(null);
		}
		
	}
	
	private boolean showUI = false;

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
					Toast.makeText(getApplicationContext(), target, Toast.LENGTH_LONG).show();
					if(!this.showUI) this.finish();
				}
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr");
		startActivityForResult(intent, 0);
	}
}
