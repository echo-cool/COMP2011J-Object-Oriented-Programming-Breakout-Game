package com.echo.model;

import com.echo.presentation.GameScreen;
import com.echo.presentation.PlayerListener;

import java.awt.*;
import java.util.ArrayList;


public class Game implements Runnable{

    //private final boolean debug = false;
    //Game Variables
    private final PlayerListener playerListener;

    public int score;
    public boolean paused = true;
    public boolean gameOver = false;
    public boolean win = false;

    // Game Area
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    public static final int game_box_w=512;
    public static final int game_box_h=412;
    private double GAME_HERTZ = 30.0;
    private int TARGET_FPS = 30;
    private final Rectangle SCREEN_BOUNDS = new Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    private final GameScreen gameScreen;
    private boolean start_again;
    private int level = 1;

    // Bricks
    private static final int brick_per_row=10;
    private static final int brick_row=10;
    private static final int brick_sep=4;
    private ArrayList<Brick> bricks;

    // Ball
    private ArrayList<Ball> playerBalls = new ArrayList<>();
    //private Ball ball;

    // Paddle
    Paddle paddle;
    private Laser laser;
    //public static Laser laser;

    // Player
    private Player player;
    private int lives = 3;


    public Game(PlayerListener playerListener, GameScreen gameScreen) {
        this.paddle = new Paddle(this);
        this.playerBalls.add(new Ball(this));
        //this.playerBalls.add(new Ball());
        this.bricks = Brick.initBricks(brick_row, brick_per_row, level);
        this.playerListener = playerListener;
        this.gameScreen = gameScreen;
        this.player = new Player(playerListener, paddle);
        this.laser = new Laser(this);
        gameScreen.addGame(this);

    }

    public void run(){
        double now = System.nanoTime();
        double lastUpdateTime = System.nanoTime();
        System.nanoTime();
        double lastRenderTime;
        int allUpdateCount = 0;
        while (lives > 0 && !win) {
            if (!paused) {
                int updateCount = 0;
                double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
                double TARGET_TIME_BETWEEN_RENDERS = 1000000000.0 / TARGET_FPS;
                int MAX_UPDATES_BEFORE_RENDER = 1;
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                    allUpdateCount++;
                    if(allUpdateCount % 30 == 0){
                        GAME_HERTZ += 2;
                        TARGET_FPS += 2;
                    }
                }
                gameScreen.paintImmediately(SCREEN_BOUNDS);
                lastRenderTime = now;
                //private final int playerLives = 1;
                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception ignored) {
                    }

                    now = System.nanoTime();
                }
                if (playerListener.isPlayPause()) {
                    paused = true;
                }
            } else {
                lastUpdateTime = System.nanoTime();
                gameScreen.paintImmediately(SCREEN_BOUNDS);
                if (playerListener.isPlayPause()) {
                    paused = false;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (playerListener.isPlayPause()) {
            paused = !paused;
        }
    }


    public void updateGame() {
        player.move();
        ArrayList<Ball> tmp = new ArrayList<>(getPlayerBalls());
        if(playerListener.isStart_again()) {
            paddle.setStickyPaddle(false);
        }
        if(!paddle.isStickyPaddle()) {
            this.start_again = false;
            for (Ball ball : playerBalls)
                ball.setStop(false);
        }
        for(Ball ball: playerBalls)
            if(ball.moveAndBounce(paddle, this) && paddle.isStickyPaddle()){
                //this.paused = true;
                ball.setStop(true);
                ball.setStickied(true);
                this.start_again = true;
                //paddle.setStickyPaddle(false);
            }
        for(Ball ball: tmp)
            for(Brick brick:bricks){
                brick.checkHit(ball, this);
                brick.checkHit(laser, this);
            }
        for(Brick brick: bricks)
            if(brick.getBonuses() != null){
                brick.getBonuses().setGame(this);
                if(brick.getBonuses().isShow()) {
                    brick.getBonuses().move();
                    brick.getBonuses().checkHit(paddle.getX(),paddle.getY(),Paddle.paddle_width,Paddle.paddle_height);
                }
            }
        playerBalls.removeIf(ball -> !ball.isAlive());
        if(playerBalls.size() <= 0){
            if(--this.lives > 0){
                this.playerBalls.add(new Ball(this));
                this.GAME_HERTZ = 30.0;
                this.TARGET_FPS = 30;
            }
            else{
                gameOver = true;
            }

            //paused = true;
        }
        if(Brick.All_die(bricks)){
            if(this.level < 3){
                this.bricks = Brick.initBricks(brick_row, brick_per_row, ++level);
            }
            else{
                win = true;
            }

        }
    }

    public int getScore() {
        return score;
    }


    //public Ball getBalls() {
    //    return ball;
    //}

    public ArrayList<Ball> getPlayerBalls() {
        return playerBalls;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public boolean isPaused() {
        return paused;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Laser getLaser() {
        return laser;
    }

    public static int getBrick_sep() {
        return brick_sep;
    }

    public boolean isStart_again() {
        return start_again;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
