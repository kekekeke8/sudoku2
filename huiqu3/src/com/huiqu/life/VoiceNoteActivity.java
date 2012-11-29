package com.huiqu.life;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.huiqu.common.HuiquActivity;
import com.huiqu.utils.Huiqu;
import com.huiqu.work.R;

public class VoiceNoteActivity extends HuiquActivity implements OnClickListener {
	private static final String LOG_TAG = "VOICE";
	private Button btnRecord = null;
	private Button btnPlay = null;
	private Button btnRemove = null;
	
	private TextView label_display = null;
	private boolean isRecording = false;
	private boolean isPlaying = false;
	private MediaRecorder mRecorder;
	
	private MediaPlayer mPlayer;
	private Timer timer;
	private Date startDatetime = null;
	private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voicenote);
		initNavbar(getString(R.string.label_voice));
		initControls();
	}

	private void initControls() {
		btnRecord = (Button) findViewById(R.id.btnRecord);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		label_display = (TextView) findViewById(R.id.label_display);
		btnRecord.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		
		btnRemove  = (Button)findViewById(R.id.btnRemove);
		btnRemove.setOnClickListener(this);
	}

	private void startPlaying() {
		 try {
			 if(new File(voice_filename).exists()){
				 mPlayer = new MediaPlayer();
				 mPlayer.setDataSource(voice_filename);
				 mPlayer.prepare();
				 mPlayer.start();
			 }
		 } catch (IOException e) {
			 Log.e(LOG_TAG, "startPlaying() failed");
		 }

		isPlaying = true;
		//btnPlay.setText(R.string.label_stop);
		btnPlay.setSelected(true);
		startTimer();
	}

	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;

		isPlaying = false;
		//btnPlay.setText(R.string.label_play);
		btnPlay.setSelected(false);
		label_display.setText("00:00");
		stopTimer();
	}

	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(genFilename());
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
		mRecorder.start();

		isRecording = true;
		//btnRecord.setText(R.string.label_stoprecord);
		startTimer();
		btnRecord.setSelected(true);
	}

	private void stopRecording() {
		 mRecorder.stop();
		 mRecorder.release();
		 mRecorder = null;

		isRecording = false;
		btnRecord.setText(R.string.label_record);
		label_display.setText("00:00");

		stopTimer();
		btnRecord.setSelected(false);
	}

	private void startTimer() {
		startDatetime = new Date();
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				String info = new Date().toLocaleString();
				Message msg = timerHandler.obtainMessage(0, info);
				timerHandler.sendMessage(msg);
				Log.d(LOG_TAG, showDifftime(startDatetime, new Date()));
			}
		}, 100, 200);
	};

	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			String info = new Date().toLocaleString();
			Message msg = timerHandler.obtainMessage(0, info);
			timerHandler.sendMessage(msg);
			Log.d(LOG_TAG, showDifftime(startDatetime, new Date()));
		}
	};

	@SuppressLint("HandlerLeak")
	private void stopTimer() {
		timer.cancel();
		timer = null;
	}

	@SuppressLint("HandlerLeak")
	Handler timerHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			label_display.setText(showDifftime(startDatetime, new Date()));
			super.handleMessage(msg);
		}
	};

	private String voice_filename = ""; 
	private String genFilename() {
		voice_filename = Huiqu.I().config.voice_path + "/voice" + dateFormater.format(new Date()) + ".amr";
		return voice_filename;
	}

	protected String showDifftime(Date begin, Date end) {
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		// long day1 = between / (24 * 3600);
		// long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % (3600) / 60;
		long second1 = between % 60;

		String result = String.format("%02d", minute1) + ":" + String.format("%02d", second1);
		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRecord:
			if (!isRecording) { // 录音
				startRecording();
			} else {
				stopRecording();
			}
			break;

		case R.id.btnPlay:
			if ((!isPlaying) && (!isRecording)) {
				startPlaying();
			} else {
				stopPlaying();
			}
			break;
		case R.id.btnRemove:
			if (!isRecording) stopRecording();
			if (!isPlaying) stopPlaying();
			File f = new File("voice_filename");
			if(f.exists()){
				f.delete();
			}
			break;
		}
	}

}
