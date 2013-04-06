package com.sdeschene.lightsout;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.sdeschene.lightsout.model.LightButton;
import com.sdeschene.lightsout.model.LightsOutGame;
import com.sdeschene.lightsout.widgets.FontFitTextView;

// Displays UI for Lights Out
//
// Contains all game logic.

public class LightsOutGameActivity extends Activity {

	private LightsOutGame game;
	private FontFitTextView moves;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// remove title bar and make full screen.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set content
		setContentView(R.layout.lights_out_game_activity);

		// start new game
		newGame();
	}

	// default on click listener for light light button presses
	View.OnClickListener lightButtonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			LightButton temp = (LightButton) v;
			temp.flipLit();
			flipSurrounding(temp);
			updateMoveCount();
		}
	};

	// Flips the gems surrounding clicked gem.
	private void flipSurrounding(LightButton button) {
		int x = button.getXpos();
		int y = button.getYpos();
		if (x - 1 >= 0)
			game.buttons[x - 1][y].flipLit();
		if (y - 1 >= 0)
			game.buttons[x][y - 1].flipLit();
		if (x + 1 <= 4)
			game.buttons[x + 1][y].flipLit();
		if (y + 1 <= 4)
			game.buttons[x][y + 1].flipLit();
		if (checkWon()) {
			Toast.makeText(getApplicationContext(), "You won!",
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean checkWon() {
		for (int x = 0; x < 5; x++)
			for (int y = 0; y < 5; y++)
				if (game.buttons[x][y].isLit())
					return false;
		return true;
	}

	// updates the move count
	private void updateMoveCount() {
		game.moveCount++;
		moves.setText("Moves: " + game.moveCount);
	}

	private void reset() {
		// turn all the lights on
		for (int x = 0; x < 5; x++)
			for (int y = 0; y < 5; y++) {
				if (!game.buttons[x][y].isLit()) {
					game.buttons[x][y].flipLit();
				}
			}
		game.moveCount = 0;
		moves.setText("Moves: -");
	}

	// 
	@Override
	public void onBackPressed() {		
		showBackDialog();
	}

	private void newGame() {
		game = new LightsOutGame();

		game.buttons[0][0] = (LightButton) findViewById(R.id.gem_0x0);
		game.buttons[1][0] = (LightButton) findViewById(R.id.gem_1x0);
		game.buttons[2][0] = (LightButton) findViewById(R.id.gem_2x0);
		game.buttons[3][0] = (LightButton) findViewById(R.id.gem_3x0);
		game.buttons[4][0] = (LightButton) findViewById(R.id.gem_4x0);

		game.buttons[0][1] = (LightButton) findViewById(R.id.gem_0x1);
		game.buttons[1][1] = (LightButton) findViewById(R.id.gem_1x1);
		game.buttons[2][1] = (LightButton) findViewById(R.id.gem_2x1);
		game.buttons[3][1] = (LightButton) findViewById(R.id.gem_3x1);
		game.buttons[4][1] = (LightButton) findViewById(R.id.gem_4x1);

		game.buttons[0][2] = (LightButton) findViewById(R.id.gem_0x2);
		game.buttons[1][2] = (LightButton) findViewById(R.id.gem_1x2);
		game.buttons[2][2] = (LightButton) findViewById(R.id.gem_2x2);
		game.buttons[3][2] = (LightButton) findViewById(R.id.gem_3x2);
		game.buttons[4][2] = (LightButton) findViewById(R.id.gem_4x2);

		game.buttons[0][3] = (LightButton) findViewById(R.id.gem_0x3);
		game.buttons[1][3] = (LightButton) findViewById(R.id.gem_1x3);
		game.buttons[2][3] = (LightButton) findViewById(R.id.gem_2x3);
		game.buttons[3][3] = (LightButton) findViewById(R.id.gem_3x3);
		game.buttons[4][3] = (LightButton) findViewById(R.id.gem_4x3);

		game.buttons[0][4] = (LightButton) findViewById(R.id.gem_0x4);
		game.buttons[1][4] = (LightButton) findViewById(R.id.gem_1x4);
		game.buttons[2][4] = (LightButton) findViewById(R.id.gem_2x4);
		game.buttons[3][4] = (LightButton) findViewById(R.id.gem_3x4);
		game.buttons[4][4] = (LightButton) findViewById(R.id.gem_4x4);

		for (int x = 0; x < 5; x++)
			for (int y = 0; y < 5; y++) {
				game.buttons[x][y].setOnClickListener(lightButtonListener);
				game.buttons[x][y].setX(x);
				game.buttons[x][y].setY(y);
			}

		game.moveCount = 0;
		moves = (FontFitTextView) findViewById(R.id.moves);

		//
		// set new game button listener
		//
		Button newGame = (Button) findViewById(R.id.reset);
		newGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				reset();
			}
		});

		//
		// set quit game button listener
		//
		Button quit = (Button) findViewById(R.id.back);
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				showBackDialog();
			}
		});
	}
	
	private void showBackDialog(){
		Builder builder = new Builder(LightsOutGameActivity.this);
		builder.setTitle("Back to Title Screen?")
				.setMessage("Progress will not be saved")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								LightsOutGameActivity.this.finish();
								overridePendingTransition(
										R.animator.pull_in_from_left,
										R.animator.push_out_to_right);
							}
						})
				.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// do nothing
							}
						});
		builder.create().show();
	}
}
