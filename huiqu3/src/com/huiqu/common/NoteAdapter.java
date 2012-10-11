package com.huiqu.common;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huiqu.model.NoteEntity;
import com.huiqu.work.R;


public class NoteAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<NoteEntity> mNotes;
	
	public NoteAdapter(Context context, List<NoteEntity> notes) {
		super();
		mNotes = notes;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mNotes.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.vlist_simple_note, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.note = (TextView) convertView.findViewById(R.id.note);
			holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
			holder.modify_date = (TextView) convertView.findViewById(R.id.modify_date);
			convertView.setTag(holder); 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Context ctx = convertView.getContext();
		final ImageView icon = holder.icon;
		icon.setOnClickListener(new View.OnClickListener( ) {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(ctx, "Hello " + mNotes.get(position).getNote(), 500).show();
			}
		});
		icon.setBackgroundResource(mNotes.get(position).getUser_icon());
		holder.note.setText(mNotes.get(position).getNote());
		holder.user_name.setText((String) mNotes.get(position).getUserNickname());
		holder.modify_date.setText(mNotes.get(position).getModify_date().toLocaleString());
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return mNotes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	final class ViewHolder {
		public ImageView icon;
		public TextView user_name;
		public TextView note;
		public TextView modify_date;
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
