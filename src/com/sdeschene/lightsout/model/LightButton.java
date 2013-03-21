package com.sdeschene.lightsout.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.sdeschene.lightsout.R;

//
// Light Button
//
// Button used in the Lights Out Game. They can either be lit
// or unlit. They also have an x and y position.

public class LightButton extends Button {

	private boolean lit;
	private int xPos, yPos;
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

	public LightButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	private void initialize() {
		this.lit = true;
		alphaDown = new AlphaAnimation(1.0f, 0.3f);
		alphaUp = new AlphaAnimation(0.3f, 1.0f);
		alphaDown.setDuration(500);
		alphaUp.setDuration(500);
		alphaDown.setFillAfter(true);
		alphaUp.setFillAfter(true);
		setBackground();
	}

	public boolean isLit() {
		return lit;
	}

	public void flipLit() {
		this.lit = !lit;
		setBackground();
	}

	private void setBackground() {

		if (lit) {
			this.setBackgroundColor(getResources().getColor(R.color.light_on));
			this.startAnimation(alphaUp);
		} else {
			this.startAnimation(alphaDown);			
			this.setBackgroundColor(getResources().getColor(R.color.light_off));

		}
	}

	public void setX(int x) {
		this.xPos = x;
	}

	public int getXpos() {
		return this.xPos;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public int getYpos() {
		return this.yPos;
	}
}
