package com.echo.presentation;

import com.echo.model.Bonuses;
import com.echo.model.Game;

import java.awt.*;

public class Drawer {
    public void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();
        Font font = new Font("Arial", Font.PLAIN, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setColor(Color.white);
        g2d.drawString(text, x, y);
    }
    public void drawString(Graphics g, String text, Rectangle rect, int size, boolean with_border) {
        Graphics2D g2d = (Graphics2D) g.create();
        Font font = new Font("Arial", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setColor(Color.yellow);
        g2d.drawString(text, x, y);
        g2d.drawRect(Game.SCREEN_WIDTH / 8, y - metrics.getHeight() * 2, (int) (Game.SCREEN_WIDTH * 0.75), metrics.getHeight() * 3);
    }

    public void drawString(Graphics g, String text, Rectangle rect, int size, boolean with_border, int font_type) {
        Graphics2D g2d = (Graphics2D) g.create();
        Font font = new Font("Arial", font_type, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.setColor(Color.cyan);
        g2d.drawString(text, x, y);
        if(with_border) {
            g2d.drawRect(Game.SCREEN_WIDTH / 8, y - metrics.getHeight() * 2, (int) (Game.SCREEN_WIDTH * 0.75), metrics.getHeight() * 3);
        }
    }
}
