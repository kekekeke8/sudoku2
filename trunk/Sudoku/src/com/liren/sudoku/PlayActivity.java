package com.liren.sudoku;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.liren.game.FullScreenActivity;
import com.liren.game.SpriteAction;
import com.liren.game.Sprite;
import com.liren.sudoku.model.SQLHelper;
import com.liren.sudoku.model.SudokuModel;

public class PlayActivity extends FullScreenActivity implements SpriteAction.OnSpriteClickListener {
	private MenuView view = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MenuView(this,MenuSprite.MENU_TYPE.PLAY); //1  = Play
		view.setOnMenuItemClickListener(this);
        setContentView(view);
	}

	public void onSpriteClick(Sprite v) {
		android.util.Log.d("D","click menu " + v.id + " in PlayActivity");
		switch(v.id){
		case 6: //flash
			selectLevel = 1;
			createNewGame();
			break;
		case 7: //easy
			selectLevel = 2;
			createNewGame();
			break;
		case 8: //medium
			selectLevel = 3;
			createNewGame();
			break;
		case 9: //hard
			selectLevel = 4;
			createNewGame();break;
		case 10: //expert
			selectLevel = 5;
			createNewGame();break;
		case 11: //resume
			if(sudoku != null){
				game = new GameView(this, sudoku);			
				this.setContentView(game);
			}else{
				Toast.makeText(this, "No more game in resume.", Toast.LENGTH_SHORT).show();
			}
			break;
		}				
	}
	
	private SudokuModel sudoku = null;
	private GameView game = null;	
	private int selectLevel = 0;
	private void createNewGame(){
		if(sudoku != null){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(this.getResources().getString(R.string.confirm_abandon));
			builder.setTitle(this.getResources().getString(R.string.confirm));
			builder.setNegativeButton(this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					SQLHelper sql = new SQLHelper(PlayActivity.this);
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
			game = new GameView(PlayActivity.this, sudoku);			
			PlayActivity.this.setContentView(game);
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
		if((GameView.sudoku != null) &&(GameView.sudoku.Model != null)){
			SudokuModel model = GameView.sudoku.Model;
			if(model.finish == 0) model.finish = 1;		
			model.data = GameView.sudoku.GetSudokuDatas();
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
//		findViewById(R.id.btnResume).setVisibility(
//				sudoku == null ? View.INVISIBLE : View.VISIBLE);
		view.setResumeVisiable(sudoku != null);
		super.onResume();
	}

	
	
}
