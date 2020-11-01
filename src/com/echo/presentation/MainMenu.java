package com.echo.presentation;


import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.echo.db.PersistentScoreKeeper;
import com.echo.model.Game;
import com.echo.model.ScoreKeeper;

public class MainMenu {
    private final PlayerListener playerListener;
    private boolean exit;
    private final GameScreen gameScreen;
    private final JPanel main;
    private final ScoreScreen scoreScreen;
    private final CardLayout cardLayout;
    private final ScoreKeeper scoreKeeper;
    private final AboutScreen aboutScreen;

    public MainMenu(JFrame win, PlayerListener lis) {
        this.playerListener = lis;
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        Menu menu = new Menu();
        aboutScreen = new AboutScreen(playerListener);
        scoreKeeper = new PersistentScoreKeeper();
        scoreScreen = new ScoreScreen(scoreKeeper, playerListener);
        gameScreen = new GameScreen();
        main.add(menu, "Main Menu");
        main.add(gameScreen, "Game Screen");
        main.add(scoreScreen, "High Scores");
        main.add(aboutScreen, "About");
        win.getContentPane().add(main);
    }

    public void run() {
        new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (!exit) {
            if (playerListener.isNew()) {
                Game game = new Game(playerListener, gameScreen);
                cardLayout.show(main, "Game Screen");
                game.run();
                int s = game.getScore();
                if (s > scoreKeeper.getLowestScore()) {
                    String name = JOptionPane.showInputDialog("New High Score, Please enter your name:");
                    scoreKeeper.addScore(name, s);
                    cardLayout.show(main, "High Scores");
                    scoreScreen.run();
                    cardLayout.show(main, "Main Menu");
                    playerListener.reset();
                }
                // start the game engine
            } else if (playerListener.isHigh()) {
                System.out.println("is high");
                cardLayout.show(main, "High Scores");
                scoreScreen.run();
                cardLayout.show(main, "Main Menu");
            } else if (playerListener.isExit()) {
                exit = true;
                scoreKeeper.saveScores();
                System.exit(0);
            } else if (playerListener.isAbout()) {
                cardLayout.show(main, "About");
                aboutScreen.run();
                cardLayout.show(main, "Main Menu");
            }
            else if (playerListener.isMenu()) {
                cardLayout.show(main, "Main Menu");
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
