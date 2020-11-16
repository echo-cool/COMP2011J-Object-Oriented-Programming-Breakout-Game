package com.echo.db;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.echo.model.HighScore;
import com.echo.model.Score;

public class HighScoreLoaderAndSaver extends HighScore {
    private final static String fileName = "Player's scores.txt";

    public HighScoreLoaderAndSaver() {
        super();
        loadScore();
    }

    public void loadScore() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String data = bufferedReader.readLine();
            int tmp = 0;
            while(data != null && tmp < 10) {
                Scanner sc = new Scanner(data);
                int playerScore = sc.nextInt();
                String playerName = sc.nextLine().trim();
                this.playerScore[tmp++] = new Score(playerName, playerScore);
                data = bufferedReader.readLine();
                sc.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading score file");
        }
    }

    public void saveScores() {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            for (Score playerScore : playerScore) {
                //System.out.println(score.getPlayerScore() + " " + score.getPlayerName() + "\n");
                bufferedWriter.write(playerScore.getPlayerScore() + " " + playerScore.getPlayerName() + "\n");
                bufferedWriter.flush();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing score file");
        }
    }
}
