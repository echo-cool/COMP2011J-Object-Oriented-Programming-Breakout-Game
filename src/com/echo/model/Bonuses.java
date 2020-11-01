package com.echo.model;

import java.awt.*;
import java.util.ArrayList;

public class Bonuses {
    private final int type;
    private int x;
    private int y;
    private int drop_speed_vx = 0;
    private int drop_speed_vy = 4;
    private final int radius = 10;
    private boolean show = false;
    private final Color color;
    private Game game;

    public Bonuses(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y+50;
        if(type == 1)
            this.color = Color.BLUE;
        else if(type == 2)
            this.color = Color.CYAN;
        else if(type == 3)
            this.color = Color.GREEN;
        else if(type == 4)
            this.color = Color.RED;
        else
            this.color = null;
    }
    public void draw(Graphics g){
        if(show) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(color);
            if (type == 1)
                g2.drawString("Multi-ball", x, y);
                //g2.fillOval(x,y,radius*2,radius*2);
            else if (type == 2)
                g2.drawString("Wide-Paddle", x, y);
                //g2.drawRect(x,y,radius,radius);
            else if (type == 3)
                g2.drawString("Sticky-Paddle", x, y);
                //g2.drawRoundRect(x,y,radius,radius,radius,radius);
            else if (type == 4)
                g2.drawString("Laser", x, y);
            else
                g2.drawString("null", x, y);
        }
        //Image img = new ImageIcon("imgs/ball.png").getImage();
        //g.drawImage(img,this.x,this.y,2*ball_r,2*ball_r,null);
    }
    public void move(){
        x+=drop_speed_vx;
        y+=drop_speed_vy;
        if(y+radius*2>Game.SCREEN_HEIGHT){
            show = false;
            drop_speed_vx = 0;
            drop_speed_vy = 0;
            //Game.paused = true;
        }
    }

    public void checkHit(int x, int y, int width, int height) {
        if (this.x + 2 * radius > x && this.x < x + width && this.y + 2 * radius > y && this.y < y + height) {
            show = false;
            if(type == 1)
                multiBall();
            if(type == 2)
                widePaddle();
            if(type == 3)
                stickyPaddle();
            if (type == 4)
                laser();


        }
    }

/* TODO
*1.Multi-ball - Every ball is split into three moving off at slightly different angles from each other
*2.Wide-Paddle - The paddle doubles in width for a period of time
3.Sticky-Paddle - When the ball hits the paddle it does not bounce but sticks and can be started again by the player
4.Laser - The paddle can fire lasers for a short period of time to destroy bricks directly above the paddle
 */
    public void multiBall(){
        ArrayList<Ball> tmp = new ArrayList<>(game.getPlayerBalls());
        for(Ball ball : tmp){
            game.getPlayerBalls().add(new Ball(ball.getX(), ball.getY(), ball.getVx() - 3, ball.getVy(), game));
            game.getPlayerBalls().add(new Ball(ball.getX(), ball.getY(), ball.getVx() + 3, ball.getVy(), game));
        }
    }
    public void widePaddle(){
        game.getPaddle().setLong_paddle(true);
    }
    public void stickyPaddle(){
        game.getPaddle().setStickyPaddle(true);
    }
    public void laser(){
        game.getLaser().setActivate(true);

    }

    public int getRadius() {
        return radius;
    }

    public boolean isShow() {
        return show;
    }

    public Color getColor() {
        return color;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
