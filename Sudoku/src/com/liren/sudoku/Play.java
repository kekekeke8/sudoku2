package com.liren.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liren.sudoku.model.SQLHelper;
import com.liren.sudoku.model.SudokuModel;

public class Play extends Activity implements View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		findViewById(R.id.btnFlash).setOnClickListener(this);
		findViewById(R.id.btnResume).setOnClickListener(this);
		
	}

	private SudokuModel sudoku = null;
	private Game game = null;

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFlash:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("纭閫�嚭鍚楋紵");
			builder.setTitle("鎻愮ず");
			builder.setNegativeButton("纭畾", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					Play.this.setTitle("Confirm");
				}
			});
			builder.setPositiveButton("鍙栨秷", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					Play.this.setTitle("Cancel");
				}
			});
			builder.show();
			break;
		case R.id.btnResume:
			game = new Game(this, sudoku);
			this.setContentView(game);
			break;
		default:
			break;
		}
	}

	private SudokuModel Load(int level) {
		SQLHelper sql = new SQLHelper(this);
		try {
			SudokuModel model = sql.play(level);
			Log.d("D","load sudoku:" +model.toString());
			return model;
		} catch (Exception ex) {
			Log.d("E",ex.getMessage());
			return null;
		} finally {
			sql.close();
		}
	}

	@Override
	protected void onPause() {
		Log.d("D","Pause Save the game.");
		SudokuModel model = Game.sudoku.Model;
		if(model.finish == 0) model.finish = 1;
		model.data = Game.sudoku.GetSudokuDatas();
		Log.d("D","Save game:" + model);
		SQLHelper sql = new SQLHelper(this);
		sql.update(model);
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.d("D","onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d("D","onResume Load the game and start..........");
		sudoku = Load(-1); // Load resume game.
		findViewById(R.id.btnResume).setVisibility(
				sudoku == null ? View.INVISIBLE : View.VISIBLE);
		super.onResume();
	}
	
	
}
