package com.echo.presentation;


import com.echo.model.Game;
import javax.swing.*;

public class Window {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Breakout !");
		jFrame.setLocationRelativeTo(null);
		PlayerListener listener = new PlayerListener();
		jFrame.addKeyListener(listener);
		MainMenu menu = new MainMenu(jFrame, listener);
		jFrame.setVisible(true);
		jFrame.requestFocusInWindow();
		menu.run();
	}

}
