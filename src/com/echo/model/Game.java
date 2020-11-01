package com.echo.model;

import com.echo.presentation.GameScreen;
import com.echo.presentation.PlayerListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Game implements Runnable{

    private boolean debug = false;

    //Game Variables
    private PlayerListener playerListener;
    private int playerLives = 1;
    private final int TARGET_FPS = 30;
    private final double GAME_HERTZ = 30.0;
    private final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
    private final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    private final int MAX_UPDATES_BEFORE_RENDER = 5;
    public int score;
    public boolean paused = true;
    public boolean gameOver = false;



    // Game Area
    private GameScreen gameScreen;
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    public static final int game_box_w=512;
    public static final int game_box_h=412;
    private static final Rectangle SCREEN_BOUNDS = new Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    // Bricks
    public static int brick_per_row=10;
    public static int brick_row=10;
    public static final int brick_sep=4;
    private static ArrayList<Brick> bricks;

    // Ball
    private static ArrayList<Ball> playerBalls = new ArrayList<>();
    //private Ball ball;

    // Paddle
    Paddle paddle;

    // Player
    Player player;



    public Game(PlayerListener playerListener, GameScreen gameScreen) {
        this.paddle = new Paddle();
        this.playerBalls.add(new Ball());
        this.playerBalls.add(new Ball());
        this.bricks = Brick.initBricks(brick_row,brick_per_row);
        this.playerListener = playerListener;
        this.gameScreen = gameScreen;
        this.player = new Player(playerListener, paddle);
        gameScreen.addGame(this);

    }

    public void run(){
        double now = System.nanoTime();
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();
        while (playerBalls.size() > 0) {
            if (!paused) {
                int updateCount = 0;
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    updateGame();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                gameScreen.paintImmediately(SCREEN_BOUNDS);
                lastRenderTime = now;

                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS) {

                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
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
                } catch (InterruptedException e) {
                }
            }
        }

        if (playerListener.isPlayPause()) {
            paused = !paused;
        }
    }


    public void updateGame() {
        player.move();
        for(Ball ball: playerBalls)
            ball.moveAndBounce(paddle, this);
        if(!debug)
            for(Ball ball: playerBalls)
                for(Brick brick:bricks){
                    brick.checkHit(ball, this);
                }
        Iterator<Ball> iter = playerBalls.iterator();
        while(iter.hasNext()){
            Ball ball = iter.next();
            if(!ball.alive)
                iter.remove();
        }
        if(playerBalls.size() <= 0){
            gameOver = true;
            //paused = true;
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
}
