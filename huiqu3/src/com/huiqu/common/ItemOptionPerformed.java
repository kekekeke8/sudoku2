package com.huiqu.common;

import java.util.Map;

import android.widget.Button;

public interface ItemOptionPerformed {
	void itemOptionOnClick(Map<String,Object> selectedItem,int position,Button button);
}
