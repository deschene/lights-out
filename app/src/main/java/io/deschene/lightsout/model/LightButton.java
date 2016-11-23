package io.deschene.lightsout.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import io.deschene.lightsout.R;

/**
 * Light Button that can either be lit or unlit. They also have an x and y position.
 */
public class LightButton extends Button {

    private boolean isLit;
    private int xPosition;
    private int yPosition;
    AlphaAnimation alphaDown;
    AlphaAnimation alphaUp;

    public LightButton(Context context) {
        super(context);
        initialize();
    }

    public LightButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        this.isLit = true;
        alphaDown = new AlphaAnimation(1.0f, 0.3f);
        alphaUp = new AlphaAnimation(0.3f, 1.0f);
        alphaDown.setDuration(500);
        alphaUp.setDuration(500);
        alphaDown.setFillAfter(true);
        alphaUp.setFillAfter(true);
        setBackground();
    }

    public boolean isLit() {
        return isLit;
    }

    public void flipLit() {
        isLit = !isLit;
        setBackground();
    }

    private void setBackground() {
        if (isLit) {
            setBackgroundColor(getResources().getColor(R.color.light_on));
            startAnimation(alphaUp);
        } else {
            startAnimation(alphaDown);
            setBackgroundColor(getResources().getColor(R.color.light_off));
        }
    }

    public void setX(int x) {
        xPosition = x;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setY(int y) {
        yPosition = y;
    }

    public int getYPosition() {
        return yPosition;
    }
}
