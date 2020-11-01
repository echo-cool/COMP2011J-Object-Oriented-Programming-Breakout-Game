package com.echo.presentation;

import com.echo.model.Ball;
import com.echo.model.Brick;
import com.echo.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {
    private static final long serialVersionUID = -8282302849760730222L;
    private boolean menu = false;
    private PlayerListener listener;
    private Game game;
    private Drawer drawer = new Drawer();

    public GameScreen(PlayerListener listener) {
        this.listener = listener;
    }

    protected void paintComponent(Graphics g) {
        //System.out.println(game);
        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.black);
            g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
            g.setColor(Color.green);
            g.drawString("Balls: " + game.getPlayerBalls().size(), 0, 20);
            g.drawString("Score: " + game.getScore(), Game.SCREEN_WIDTH / 2, 20);
            game.getPaddle().draw(g);
            for(Ball ball : game.getPlayerBalls())
                ball.draw(g);
            for(Brick brick: game.getBricks())
                brick.draw(g);
            if (game.isPaused() && !game.gameOver) {
                g.drawString("Press 'p' to continue ", 256, 256);
            }
            if (game.gameOver){
                drawer.drawString(g, "Game Over !", new Rectangle(0, 256, Game.SCREEN_WIDTH, 96), 54);
                drawer.drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 256+96, Game.SCREEN_WIDTH, 96), 24);
            }
        }
    }

    public void addGame(Game g) {
        this.game = g;
    }
}
