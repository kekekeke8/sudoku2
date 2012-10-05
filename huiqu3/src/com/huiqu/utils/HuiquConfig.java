package com.huiqu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import android.graphics.Path;
import android.os.Environment;

public class HuiquConfig {
	private String service_url = "http://huiqu.sinaapp.com/server/s_login.php";
	public String home_path = "/huiqu";
	public String voice_path = "/huiqu/voice";
	public String photo_path = "/huiqu/photo";
	public String video_path = "/huiqu/video";
	private String config_filename = "/huiqu/config.json";
	
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

	public void saveConfig() throws IOException {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
			File targetFile = new File(sdCardDir.getCanonicalPath() + config_filename);
			RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
			raf.seek(targetFile.length());
			raf.close();
		}
	}

	public void loadConfig() throws FileNotFoundException, IOException {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 获取SD卡对应的存储目录
			File sdCardDir = Environment.getExternalStorageDirectory();
			// 获取指定文件对应的输入流
			FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + config_filename);
			// 将指定输入流包装成BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			StringBuilder sb = new StringBuilder("");
			String line = null;
			// 一直读，直到读到最后跳出
			while ((line = br.readLine()) != null) {
				// 一直追加读出的内容
				sb.append(line);
			}
			// 返回读出的内容，并把它转化为字符串
			//return sb.toString();
		}

	}
}
