package com.huiqu.life;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.huiqu.common.HuiquActivity;
import com.huiqu.common.NoteAdapter;
import com.huiqu.model.NoteEntity;
import com.huiqu.model.UserEntity;
import com.huiqu.utils.Huiqu;
import com.huiqu.work.R;

@SuppressLint("HandlerLeak")
public class NoteActivity extends HuiquActivity implements OnClickListener, OnItemSelectedListener, OnScrollListener {

	private boolean showUI = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String mode = this.getIntent().getStringExtra("mode");
		if (mode != null)
			showUI = mode.equals("ui");
		
		if(showUI){
			setContentView(R.layout.activity_note);
			initNavbar(getString(R.string.label_note));
			listview = (ListView) findViewById(R.id.listview);
			listview.setOnItemSelectedListener(this);
			listview.setOnScrollListener(this);
			Button btnNewNote = (Button)findViewById(R.id.btnNewNote);
			btnNewNote.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					newNote();
				}
			});
			showData();
		}else{
			newNote();
		}
	}
	protected void newNote() {
		Intent intent = new Intent(this,NewNoteActivity.class);
		startActivityForResult(intent, 0);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK){
			String note = data.getStringExtra("note");
			if(note.trim().length() > 0){
				doPostNote(note);
			}
		}else{
			if (!showUI) finish();
		}
	}


	private ListView listview;
	private void showData() {
		final ProgressBar prog = (ProgressBar) findViewById(R.id.prog);		
		prog.setVisibility(View.VISIBLE);
		listview.setVisibility(View.INVISIBLE);
		
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				NoteAdapter adapter = (NoteAdapter) message.obj;
				listview.setAdapter(adapter);
				prog.setVisibility(View.INVISIBLE);
				listview.setVisibility(View.VISIBLE);
			}
		};

		new Thread() {
			@Override
			public void run() {
				Message message = handler.obtainMessage(0, new NoteAdapter(NoteActivity.this, getData()));
				handler.sendMessage(message);
			}
		}.start();
	}
	public List<NoteEntity> getData() {
		List<NoteEntity> list = new ArrayList<NoteEntity>();
		for (int i = 0;i < 120; i++) {
			NoteEntity note = new NoteEntity();
			note.setNote("大家好！书法俱乐部就要开始活动啦！7月24日下周二18:00——19:30首享大会议室，请大家准备好书法用具，毛笔：中号兼毫，硬笔：用签字笔即可，墨汁、宣纸、砚台工会统一购买。请大家每次携带好用笔参加学习。" + i);
			note.setUser_id("2222");
			note.setUserNickname("工会");
			note.setUser_icon(R.drawable.ic_launcher);
			note.setModify_date(new Date());
			list.add(note);
		}
		return list;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_note, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPost:
			String note = "";
			doPostNote(note);
			break;

		case R.id.btnCancel:

			break;
		}
	}

	public void doPostNote(String note) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_id", Huiqu.I().user.getId()));
		params.add(new BasicNameValuePair("note", note));
		Huiqu.I().service.call(Huiqu.I().config.getService_url(), params, new Handler() {
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

				if (!showUI) finish();
			}
		});
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisiableItem, int visableItemCount, int totalItemCount) {
		
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
}
