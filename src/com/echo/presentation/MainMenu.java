package com.echo.presentation;


import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.echo.db.PersistentScoreKeeper;
import com.echo.model.Game;
import com.echo.model.ScoreKeeper;

public class MainMenu {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -207820671704916628L;
    private JFrame window;
    private PlayerListener playerListener;
    private boolean exit;
    private Game game;
    private GameScreen gameScreen;
    private Menu menu;
    private JPanel main;
    private ScoreScreen scoreScreen;
    private CardLayout cardLayout;
    private ScoreKeeper scoreKeeper;
    private AboutScreen aboutScreen;

    public MainMenu(JFrame win, PlayerListener lis) {
        this.window = win;
        this.playerListener = lis;
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        menu = new Menu();
        aboutScreen = new AboutScreen(playerListener);
        scoreKeeper = new PersistentScoreKeeper();
        scoreScreen = new ScoreScreen(scoreKeeper, playerListener);
        gameScreen = new GameScreen(playerListener);
        main.add(menu, "Main Menu");
        main.add(gameScreen, "Game Screen");
        main.add(scoreScreen, "High Scores");
        main.add(aboutScreen, "About");
        window.getContentPane().add(main);
    }

    public void run() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        while (!exit) {
            if (playerListener.isNew()) {
                game = new Game(playerListener, gameScreen);
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
