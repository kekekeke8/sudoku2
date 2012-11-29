package com.huiqu.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;

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
				huiqu_path.mkdirs();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static File[] getPhotoList(){
		FilenameFilter f = new FilenameFilter() {
			@Override
			public boolean accept(File file, String arg1) {
				return arg1.endsWith(".jpg");
			}
		};
		return listfile(Huiqu.I().config.photo_path,f);
	}
	
	public static File[] getVoiceList(){
		FilenameFilter f = new FilenameFilter() {
			@Override
			public boolean accept(File file, String arg1) {
				return arg1.endsWith(".amr") || arg1.endsWith(".mp3") || arg1.endsWith(".m4a");
			}
		};
		return listfile(Huiqu.I().config.voice_path,f);
	}
	
	public static File[] getVideoList(){
		FilenameFilter f = new FilenameFilter() {
			@Override
			public boolean accept(File file, String arg1) {
				return arg1.endsWith(".3gp") || arg1.endsWith(".mp4");
			}
		};
		return listfile(Huiqu.I().config.video_path,f);
	}
	
	public static File[] listfile(String path,FilenameFilter filter){
		File filepath = new File(path);
		if(filepath.exists()){
			return filepath.listFiles(filter);
		}return null;
	}
	
	static public void getFileList(File path, Map<String, String> fileList){
	     if(path.isDirectory()){
	         File[] files = path.listFiles();
	         if(null == files) return;
	         for(int i = 0; i < files.length; i++){
	             getFileList(files[i], fileList);
	         }
	     }
	     else{
	         String filePath = path.getAbsolutePath();
	         String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
	         fileList.put(fileName, filePath);
	     }
	 }
	public static  void addShortcut(Activity context,int shortcut_name,int icon){         
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");   //快捷方式的名称         
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(shortcut_name));  //       
		shortcut.putExtra("duplicate", false);                
		ComponentName comp = new ComponentName(context.getPackageName(), "."+context.getLocalClassName());  
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));         
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(context,context.getClass()));          
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, icon);         
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);                       
		context.sendBroadcast(shortcut);     
	}
}