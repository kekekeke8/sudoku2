package com.huiqu.life;

import android.os.Bundle;
import android.view.Menu;

import com.huiqu.common.HuiquActivity;
import com.huiqu.work.R;

public class AccountActivity extends HuiquActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        this.initNavbar("Account");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_account, menu);
        return true;
    }
}
