package com.echo.presentation;


import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.echo.db.HighScoreLoaderAndSaver;
import com.echo.model.Game;
import com.echo.model.HighScore;

public class MainMenu {
    private final PlayerListener pListener;
    private boolean exit;
    private final GameScreen gameScreen;
    private final JPanel jPanel;
    private final HighScoreScreen highScoreScreen;
    private final CardLayout layout;
    private final HighScore highScoreKeeper;
    private final HelpScreen helpScreen;

    public MainMenu(JFrame win, PlayerListener lis) {
        this.pListener = lis;
        layout = new CardLayout();
        jPanel = new JPanel(layout);
        Menu menu = new Menu();
        helpScreen = new HelpScreen(pListener);
        highScoreKeeper = new HighScoreLoaderAndSaver();
        highScoreScreen = new HighScoreScreen(highScoreKeeper, pListener);
        gameScreen = new GameScreen();
        jPanel.add(menu, "Main Menu");
        jPanel.add(gameScreen, "Game Screen");
        jPanel.add(highScoreScreen, "High Scores");
        jPanel.add(helpScreen, "About");
        win.getContentPane().add(jPanel);
    }

    public void run() {
        while (!exit) {
            if (pListener.isNew()) {
                Game game = new Game(pListener, gameScreen);
                layout.show(jPanel, "Game Screen");
                game.run();
                int s = game.getScore();
                if (s > highScoreKeeper.getLowestScore()) {
                    String name = JOptionPane.showInputDialog("New High Score, Please enter your name:");
                    highScoreKeeper.addScore(name, s);
                    layout.show(jPanel, "High Scores");
                    highScoreScreen.run();
                    layout.show(jPanel, "Main Menu");
                    pListener.reset();
                }
                // start the game engine
            } else if (pListener.isHigh()) {
                layout.show(jPanel, "High Scores");
                highScoreScreen.run();
                layout.show(jPanel, "Main Menu");
            } else if (pListener.isExit()) {
                exit = true;
                highScoreKeeper.saveScores();
                System.exit(0);
            } else if (pListener.isAbout()) {
                layout.show(jPanel, "About");
                helpScreen.run();
                layout.show(jPanel, "Main Menu");
            }
            else if (pListener.isMenu()) {
                layout.show(jPanel, "Main Menu");
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit = false;
    }

}
