package com.echo.presentation;

import com.echo.model.Game;
import com.echo.model.ScoreKeeper;
import com.echo.model.ScoreKeeper.Score;

import javax.swing.*;
import java.awt.*;

public class ScoreScreen extends JPanel {
	private static final long serialVersionUID = 1616386874546775416L;
	private final ScoreKeeper scoreKeeper;
	private boolean menu = false;
	private final PlayerListener listener;

	public ScoreScreen(ScoreKeeper sc, PlayerListener l) {
		this.scoreKeeper = sc;
		this.listener = l;
	}


	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		Drawer drawer = new Drawer();
		drawer.drawString(g, "Breakout Hall of Fame", new Rectangle(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT / 8),
				36);
		g.setColor(Color.GREEN);
		Score[] scores = scoreKeeper.getScores();
		g.setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = 0; i < scores.length; i++) {
			Score score = scores[i];
			g.drawString(score.getName(), 2 * Game.SCREEN_WIDTH / 6, 96 + i * 32);
			g.drawString("" + score.getScore(), 4 * Game.SCREEN_WIDTH / 6, 96 + i * 32);
		}
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
