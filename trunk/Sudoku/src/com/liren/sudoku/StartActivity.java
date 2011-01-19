package com.liren.sudoku;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.liren.game.FullScreenActivity;
import com.liren.game.Sprite;
import com.liren.game.SpriteAction;
import com.liren.sudoku.sprites.Status;

public class StartActivity extends FullScreenActivity implements SpriteAction.OnSpriteClickListener {
	private MenuView view = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MenuView(this,MenuSprite.MENU_TYPE.START);
        view.setOnMenuItemClickListener(this);
        setContentView(view);
        soundPlayer.playBackground();
    }
    
    private Intent intent = null;
    public void onSpriteClick(Sprite v) {
		switch(v.id){
		case 0: //play
			soundPlayer.playRight();
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
			intent = new Intent(this,ImageViewActivity.class);
			intent.putExtra("image", R.drawable.back1);
			this.startActivity(intent);
			this.finish();
			break;
		case 4: //about
			intent = new Intent(this,ImageViewActivity.class);
			intent.putExtra("image", R.drawable.back1);
			this.startActivity(intent);
			this.finish();
			break;
		case 5: //exit
			StartActivity.this.finish();
			break;
		}				
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
		if(keyCode == KeyEvent.KEYCODE_BACK) return false;
		return super.onKeyDown(keyCode, event);
	}	
}