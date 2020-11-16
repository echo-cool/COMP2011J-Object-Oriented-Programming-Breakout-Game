package com.echo.presentation;

import com.echo.model.Game;
import com.echo.model.HighScore;
import com.echo.model.Score;

import javax.swing.*;
import java.awt.*;

public class HighScoreScreen extends JPanel {
	private final HighScore highScore;
	private boolean menu = false;
	private final PlayerListener playerListener;

	public HighScoreScreen(HighScore highScore, PlayerListener playerListener) {
		this.highScore = highScore;
		this.playerListener = playerListener;
	}


	public void paintComponent(Graphics graphics) {
		graphics.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		Drawer drawer = new Drawer();
		drawer.drawString(graphics, "Breakout Hall of Fame", new Rectangle(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT / 8),
				36);
		graphics.setColor(Color.GREEN);
		Score[] scores = highScore.getPlayerScore();
		graphics.setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = 0; i < scores.length; i++) {
			Score score = scores[i];
			graphics.drawString(score.getPlayerName(), 2 * Game.SCREEN_WIDTH / 6, 96 + i * 32);
			graphics.drawString("" + score.getPlayerScore(), 4 * Game.SCREEN_WIDTH / 6, 96 + i * 32);
		}
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
