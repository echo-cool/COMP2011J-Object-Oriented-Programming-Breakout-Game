package com.echo.presentation;


import com.echo.model.Game;

import javax.swing.*;
import java.awt.*;

public class HelpScreen extends JPanel {

    private boolean menu = false;
    private final PlayerListener playerListener;

    public HelpScreen(PlayerListener playerListener) {
        this.playerListener = playerListener;
    }

    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        graphics.setColor(Color.green);
        Drawer drawer = new Drawer();
        drawer.drawString(graphics, "BreakOut Controls", new Rectangle(0, 0, Game.SCREEN_WIDTH, 64), 36);
        graphics.drawString("Move Left", 1 * Game.SCREEN_WIDTH / 6, 96 + 0 * 32);
        graphics.drawString("left arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 0 * 32);
        graphics.drawString("Move Right", 1 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        graphics.drawString("right arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        graphics.drawString("Restart(When Stickied)", 1 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        graphics.drawString("space bar", 4 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        graphics.drawString("Play/Pause", 1 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        graphics.drawString("p", 4 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        drawer.drawString(graphics, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Game.SCREEN_WIDTH, 96), 24);
    }

    public void run() {
        while (!menu) {
            if (playerListener.isMenu()) {
                menu = true;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        playerListener.reset();
        menu = false;
    }


}
