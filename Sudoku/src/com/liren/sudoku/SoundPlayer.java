package com.liren.sudoku;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.liren.sudoku.R;

public class SoundPlayer {
	private static SoundPlayer _soundPlayer;
	public static SoundPlayer getInstance(Context context){
		if(_soundPlayer == null)
			_soundPlayer = new SoundPlayer(context);
		return _soundPlayer;
	}
	public static SoundPlayer getInstance(){
		return _soundPlayer;
	}
	private Context context = null;
	private SoundPlayer(Context context) {
		this.context = context;
		this.initSounds();
	}

	private SoundPool _soundPool;
	private static HashMap<Integer, Integer> _soundPoolMap = new HashMap<Integer, Integer>();

	private void initSounds() {
		_soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 100);
		_soundPoolMap.put(R.raw.select, _soundPool.load(context, R.raw.select, 1));
		_soundPoolMap.put(R.raw.error, _soundPool.load(context, R.raw.error, 1));
		_soundPoolMap.put(R.raw.explodes,_soundPool.load(context, R.raw.explodes, 1));
		_soundPoolMap.put(R.raw.level_complete,_soundPool.load(context, R.raw.level_complete, 1));
		_soundPoolMap.put(R.raw.right,_soundPool.load(context, R.raw.right, 1));
	}

	public void playSound(int sound) {
		AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;
		_soundPool.play(_soundPoolMap.get(sound), volume, volume, 1, 0, 1f);
	}

	public void playError() {
		playSound(R.raw.error);
	}

	public void playSelect() {
		playSound(R.raw.select);
	}
	
	public void playRight() {
		playSound(R.raw.right);
	}

	public void playExplode() {
		playSound(R.raw.explodes);
	}
	
	public void playLevelComplete(){
		playSound(R.raw.level_complete);
	}
}

