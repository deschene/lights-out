package io.deschene.lightsout.model;

/**
 * Game Class. Contains variables stored during gameplay.
 */
public final class LightsOutGame {

    public int moveCount;
    public LightButton[][] buttons;


    private static final int BOARD_X_SIZE = 5;
    private static final int BOARD_Y_SIZE = 5;

    public LightsOutGame() {
        buttons = new LightButton[BOARD_X_SIZE][BOARD_Y_SIZE];
        moveCount = 0;
    }
}
