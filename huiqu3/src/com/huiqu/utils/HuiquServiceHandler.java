package com.huiqu.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class HuiquServiceHandler extends Handler {
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		Bundle response = msg.getData();
		parseResponse(response);
	}
	
	private void parseResponse(Bundle response) {
		if (response.getInt("ret") == HuiquService.RESULT_OK) { // 通讯成功
			try {
				JSONObject result = new JSONObject(response.getString("result"));
				int r = result.getInt("ret");
				if (r == HuiquService.RESULT_OK) { // 交易成功
					handleResult(result.getJSONObject("data"));
				} else {
					handleError(result.getInt("ret"),result.getString("message"));
				}
			} catch (JSONException e) {
				handleError(HuiquService.EXCEPTION,e.toString());
			}
		} else {
			handleError(response.getInt("ret"),response.getString("result"));
		}
	}

    public abstract void handleResult(JSONObject data);
    public abstract void handleError(int ret,String message);
}
