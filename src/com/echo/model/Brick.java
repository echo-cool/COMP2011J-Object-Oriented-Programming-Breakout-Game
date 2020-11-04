package com.echo.model;

import java.awt.*;
import java.util.ArrayList;

public class Brick {
    private static int BRICK_WIDTH;
    private static int BRICK_HEIGHT;
    private boolean Alive = true;
    private final int x;
    private final int y;
    private Color color;
    private Bonuses bonuses;

    public Brick(int x, int y, int BRICK_HEIGHT,int BRICK_WIDTH) {
        this.x = x;
        this.y = y;
        Brick.BRICK_HEIGHT = BRICK_HEIGHT;
        Brick.BRICK_WIDTH = BRICK_WIDTH;
    }

    public static ArrayList<Brick> initBricks(int brick_row, int brick_per_row, int level){
        ArrayList<Brick> bricks=new ArrayList<>();
        int brick_width = (Game.game_box_w - (brick_per_row - 1) * Game.getBrick_sep()) / brick_per_row;
        int brick_height = 8;
        if(level == 1) {
            for (int i = 0; i < brick_row; i++) {
                for (int j = 0; j < brick_per_row; j++) {
                    int x = j * brick_width + Game.getBrick_sep() * (j + 1);
                    int y = i * brick_height + Game.getBrick_sep() * i;
                    Brick brick = new Brick(x, y + 30, brick_height, brick_width);
                    int a = (int) (Math.random() * 30);
                    switch (a) {
                        case 1:
                            brick.setColor(Color.GREEN);
                            brick.bonuses = new Bonuses(1, x, y);
                            break;
                        case 2:
                            brick.setColor(Color.BLUE);
                            brick.bonuses = new Bonuses(2, x, y);
                            break;
                        case 3:
                            brick.setColor(Color.ORANGE);
                            brick.bonuses = new Bonuses(3, x, y);
                            break;
                        case 4:
                            brick.setColor(Color.CYAN);
                            brick.bonuses = new Bonuses(4, x, y);
                            break;
                        default:
                            brick.setColor(Color.RED);
                            break;

                    }
                    bricks.add(brick);
                }
            }
        }
        else if(level == 2){
            for (int i = 0; i < brick_row; i++) {
                for (int j = 0; j < brick_per_row; j++) {
                    int x = j * brick_width + Game.getBrick_sep() * (j + 1);
                    int y = i * brick_height + Game.getBrick_sep() * i;
                    if(i % 2 == 0 || j % 2 == 0)
                        continue;
                    Brick brick = new Brick(x, y + 30, brick_height, brick_width);
                    int a = (int) (Math.random() * 30);
                    switch (a) {
                        case 1:
                            brick.setColor(Color.GREEN);
                            brick.bonuses = new Bonuses(1, x, y);
                            break;
                        case 2:
                            brick.setColor(Color.BLUE);
                            brick.bonuses = new Bonuses(2, x, y);
                            break;
                        case 3:
                            brick.setColor(Color.ORANGE);
                            brick.bonuses = new Bonuses(3, x, y);
                            break;
                        case 4:
                            brick.setColor(Color.CYAN);
                            brick.bonuses = new Bonuses(4, x, y);
                            break;
                        default:
                            brick.setColor(Color.RED);
                            break;

                    }
                    bricks.add(brick);
                }
            }
        }
        else if(level == 3){
            for (int i = 3; i < brick_row-3; i++) {
                for (int j = 3; j < brick_per_row-3; j++) {
                    int x = j * brick_width + Game.getBrick_sep() * (j + 1);
                    int y = i * brick_height + Game.getBrick_sep() * i;
                    Brick brick = new Brick(x, y + 30, brick_height, brick_width);
                    int a = (int) (Math.random() * 30);
                    switch (a) {
                        case 1:
                            brick.setColor(Color.GREEN);
                            brick.bonuses = new Bonuses(1, x, y);
                            break;
                        case 2:
                            brick.setColor(Color.BLUE);
                            brick.bonuses = new Bonuses(2, x, y);
                            break;
                        case 3:
                            brick.setColor(Color.ORANGE);
                            brick.bonuses = new Bonuses(3, x, y);
                            break;
                        case 4:
                            brick.setColor(Color.CYAN);
                            brick.bonuses = new Bonuses(4, x, y);
                            break;
                        default:
                            brick.setColor(Color.RED);
                            break;

                    }
                    bricks.add(brick);
                }
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
                    case "java.awt.Color[r=255,g=0,b=0]":
                    case "java.awt.Color[r=0,g=255,b=0]":
                    case "java.awt.Color[r=255,g=200,b=0]":
                    case "java.awt.Color[r=255,g=255,b=0]": {
                        this.setAlive(false);
                        game.score+=1;
                        break;
                    }
                }
                if(bonuses != null) {
                    bonuses.setShow(true);
                }
            }

    }
    public void checkHit(Laser laser, Game game){
        if(this.isAlive()&&laser.collide(this.getX(),this.getY(),BRICK_WIDTH,BRICK_HEIGHT)){
            this.setAlive(false);
            game.score+=1;
            if(bonuses != null)
                bonuses.setShow(true);
        }
    }

    public static boolean allClear(ArrayList<Brick> bricks){
        boolean flag = false;
        for(Brick brick: bricks){
            if(brick.isAlive()){
                flag = true;
                break;
            }
        }
        return !flag;
    }
    public void draw(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        if(Alive){
            g2.setColor(color);
            g2.fillRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
            //g2.setColor(Color.CYAN);
            //g2.drawRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
        }
    }
    public void setAlive(boolean alive) { this.Alive = alive; }
    public void setColor(Color color) { this.color = color; }

    public boolean isAlive() { return Alive; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Color getColor() { return color; }
    public String getColor_name(){ return String.valueOf(getColor());}
    public Bonuses getBonuses() { return bonuses; }


}