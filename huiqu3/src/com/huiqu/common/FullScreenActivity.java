package com.huiqu.common;

import android.os.Bundle;
import android.view.WindowManager;

public class FullScreenActivity extends NoTitleActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
