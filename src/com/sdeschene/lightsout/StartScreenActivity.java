package com.sdeschene.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartScreenActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.start_screen_activity);

		Button newGame = (Button) findViewById(R.id.new_game);
		Button quit = (Button) findViewById(R.id.quit);

		newGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartScreenActivity.this, LightsOutGameActivity.class);
				startActivity(intent);
				overridePendingTransition(R.animator.pull_in_from_right,
						R.animator.push_out_to_left);
			}
		});

		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StartScreenActivity.this.finish();
				overridePendingTransition(R.animator.pull_in_from_left,
						R.animator.push_out_to_right);
			}
		});
	}
}