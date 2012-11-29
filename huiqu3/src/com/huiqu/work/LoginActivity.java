package com.huiqu.work;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.model.UserEntity;
import com.huiqu.utils.Huiqu;
import com.huiqu.utils.HuiquServiceHandler;

public class LoginActivity extends NoTitleActivity {

	private Button btnRegist = null;
	private Button btnLogin = null;
	private EditText txtEmail = null;
	private EditText txtPassword = null;
	private CheckBox chbRememberPassword = null;
	private TextView txtLostPassword = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		InitControls();
	}

	private View progress_view = null;
	private View login_view = null;

	private void showProgressView() {
		progress_view.setVisibility(View.VISIBLE);
		login_view.setVisibility(View.INVISIBLE);
	}

	private void showLoginView() {
		progress_view.setVisibility(View.INVISIBLE);
		login_view.setVisibility(View.VISIBLE);
	}
	public void InitControls() {
		progress_view = (View) findViewById(R.id.login_process_view);
		login_view = (View) findViewById(R.id.login_view);
		btnRegist = (Button) findViewById(R.id.btnRegist);
		btnRegist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
				LoginActivity.this.startActivity(intent);
			}
		});

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				doLogin();
			}
		});

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		chbRememberPassword = (CheckBox) findViewById(R.id.chbRememberPassword);
		txtLostPassword = (TextView) findViewById(R.id.txtLostPassword);
		txtLostPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				forGetPassword();
			}
		});
	}

	protected void forGetPassword() {
		// TODO Auto-generated method stub

	}

	protected void doLogin() {
		final String email = txtEmail.getText().toString();
		final String password = txtPassword.getText().toString();
		if (email.length() < 5) {
			txtEmail.requestFocus();
			txtEmail.setText("");
		} else if (password.length() < 6) {
			txtPassword.requestFocus();
			txtPassword.setText("");
		} else {
			showProgressView();
			Huiqu.I().methods.login(email, password, new HuiquServiceHandler() {
				@Override
				public void handleResult(JSONObject data) {
					Huiqu.I().user = new UserEntity(data);
					if(chbRememberPassword.isChecked()) saveLoginParams(email,password);
					setResult(RESULT_OK);
					finish();
				}

				@Override
				public void handleError(int ret, String message) {
					Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
					txtPassword.requestFocus();
					txtPassword.setText("");
					showLoginView();
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.setResult(RESULT_CANCELED);
			this.finish();
			return true;
		}
		return super.onKeyLongPress(keyCode, event);
	}
	public void saveLoginParams(String login_id,String login_password) {
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("login_id",login_id));
			params.add(new BasicNameValuePair("login_password",login_password));
			Huiqu.I().config.saveConfig(params);
		} catch (Exception e) {
			Log.d(Huiqu.TAG_DEBUG,"LoginActivity onCreate exception:" + e);
		}
	}


}
