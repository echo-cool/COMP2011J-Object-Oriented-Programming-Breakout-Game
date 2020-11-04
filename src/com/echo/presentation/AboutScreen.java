package com.echo.presentation;


import com.echo.model.Game;

import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JPanel {
    private static final long serialVersionUID = -1264717778772722118L;
    private boolean menu = false;
    private final PlayerListener listener;

    public AboutScreen(PlayerListener pl) {
        listener = pl;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        g.setColor(Color.green);
        Drawer drawer = new Drawer();
        drawer.drawString(g, "BreakOut Controls", new Rectangle(0, 0, Game.SCREEN_WIDTH, 64), 36);
        g.drawString("Move Left", 1 * Game.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("left arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("Move Right", 1 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("right arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("Restart(When Stickied)", 1 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("space bar", 4 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("Play/Pause", 1 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("p", 4 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        drawer.drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Game.SCREEN_WIDTH, 96), 24);
    }

    public void run() {
        while (!menu) {
            if (listener.isMenu()) {
                menu = true;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        listener.reset();
        menu = false;
    }


}
