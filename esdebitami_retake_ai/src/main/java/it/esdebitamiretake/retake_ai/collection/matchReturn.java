package it.esdebitamiretake.retake_ai.collection;

import java.util.List;

public class matchReturn {

	private int score;
	private List<String> matchSentence;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<String> getMatchSentence() {
		return matchSentence;
	}
	public void setMatchSentence(List<String> matchSentence) {
		this.matchSentence = matchSentence;
	}
	public matchReturn(int score, List<String> matchSentence) {
		super();
		this.score = score;
		this.matchSentence = matchSentence;
	}
	public matchReturn() {
		// TODO Auto-generated constructor stub
	}
	
	
}