package com.huiqu.work;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FirstTimeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_first_time, menu);
        return true;
    }
}
