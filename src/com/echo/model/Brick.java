package com.echo.model;

import java.awt.*;
import java.util.ArrayList;

public class Brick {
    private static int BRICK_WIDTH;
    private static int BRICK_HEIGHT;
    private boolean Alive = true;
    private int x,y;
    private Color color;
    private int bonuses = 0;

    public Brick(int x, int y, int BRICK_HEIGHT,int BRICK_WIDTH) {
        this.x = x;
        this.y = y;
        this.BRICK_HEIGHT = BRICK_HEIGHT;
        this.BRICK_WIDTH = BRICK_WIDTH;
    }
    public Brick(int x, int y, int BRICK_HEIGHT, int BRICK_WIDTH, int bonuses) {
        this.x = x;
        this.y = y;
        this.BRICK_HEIGHT = BRICK_HEIGHT;
        this.BRICK_WIDTH = BRICK_WIDTH;
        this.bonuses = bonuses;
    }

    public void setAlive(boolean alive) { this.Alive = alive; }
    public void setColor(Color color) { this.color = color; }

    public static int getWIDTH() { return BRICK_WIDTH; }
    public static int getHEIGHT() { return BRICK_HEIGHT; }
    public boolean isAlive() { return Alive; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Color getColor() { return color; }
    public String getColor_name(){ return String.valueOf(getColor());}
    public int getBonuses() { return bonuses; }

    //    实例化所有砖块
    public static ArrayList<Brick> initBricks(int brick_row, int brick_per_row){

//        实例化一个list
        ArrayList<Brick> bricks=new ArrayList<>();
        int brick_width=(Game.game_box_w-(brick_per_row-1)*Game.brick_sep)/brick_per_row;
        int brick_height = 8;
//        通过for循环将所有砖块实例化后添加到list中
        for(int i=0;i<brick_row;i++){
            for(int j=0;j<brick_per_row;j++){
                int x=j * brick_width+4*(j+1);
                int y=i * brick_height+4*i;
                Brick brick=new Brick(x,y+30,brick_height,brick_width);
//                设置空白砖块
                int a=(int)(Math.random()*9+1);
                int b=(int)(Math.random()*9+1);
                if (j==b||i==b){
                    brick.setAlive(false);
                }
//                用switch来初始化颜色
                switch (a){
                    case 1:
                    case 8:
                        brick.setColor(Color.GREEN);
                        break;
                    case 2:
                    case 7:
                        brick.setColor(Color.RED);
                        break;
                    case 3:
                    case 5:
                    case 9:
                        brick.setColor(Color.BLUE);
                        break;
                    case 4:
                        brick.setColor(Color.ORANGE);
                        break;
                    case 6:
                        brick.setColor(Color.YELLOW);
                        break;
                    case 10:
                        brick.setColor(Color.CYAN);
                        break;
                }
                bricks.add(brick);
            }
        }
        return bricks;
    }
    public void checkHit(Ball ball, Game game){
            if(this.isAlive()&&ball.collide(this.getX(),this.getY(),BRICK_WIDTH,BRICK_HEIGHT)){
                ball.BounceY();
                switch (this.getColor_name()){
                    case "java.awt.Color[r=0,g=255,b=255]":
                    case "java.awt.Color[r=0,g=0,b=255]":
                    case "java.awt.Color[r=255,g=0,b=0]": {
                        this.setAlive(false);
                        game.score+=1;
                        break;
                    }
                    case "java.awt.Color[r=0,g=255,b=0]":{
                        this.setColor(Color.RED);
                        game.score+=1;
                        break;
                    }
                    case "java.awt.Color[r=255,g=200,b=0]":{
                        this.setColor(Color.GREEN);
                        game.score+=1;
                        break;
                    }
                    case "java.awt.Color[r=255,g=255,b=0]":{
                        this.setColor(Color.CYAN);
                        game.score+=1;
                        break;
                    }
                }
            }

    }
    public boolean All_die(ArrayList<Brick> bricks){

//        循环所有砖块
        boolean flag = false;
        int i=0,j=0;

//        如果有砖块存活flag1=1,当flag1=0时砖块没有，跳出胜利
        for(Brick brick: bricks){
            if(brick.isAlive()==true){
                flag = true;
                break;
            }
        }
        if (flag == true){
            return false;
        }
        else return true;
    }
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        if(Alive){
            g2.setColor(color);
            g2.fillRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
        }
    }

}