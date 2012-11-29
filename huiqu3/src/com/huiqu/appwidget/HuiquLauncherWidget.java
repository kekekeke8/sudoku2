package com.huiqu.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.huiqu.life.NoteActivity;
import com.huiqu.life.PhotoActivity;
import com.huiqu.life.RecordActivity;
import com.huiqu.work.MainActivity;
import com.huiqu.work.R;

public class HuiquLauncherWidget extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.launcher);
            views.setOnClickPendingIntent(R.id.btnLauncherWork, pendingIntent);
            
            Intent intent2 = new Intent(context, PhotoActivity.class);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
            views.setOnClickPendingIntent(R.id.btnLauncherPhoto, pendingIntent2);
            
            Intent intent3 = new Intent(context, NoteActivity.class);
            PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0, intent3, 0);
            views.setOnClickPendingIntent(R.id.btnLauncherNote, pendingIntent3);
            
            Intent intent4 = new Intent(context, RecordActivity.class);
            PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 0, intent4, 0);
            views.setOnClickPendingIntent(R.id.btnlauncherVoice, pendingIntent4);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
	}

}
