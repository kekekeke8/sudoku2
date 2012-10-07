package com.huiqu.work;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    @SuppressLint("HandlerLeak")
	Handler callBackHandler = new Handler(){
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
	        
	    }
	};
}
