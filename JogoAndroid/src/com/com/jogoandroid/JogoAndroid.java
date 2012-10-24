package com.com.jogoandroid;

import android.os.Bundle;
import android.app.Activity;

public class JogoAndroid extends Activity {
	private GameView game;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		game = new GameView(this);
		setContentView(game);
	}

	protected void onDestroy() {
		game.release();
	}

}
