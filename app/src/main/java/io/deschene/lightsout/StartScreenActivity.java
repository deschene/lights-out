package io.deschene.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        Button newGameButton = findViewById(R.id.new_game);
        Button quitButton = findViewById(R.id.quit);

        newGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LightsOutGameActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.pull_in_from_right,
                    R.animator.push_out_to_left);
        });

        quitButton.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.animator.pull_in_from_left,
                    R.animator.push_out_to_right);
        });
    }
}
