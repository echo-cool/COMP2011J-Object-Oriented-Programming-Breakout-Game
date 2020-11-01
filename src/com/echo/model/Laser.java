package com.echo.model;

import java.awt.*;

public class Laser {
    private int x;
    private int y;
    private final int width;
    private int height = 0;
    private final Game game;
    private boolean activate = false;


    public Laser(Game game) {
        this.game = game;
        x = game.getPaddle().getX()+ Paddle.getPaddle_width()/3;
        y = game.getPaddle().getY();
        width = Paddle.getPaddle_width()/3;
    }

    public void draw(Graphics g){
        if(activate) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.magenta);
            g2.fillRect(x, y, width, height);
            if (height < Game.SCREEN_HEIGHT)
                x = game.getPaddle().getX()+ Paddle.getPaddle_width()/3;
            int speed = 20;
            y -= speed;
                height += speed;
        }
        if(height >= Game.SCREEN_HEIGHT){
            activate = false;
        }
        if(!activate && height > 0){
            height = 0;
            y = game.getPaddle().getY();
        }
    }

    public boolean collide(int object_x,int object_y,int object_width,int object_height){
        return this.x + width > object_x && this.x < object_x + object_width && this.y + height > object_y && this.y < object_y + object_height;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

}
