package com.echo.model;

import javax.swing.*;
import java.awt.*;


public class Ball {
    private static final int ballRadius = 10;
    private int x=(Game.game_box_w)/2;
    private int y=Game.game_box_h-Paddle.paddle_to_button-Paddle.paddle_height*2;

    public int vx=0;
    public int vy=5;
    public int tmp_vx=0;
    public int tmp_vy=5;
    public boolean alive = true;
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.magenta);
        g2.fillOval(x,y,ballRadius*2,ballRadius*2);

        //Image img = new ImageIcon("imgs/ball.png").getImage();
        //g.drawImage(img,this.x,this.y,2*ball_r,2*ball_r,null);
    }
    public void moveAndBounce(Paddle paddle, Game game){
        if(game.paused){
            if(vx != 0)
                tmp_vx = vx;
            if(vy != 0)
                tmp_vy = vy;
            vx = 0;
            vy = 0;
        }
        else{
            if(tmp_vx != 0)
                vx = tmp_vx;
            if(tmp_vy != 0)
                vy = tmp_vy;
            tmp_vx = 0;
            tmp_vy = 0;
        }
        x+=vx;
        y+=vy;
        //Left Wall
        if(x<0) {
            x = 0;
            BounceX();
        }
        //Right Wall
        else if(x+ballRadius*2>Game.game_box_w){
            x=Game.game_box_w-ballRadius*2;
            BounceX();
        }
        //Top Wall
        else if(y<=25){
            y=25;
            BounceY();
            vx=(int)(Math.random()*5-5);
        }
        //Drop
        else if(y+ballRadius*2>Game.SCREEN_HEIGHT){
            x=(Game.game_box_w)/2;;
            y=Game.game_box_h-Paddle.paddle_to_button-Paddle.paddle_height*2;
            vx = 0;
            vy = 5;
            alive = false;
            //Game.paused = true;
        }
        if(this.collide(paddle.getX(),paddle.getY(),Paddle.paddle_width,Paddle.paddle_height)){
            this.BounceY();
            vx=(int)(Math.random()*10-5);
        }
    }

    public void BounceX(){
        vx=-vx;
    }
    public void BounceY(){
        vy=-vy;
    }
    public boolean isOUt(){
        if (getY()+ballRadius*2==Game.game_box_h)
            return true;
        else
            return false;
    }
    public boolean collide(int object_x,int object_y,int object_width,int object_height){
        if(this.x+2*ballRadius>object_x && this.x<object_x+object_width && this.y+2*ballRadius>object_y&&this.y<object_y+object_height){
            return true;
        }
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getVx(){
        return vx;
    }
    public int getVy(){
        return vy;
    }



}
