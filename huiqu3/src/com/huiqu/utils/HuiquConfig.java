package com.huiqu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Environment;
import android.util.Log;

public class HuiquConfig {
	private String service_url = "http://192.168.43.17/1399/1399/server/service.php";
	public String home_path = "/huiqu";
	public String voice_path = "/huiqu/voice";
	public String photo_path = "/huiqu/photo";
	public String video_path = "/huiqu/video";
	private String config_filename = "/huiqu/huiqu.config";
	
	public HuiquConfig(){
		init_paths();
	}

	private void init_paths() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File sdcard = Environment.getExternalStorageDirectory();
			home_path = sdcard.getPath() + home_path;
			voice_path = sdcard.getPath() + voice_path;
			photo_path = sdcard.getPath() + photo_path;
			video_path = sdcard.getPath() + video_path;
			config_filename = sdcard.getPath() + config_filename;
			Utils.createDirectory(home_path);
			Utils.createDirectory(voice_path);
			Utils.createDirectory(photo_path);
			Utils.createDirectory(video_path);
		}
	}

	

	public String getService_url() {
		return service_url;
	}

	public void setService_url(String service_url) {
		this.service_url = service_url;
	}

	public void saveConfig(List<NameValuePair> params) throws IOException {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File targetFile = new File(config_filename);
			if(targetFile.exists()) targetFile.delete();
			RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
			for(int i = 0;i < params.size();i++){
				raf.writeUTF(params.get(i).getName() + "=" + params.get(i).getValue() + "\n");
			}
			raf.seek(targetFile.length());
			raf.close();
			Log.d(Huiqu.TAG_DEBUG,"saveConfig sdcard ok");
		}else{
			Log.d(Huiqu.TAG_DEBUG,"loadConfig sdcard is not ready");
		}
	}

	public List<NameValuePair> loadConfig() throws FileNotFoundException, IOException {
		List<NameValuePair> configs = new ArrayList<NameValuePair>();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				FileInputStream fis = new FileInputStream(config_filename);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					String [] temp = line.split("=");
					configs.add(new BasicNameValuePair(temp[0], temp[1]));
					Log.d(Huiqu.TAG_DEBUG,"loadConfig  read:" + line.trim());
				}
		}else{
			Log.d(Huiqu.TAG_DEBUG,"loadConfig sdcard is not ready");
		}
		return configs;
	}
}
