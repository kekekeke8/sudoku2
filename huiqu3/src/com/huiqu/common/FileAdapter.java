package com.huiqu.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.work.R;

public class FileAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mData;
	private ItemOptionPerformed optionPerformed;
	private boolean showImage = false;
	
	public FileAdapter(Context context, List<Map<String, Object>> data,ItemOptionPerformed listener) {
		super();
		mData = data;
		this.mInflater = LayoutInflater.from(context);
		if(listener != null)
			this.optionPerformed = listener;
	}
	public FileAdapter(Context context, List<Map<String, Object>> data,ItemOptionPerformed listener,boolean showImage) {
		super();
		mData = data;
		this.mInflater = LayoutInflater.from(context);
		this.optionPerformed = listener;
		
		this.showImage = showImage;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.vlist, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.info = (TextView) convertView.findViewById(R.id.info);
			holder.option = (Button) convertView.findViewById(R.id.btnOption);
			if(this.optionPerformed == null)holder.option.setVisibility(View.INVISIBLE);
			convertView.setTag(holder); 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final ImageView img = holder.img;
		img.setBackgroundResource((Integer) mData.get(position).get("img"));
		if(this.showImage){
			final File file = (File)(mData.get(position).get("file"));
			if((file != null) &&(file.exists())){
				loadDrawable(file.getAbsolutePath(), new ImageCallback() {
	                public void imageLoaded(Drawable imageDrawable, String imageUrl) {                 
	                	//img.setBackgroundDrawable(imageDrawable);
	                }
	            });
			}
		}
			
		holder.title.setText((String) mData.get(position).get("title"));
		holder.info.setText((String) mData.get(position).get("info"));
		holder.option.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(optionPerformed != null){
					optionPerformed.itemOptionOnClick(mData.get(position),position,(Button)v);
				}
			}
		});
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	final class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button option;
	}
	
	public void loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
//                Drawable drawable = Drawable.createFromPath(imageUrl);
//                Message message = new Message();
//                message.obj = drawable;
//                handler.sendMessage(message);
            }
        }.start();
    }
	
	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}
