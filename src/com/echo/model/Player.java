package com.echo.model;

import com.echo.presentation.PlayerListener;

public class Player {
    private PlayerListener playerListener;
    private Paddle paddle;

    public Player(PlayerListener playerListener, Paddle paddle) {
        this.playerListener = playerListener;
        this.paddle = paddle;
    }
    public void move(){
        if (playerListener.isLeft()) {
            paddle.moveLeft();
        } else if (playerListener.isRight()) {
            paddle.moveRight();
        }



    }
}
