package com.huiqu.work;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.utils.Huiqu;

public class MainActivity extends NoTitleActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button b = (Button) this.findViewById(R.id.btnLogin);
		t = (TextView) this.findViewById(R.id.txtLostPassword);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Huiqu.I().methods.login("test@huiqu.com", "123456000", callBackHandler);
			}
		});
	}

	Handler callBackHandler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        JSONObject ret = null;
			try {
				ret = new JSONObject(msg.getData().getString("result"));
				Toast.makeText(getApplicationContext(), msg.what + ":[" + ret.getString("message") + "]", Toast.LENGTH_LONG).show();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	};

	TextView t = null;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
}
