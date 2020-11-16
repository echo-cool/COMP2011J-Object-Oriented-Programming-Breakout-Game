package com.echo.presentation;

import com.echo.model.Game;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final Rectangle Welcome_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 64, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);
    private final Rectangle Author_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);
    private final Rectangle startGame_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, 2 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);
    private final Rectangle highScores_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, 3 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);
    private final Rectangle help_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, 4 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);
    private final Rectangle exit_Rectangle = new Rectangle(Game.SCREEN_WIDTH / 3, 5 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3);

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        Drawer drawer = new Drawer();

        drawer.drawString(g, "Welcome to Breakout !", Welcome_Rectangle, 24);
        drawer.drawString(g, "Author: WangYuyang", Author_Rectangle, 18, false, Font.ITALIC);
        drawer.drawString(g, "To play a game press N", startGame_Rectangle, 18, true);
        drawer.drawString(g, "To see the High scores press H", highScores_Rectangle, 18,true);
        drawer.drawString(g, "For help press A", help_Rectangle, 18,true);
        drawer.drawString(g, "To exit press X", exit_Rectangle, 18,true);

    }
}
