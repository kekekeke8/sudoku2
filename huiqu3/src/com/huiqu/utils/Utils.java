package com.huiqu.utils;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

public class Utils {
	public String record(Activity activity){
//		Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//		activity.startActivityForResult(intent, 0); 
//		if(resultCode == RESULT_OK {    
//			Uri uri = data.getData();
//			Toast.makeText(activity, uri.toString(), Toast.LENGTH_LONG);
//		}
		return "";
	}
	
	
	static public void movefile(String source,String target) {
		File file_source = new File(source);
		File file_target = new File(target);
		file_source.renameTo(file_target);
	}
	
	static public void createDirectory(String path) {
		try {
			File huiqu_path = new File(path);
			if(!huiqu_path.exists()){
				huiqu_path.mkdir();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
