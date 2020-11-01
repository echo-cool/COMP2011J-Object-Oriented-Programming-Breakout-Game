package com.echo.model;

import java.awt.*;

public class Paddle {
    public static int paddle_width = 60;
    public static int paddle_height =10;
    public static final int paddle_to_button = 60;
    private static final int paddleSpeed = 10;
    private int x;
    private final int y;
    private boolean long_paddle = false;
    private int time_count = 0;
    private boolean stickyPaddle = false;
    private final Game game;

    public Paddle(Game game) {
        x=(Game.SCREEN_WIDTH-paddle_width)/2;
        y=Game.SCREEN_HEIGHT-paddle_to_button;
        this.game = game;

    }

    public void moveLeft() {
        if(x>=paddleSpeed)
            x-=paddleSpeed;
        else
            x=0;
        for(Ball ball: game.getPlayerBalls())
            if(ball.isStickied()) {
                ball.setX(ball.getX() - paddleSpeed);
                ball.setStickied(false);
            }

    }

    public void moveRight() {
        if(x+paddleSpeed<=Game.SCREEN_WIDTH-paddle_width)
            x+=paddleSpeed;
        else
            x=Game.SCREEN_WIDTH-paddle_width;
        for(Ball ball: game.getPlayerBalls())
            if(ball.isStickied()) {
                ball.setX(ball.getX() + paddleSpeed);
                ball.setStickied(false);
            }
    }

    public void draw(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.blue);
        //g2.fillRect(0,0,paddle_width, paddle_height);
        g2.fillRect(x,y,paddle_width, paddle_height);
        //Image img = new ImageIcon("imgs/paddle.png").getImage();
        //g.drawImage(img,this.x,this.y,paddle_width,paddle_height,null);
        if(long_paddle && paddle_width < 120){
            paddle_width++;
        }
        if(!long_paddle && paddle_width > 60){
            paddle_width--;
        }
        if(long_paddle){
            time_count++;
            if(time_count > 300){
                long_paddle = false;
                time_count = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getPaddle_width() {
        return paddle_width;
    }

    public void setLong_paddle(boolean long_paddle) {
        this.long_paddle = long_paddle;
        this.time_count = 0;
    }

    public boolean isStickyPaddle() {
        return stickyPaddle;
    }

    public void setStickyPaddle(boolean stickyPaddle) {
        this.stickyPaddle = stickyPaddle;
    }

}

