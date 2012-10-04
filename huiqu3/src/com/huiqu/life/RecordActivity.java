package com.huiqu.life;

import com.huiqu.work.R;
import com.huiqu.work.R.layout;
import com.huiqu.work.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RecordActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_record, menu);
        return true;
    }
}
