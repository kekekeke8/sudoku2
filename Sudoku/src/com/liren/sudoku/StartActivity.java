package com.liren.sudoku;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.liren.game.FullScreenActivity;
import com.liren.game.Sprite;
import com.liren.game.SpriteAction;
import com.liren.sudoku.model.SQLHelper;
import com.liren.sudoku.sprites.Status;

public class StartActivity extends FullScreenActivity implements SpriteAction.OnSpriteClickListener {
	private MenuView view = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MenuView(this,MenuSprite.MENU_TYPE.START);
        view.setOnMenuItemClickListener(this);
        setContentView(view);
    }
    
    private Intent intent = null;
    public void onSpriteClick(Sprite v) {
		switch(v.id){
		case 0: //play
			intent = new Intent(this,PlayActivity.class);
			this.startActivity(intent);
			this.finish();
			break;
		case 1: //status
			intent = new Intent(this,Status.class);
			intent.putExtra("image", R.drawable.back1);
			this.startActivity(intent);
			this.finish();
			break;
		case 2: //options
			intent = new Intent(this,ImageViewActivity.class);
			intent.putExtra("image", R.drawable.back1);
			this.startActivity(intent);
			this.finish();
			break;
		case 3: //help
			intent = new Intent(this,InfoActivity.class);
			intent.putExtra("info", R.string.game_description);
			this.startActivity(intent);
			this.finish();
			break;
		case 4: //about
			intent = new Intent(this,InfoActivity.class);
			intent.putExtra("info", R.string.game_about);
			this.startActivity(intent);
			this.finish();
			break;
		case 5: //exit
			exit();
			break;
		}				
	}

    private void exit(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(this.getResources().getString(R.string.confirm_exit));
		builder.setTitle(this.getResources().getString(R.string.confirm));
		
		builder.setPositiveButton(this.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setNegativeButton(this.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				StartActivity.this.finish();
			}
		});
		builder.show();
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if(keyCode == KeyEvent.KEYCODE_BACK) {exit(); return false;}
		return super.onKeyDown(keyCode, event);
	}	
}