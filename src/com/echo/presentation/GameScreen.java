package com.echo.presentation;

import com.echo.model.Ball;
import com.echo.model.Brick;
import com.echo.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {
    private static final long serialVersionUID = -8282302849760730222L;
    private Game game;
    private final Drawer drawer = new Drawer();

    public GameScreen() {
    }

    protected void paintComponent(Graphics g) {
        //System.out.println(game);
        if (game != null) {
            g.setColor(Color.black);
            g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
            g.setColor(Color.green);
            g.drawString("Balls: " + game.getPlayerBalls().size(), 0, 20);
            g.drawString("Score: " + game.getScore(), Game.SCREEN_WIDTH / 4, 20);
            g.drawString("Lives: " + game.getLives(), Game.SCREEN_WIDTH * 2/4, 20);
            g.drawString("Level: " + game.getLevel(), Game.SCREEN_WIDTH * 3/4, 20);
            game.getPaddle().draw(g);
            for(Ball ball : game.getPlayerBalls())
                ball.draw(g);
            for(Brick brick: game.getBricks()) {
                brick.draw(g);
                if(brick.getBonuses() != null){
                    if(brick.getBonuses().isShow())
                        brick.getBonuses().draw(g);
                }
            }
            game.getLaser().draw(g);
            if (game.isPaused() && !game.gameOver) {
                g.drawString("Press 'p' to continue ", 256, 256);
            }
            if(game.isStart_again()){
                g.drawString("Press SPACE to continue ", 256, 256);
                g.drawString("And now you can move your paddle ", 256, 256+30);
            }
            if (game.gameOver){
                drawer.drawString(g, "Game Over !", new Rectangle(0, 256, Game.SCREEN_WIDTH, 96), 54);
                drawer.drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 256+96, Game.SCREEN_WIDTH, 96), 24);
            }
            if(game.win){
                drawer.drawString(g, "You Win !", new Rectangle(0, 256, Game.SCREEN_WIDTH, 96), 54);
                drawer.drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 256+96, Game.SCREEN_WIDTH, 96), 24);

            }
        }
    }

    public void addGame(Game g) {
        this.game = g;
    }
}
