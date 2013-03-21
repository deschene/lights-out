package com.sdeschene.lightsout.model;


// Game Class
//
// Contains variables stored during gameplay.

public class LightsOutGame {

	public LightButton[][] buttons;
	public int moveCount;
	
	public LightsOutGame() {
		buttons = new LightButton[5][5];
		moveCount = 0;
	}
}
