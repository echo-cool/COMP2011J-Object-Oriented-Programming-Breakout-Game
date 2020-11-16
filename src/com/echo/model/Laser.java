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
            Point top = new Point(x+(width)/2,y-10);
            Point left = new Point(x,y);
            Point right = new Point(x+width,y);
            int[] xPoints = new int[]{top.x, left.x, right.x};
            int[] yPoints = new int[]{top.y,left.y,right.y};
            g2.fillPolygon(new Polygon(xPoints,yPoints,3));
//            if (height < Game.SCREEN_HEIGHT)
//                x = game.getPaddle().getX()+ Paddle.getPaddle_width()/3;
            int speed = 20;
            y -= speed;
            if(height < 120)
                height += speed;
        }
        if(y < 0 && activate){
            //System.out.println("sdfsadf");
            activate = false;
            y = game.getPaddle().getY();
            height = 0;
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
        this.x = game.getPaddle().getX()+ Paddle.getPaddle_width()/3;
    }

}
