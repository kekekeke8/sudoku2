package com.liren.sudoku;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liren.sudoku.model.SQLHelper;
import com.liren.sudoku.model.SudokuModel;

public class Play extends Activity implements View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		findViewById(R.id.btnFlash).setOnClickListener(this);
		findViewById(R.id.btnEasy).setOnClickListener(this);
		findViewById(R.id.btnMedium).setOnClickListener(this);
		findViewById(R.id.btnHard).setOnClickListener(this);
		findViewById(R.id.btnExpert).setOnClickListener(this);
		findViewById(R.id.btnResume).setOnClickListener(this);
	}

	private SudokuModel sudoku = null;
	private Game game = null;

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFlash:
			selectLevel = 1;
			createNewGame();
			break;
		case R.id.btnEasy:
			selectLevel = 2;
			createNewGame();
			break;
		case R.id.btnMedium:
			selectLevel = 3;
			createNewGame();
			break;
		case R.id.btnHard:
			selectLevel = 4;
			createNewGame();
			break;
		case R.id.btnExpert:
			selectLevel = 5;
			createNewGame();
			break;
		case R.id.btnResume:
			if(sudoku != null){
				game = new Game(this, sudoku);			
				this.setContentView(game);
			}else{
				Toast.makeText(this, "No more game in resume.", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
	
	int selectLevel = 0;
	private void createNewGame(){
		if(sudoku != null){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(this.getResources().getString(R.string.confirm_abandon));
			builder.setTitle(this.getResources().getString(R.string.confirm));
			builder.setNegativeButton(this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					SQLHelper sql = new SQLHelper(Play.this);
					sql.abandonGame();
					LoadNewGame();
				}
			});
			builder.setPositiveButton(this.getResources().getString(R.string.no), new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			builder.show();
		}else{
			LoadNewGame();
		}
	}
	private void LoadNewGame() {
		sudoku = LoadGame(selectLevel);
		if(sudoku != null){
			game = new Game(Play.this, sudoku);			
			Play.this.setContentView(game);
		}else{
			Toast.makeText(this, "No more game in this level.", Toast.LENGTH_SHORT).show();
		}
	}

	private SudokuModel LoadGame(int level) {
		SQLHelper sql = new SQLHelper(this);
		try {
			if(level == -1)
				return sql.loadResumeGame();
			else
				return sql.loadGame(level);
		} catch (Exception ex) {
			Log.d("E",ex.getMessage());
			return null;
		} finally {
			sql.close();
		}
	}

	@Override
	protected void onPause() {
		if((Game.sudoku != null) &&(Game.sudoku.Model != null)){
			SudokuModel model = Game.sudoku.Model;
			if(model.finish == 0) model.finish = 1;		
			model.data = Game.sudoku.GetSudokuDatas();
			Log.d("D","Save game:" + model);
			SQLHelper sql = new SQLHelper(this);
			sql.update(model);
		}
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
		sudoku = LoadGame(-1); // Load resume game.
		findViewById(R.id.btnResume).setVisibility(
				sudoku == null ? View.INVISIBLE : View.VISIBLE);
		super.onResume();
	}
	
	
}
