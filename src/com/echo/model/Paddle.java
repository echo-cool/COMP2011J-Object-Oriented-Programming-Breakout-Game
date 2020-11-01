package com.echo.model;

import javax.swing.*;
import java.awt.*;

public class Paddle {
    public static int paddle_width = 60;
    public static int paddle_height =10;
    public static final int paddle_to_button = 60;
    private Rectangle hitBox;
    private static int paddleSpeed = 10;
    private int x=0;
    private int y=0;

    public Paddle() {
        x=(Game.SCREEN_WIDTH-paddle_width)/2;
        y=Game.SCREEN_HEIGHT-paddle_to_button;
        hitBox = new Rectangle(x, y, paddle_width, paddle_height);

    }
    public void setStartPosition(){
        x=(Game.SCREEN_WIDTH-paddle_width)/2;
        y=Game.SCREEN_HEIGHT-paddle_to_button;
    }
    public void moveLeft() {
        if(x>=paddleSpeed)
            x-=paddleSpeed;
        else
            x=0;
    }

    public void moveRight() {
        if(x+paddleSpeed<=Game.SCREEN_WIDTH-paddle_width)
            x+=paddleSpeed;
        else
            x=Game.SCREEN_WIDTH-paddle_width;;
    }

    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        //g2.setColor(Color.blue);
        //g2.fillRect(0,0,paddle_width, paddle_height);
        //g2.fillRect(x,y,paddle_width, paddle_height);
        Image img = new ImageIcon("imgs/paddle.png").getImage();
        g.drawImage(img,this.x,this.y,paddle_width,paddle_height,null);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public static int getPaddleSpeed() {
        return paddleSpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
