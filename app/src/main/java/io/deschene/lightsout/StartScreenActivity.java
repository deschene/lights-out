package io.deschene.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start_screen_activity);

        Button newGameButton = (Button) findViewById(R.id.new_game);
        Button quitButton = (Button) findViewById(R.id.quit);

        newGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreenActivity.this, LightsOutGameActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.pull_in_from_right,
                        R.animator.push_out_to_left);
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StartScreenActivity.this.finish();
                overridePendingTransition(R.animator.pull_in_from_left,
                        R.animator.push_out_to_right);
            }
        });
    }
}