package com.huiqu.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huiqu.common.HuiquActivity;
import com.huiqu.work.R;

public class NewNoteActivity extends HuiquActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_new_note);
		initNavbar(getString(R.string.label_newnote));
		final EditText edtNote =  (EditText)findViewById(R.id.edtNote);
		Button btnPost = (Button)findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();   
		        Bundle b = new Bundle();   
		        b.putString("note",edtNote.getText().toString());   
		        i.putExtras(b);   
				setResult(RESULT_OK, i);
				finish();
			}
		});
	}
}
