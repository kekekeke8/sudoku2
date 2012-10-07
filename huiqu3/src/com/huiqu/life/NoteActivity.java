package com.huiqu.life;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.utils.Huiqu;
import com.huiqu.work.R;

public class NoteActivity extends NoTitleActivity implements OnClickListener {

	private boolean showUI = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		String mode = this.getIntent().getStringExtra("mode");
		if (mode != null) showUI = mode.equals("ui");
		this.findViewById(R.id.btnPost).setOnClickListener(this);
		this.findViewById(R.id.btnCancel).setOnClickListener(this);
	}
	ProgressDialog progress = null;
	@SuppressLint("HandlerLeak")
	Handler callBackHandler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        JSONObject ret = null;
			try {
				progress.dismiss();
				ret = new JSONObject(msg.getData().getString("result"));
				Toast.makeText(getApplicationContext(), msg.what + ":[" + ret.getString("message") + "]", Toast.LENGTH_LONG).show();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			if(!showUI) finish();
	    }
	};
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
//			progress = new ProgressDialog(getApplicationContext());
//			progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//			progress.setIcon(R.drawable.icon_write_on);
//			progress.setMessage("Loading...");  
//			progress.setCancelable(false);  
//			progress.show();
			doPostNote(note);
			break;

		case R.id.btnCancel:

			break;
		}
	}

	public void doPostNote(String note) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login_id", Integer.toString(Huiqu.I().user.getId())));
		params.add(new BasicNameValuePair("note", note));
		Huiqu.I().service.call(Huiqu.I().config.getService_url(), params, callBackHandler);
	}
}
