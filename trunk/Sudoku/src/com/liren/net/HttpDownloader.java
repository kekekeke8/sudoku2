package com.liren.net;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpDownloader {
	public String download(String urlStr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try{
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while((line = buffer.readLine()) != null){
				sb.append(line);
			}
		}catch(Exception e){			
			e.printStackTrace();
		}finally{
			try{
				buffer.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
