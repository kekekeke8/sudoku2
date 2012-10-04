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
import com.huiqu.utils.Application;

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
				Application.I().service.login("test@huiqu.com", "123456000", callBackHandler);
			}
		});
	}

	//-----------------------------------------------------------------------
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

//	Runnable runnable = new Runnable(){
//	    @Override
//	    public void run() {
//	    	String uriAPI = "http://huiqu.sinaapp.com/server/s_login.php";
//			HttpPost httpRequest = new HttpPost(uriAPI);
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			params.add(new BasicNameValuePair("login_id", "test@huiqu.com"));
//			params.add(new BasicNameValuePair("login_password", "123456"));
//			try {
//				httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//				HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//					String strResult = EntityUtils.toString(httpResponse.getEntity());
//					Message msg = new Message();
//					msg.obj = strResult;
//					msg.what = 0;
//					handler2.sendMessage(msg);
//				} else {
//					Message msg = new Message();
//					msg.obj = httpResponse.getStatusLine().toString();
//					msg.what = 1;
//					handler2.sendMessage(msg);
//				}
//			} catch (Exception e) {
//				Message msg = new Message();
//				msg.what = 9999;
//				msg.obj = e;
//				handler2.sendMessage(msg);
//			}
//	    }
//	};
	//-----------------------------------------------------------------------
	
	TextView t = null;

//	private void serviceCall() {
//		new Thread(new Runnable() {
//			public void run() {
//				login();
//			}
//		}).run();
//	}
//
//	private Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) { // 当有消息发送出来的时候就执行Handler的这个方法
//			super.handleMessage(msg);
//			//UI
//			Toast.makeText(getApplicationContext(), msg.what + ":" + msg.obj.toString(), Toast.LENGTH_LONG).show();
//		}
//	};
//
//	private void login() {
//		String uriAPI = "http://huiqu.sinaapp.com/server/s_login.php";
//		HttpPost httpRequest = new HttpPost(uriAPI);
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("login_id", "test@huiqu.com"));
//		params.add(new BasicNameValuePair("login_password", "123456"));
//		try {
//			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				String strResult = EntityUtils.toString(httpResponse.getEntity());
//				Message msg = new Message();
//				msg.obj = strResult;
//				msg.what = 0;
//				handler.sendMessage(msg);
//			} else {
//				Message msg = new Message();
//				msg.obj = httpResponse.getStatusLine().toString();
//				msg.what = 1;
//				handler.sendMessage(msg);
//			}
//		} catch (Exception e) {
//			Message msg = new Message();
//			msg.what = 9999;
//			msg.obj = e;
//			handler.sendMessage(msg);
//		}
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
}
