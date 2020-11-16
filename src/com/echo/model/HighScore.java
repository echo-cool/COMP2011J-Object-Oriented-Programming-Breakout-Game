package com.echo.model;

import java.util.Arrays;

public class HighScore {
	protected Score[] playerScore;

	public HighScore() {
		this.playerScore = new Score[10];
		for (int i = 0; i < playerScore.length; i++) {
			this.playerScore[i] = new Score("Player", 0);
		}
	}

	private void sortScores() {
		Arrays.sort(playerScore, (data1, data2) -> Integer.compare(data2.getPlayerScore(), data1.getPlayerScore()));
	}

	public int getLowestScore() {
		sortScores();
		return playerScore[9].getPlayerScore();
	}

	public Score[] getPlayerScore() {
		Score[] score = new Score[10];
		int count = 0;
		for(Score tmp: playerScore){
			score[count++] = tmp;
		}
		return score;
	}

	public void saveScores() {
	}

	public void addScore(String name, int s) {
		playerScore[9] = new Score(name, s);
		sortScores();
		//System.out.println(Arrays.toString(PlayerScore));
	}
}
