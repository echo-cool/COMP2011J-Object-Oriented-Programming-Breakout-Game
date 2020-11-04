package com.echo.model;

import java.awt.*;


public class Ball {
    private static final int ballRadius = 7;
    private int x;
    private int y=200;
    private int vx=0;
    private int vy=5;
    private int tmp_vx=0;
    private int tmp_vy=5;
    private boolean alive = true;
    private boolean stop = false;
    private boolean stickied = false;
    private Game game;

    public Ball(Game game) {
        this.game = game;
        this.x = game.getPaddle().getX()+(game.getPaddle().paddle_width)/2;
    }

    public Ball(int x, int y, int vx, int vy, Game game) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.tmp_vy = vy;
        this.game = game;

    }

    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.magenta);
        g2.fillOval(x,y,ballRadius*2,ballRadius*2);

        //Image img = new ImageIcon("imgs/ball.png").getImage();
        //g.drawImage(img,this.x,this.y,2*ball_r,2*ball_r,null);
    }
    public boolean moveBounce(Paddle paddle, Game game){
        if(game.paused || stop){
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
            if(vy == 0)
                vy = 5;
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
            //vx=(int)(Math.random()*5-5);
        }
        //Drop
        else if(y+ballRadius*2>Game.SCREEN_HEIGHT){
            x=(Game.game_box_w)/2;
            y=Game.game_box_h-Paddle.paddle_to_button-Paddle.paddle_height*2;
            vx = 0;
            vy = 0;
            alive = false;
            //Game.paused = true;
        }
        if(this.collide(paddle.getX(),paddle.getY(),Paddle.paddle_width,Paddle.paddle_height)){
            if(this.x+ 2 * ballRadius > paddle.getX() && this.x < paddle.getX()+Paddle.paddle_width)
                this.BounceY();
            else
                this.BounceX();
            if(!(this.x > paddle.getX() + Paddle.paddle_width/3 && this.x < paddle.getX() + Paddle.paddle_width*2/3)){
                if(this.x < paddle.getX() + Paddle.paddle_width/3 && this.x > paddle.getX())
                    vx=(int)(-Math.random()*5);
                else
                    vx=(int)(Math.random()*5);
            }
            return true;
        }
        return false;
    }

    public void BounceX(){
        vx=-vx;
    }
    public void BounceY(){
        vy=-vy;
    }

    public boolean collide(int object_x,int object_y,int object_width,int object_height){
        Rectangle object = new Rectangle(object_x,object_y,object_width,object_height);
        Rectangle ball = new Rectangle(x,y,ballRadius*2,ballRadius*2);
        return object.intersects(ball);
//        if(this.x + 2 * ballRadius >= object_x && this.x <= object_x+object_width && this.y+ 2 * ballRadius >= object_y && this.y <= object_y + object_height){
//            return true;
//        }
//        return false;
    }
    public void increaseSpeed(){
        this.tmp_vy = vx*2;
        this.tmp_vy = vy*2;
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

    public boolean isAlive() {
        return alive;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStickied() {
        return stickied;
    }

    public void setStickied(boolean stickied) {
        this.stickied = stickied;
    }
}
