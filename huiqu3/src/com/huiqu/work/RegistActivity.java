package com.huiqu.work;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.huiqu.common.NoTitleActivity;
import com.huiqu.model.AbstractEntity;
import com.huiqu.utils.Huiqu;
import com.huiqu.utils.HuiquServiceHandler;

public class RegistActivity extends NoTitleActivity {
	private Button btnRegist = null;
	private Spinner spUnits = null;
	private EditText txtEmail = null;
	private EditText txtPassword = null;
	private EditText txtWorkNo = null;
	private EditText txtWorkPass = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		InitControls();
		showRegistView();
	}

	private View progress_view = null;
	private View regist_view = null;

	private void showProgressView() {
		progress_view.setVisibility(View.VISIBLE);
		regist_view.setVisibility(View.INVISIBLE);
	}

	private void showRegistView() {
		progress_view.setVisibility(View.INVISIBLE);
		regist_view.setVisibility(View.VISIBLE);
	}

	public void InitControls() {
		progress_view = (View) findViewById(R.id.regist_process_view);
		regist_view = (View) findViewById(R.id.regist_view);
		btnRegist = (Button) findViewById(R.id.btnRegist);
		btnRegist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();
				String workno = txtWorkNo.getText().toString();
				String workpass = txtWorkPass.getText().toString();
				String school = selectedSchoolId;
				if (email.length() < 5) {
					txtEmail.requestFocus();
					txtEmail.setText("");
				} else if (password.length() < 6) {
					txtPassword.requestFocus();
					txtPassword.setText("");
				} else if ((workno.length() > 0) && (workpass.length() > 0)) {
					showProgressView();
					Huiqu.I().methods.regist(email, password, school, workno, workpass, new HuiquServiceHandler() {
						@Override
						public void handleResult(JSONObject data) {
							Toast.makeText(getApplicationContext(), "用户注册成功,请重新登录", Toast.LENGTH_LONG).show();
							setResult(RESULT_OK);
							finish();
						}

						@Override
						public void handleError(int ret, String message) {
							Toast.makeText(getApplicationContext(), "注册用户失败:" + message, Toast.LENGTH_LONG).show();
							showRegistView();
						}
					});
				}
			}
		});

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		spUnits = (Spinner) findViewById(R.id.spUnits);
		txtWorkNo = (EditText) findViewById(R.id.txtWorkNo);
		txtWorkPass = (EditText) findViewById(R.id.txtWorkPass);
		spUnits.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterview, View view, int position, long l) {
				selectedSchoolId = school_ids.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterview) {
				selectedSchoolId = "0";
			}
		});
		initSchools();
	}

	private String selectedSchoolId = "0";

	private void initSchools() {
		Huiqu.I().methods.getschools(new HuiquServiceHandler() {
			@Override
			public void handleResult(JSONObject data) {
				try {
					if (data.getInt("rowcount") > 0) {
						JSONArray rows = data.getJSONArray("rows");
						for (int i = 0; i < rows.length(); i++) {
							JSONObject jo = rows.getJSONObject(i);
							school_ids.add(jo.getString("id"));
							school_names.add(jo.getString("name"));
						}
						Log.e(Huiqu.TAG_DEBUG, "getSchools schools count " + data.getInt("rowcount"));
						spUnits.setAdapter(getUnitAdapter());
					} else {
						Log.e(Huiqu.TAG_DEBUG, "getSchools empty");
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e(Huiqu.TAG_ERROR, "getSchools " + e);
				}
			}

			@Override
			public void handleError(int ret, String message) {
				Log.e(Huiqu.TAG_DEBUG, "getSchools error:" + message);
			}
		});
	}

	private List<String> school_names = new ArrayList<String>();
	private List<String> school_ids = new ArrayList<String>();

	private SpinnerAdapter getUnitAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, school_names);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);

		return adapter;
	}
}
