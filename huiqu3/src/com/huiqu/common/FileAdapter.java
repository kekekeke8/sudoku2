package com.huiqu.common;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huiqu.work.R;

public class FileAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mData;
	private ItemOptionPerformed optionPerformed;

	public FileAdapter(Context context, List<Map<String, Object>> data,ItemOptionPerformed listener) {
		super();
		mData = data;
		this.mInflater = LayoutInflater.from(context);
		this.optionPerformed = listener;
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
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
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
}
