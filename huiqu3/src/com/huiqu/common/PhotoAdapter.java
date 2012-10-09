package com.huiqu.common;

import java.io.File;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
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

import com.huiqu.utils.Gryphics;
import com.huiqu.work.R;

public class PhotoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Map<String, Object>> mData;

	public PhotoAdapter(Context context, List<Map<String, Object>> data) {
		super();
		mData = data;
		this.mInflater = LayoutInflater.from(context);
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
			convertView = mInflater.inflate(R.layout.vlist_photo, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.info = (TextView) convertView.findViewById(R.id.info);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final ImageView img = holder.img;
		img.setBackgroundResource((Integer) mData.get(position).get("img"));
		final File file = (File) (mData.get(position).get("file"));
		if ((file != null) && (file.exists())) {
			loadDrawable(file.getAbsolutePath(), new ImageCallback() {
				public void imageLoaded(Bitmap imageDrawable, String imageUrl) {
					img.setImageBitmap(imageDrawable);
				}
			});
		}

		holder.title.setText((String) mData.get(position).get("title"));
		holder.info.setText((String) mData.get(position).get("info"));
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
	}

	public void loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Bitmap) message.obj, imageUrl);
			}
		};
		new Thread() {
			@Override
			public void run() {
				Bitmap bitmap = Gryphics.getImageThumbnail(imageUrl, 60, 60);
				Message message = new Message();
				message.obj = bitmap;
				handler.sendMessage(message);
			}
		}.start();
	}

	public interface ImageCallback {
		public void imageLoaded(Bitmap image, String imageUrl);
	}

}
