package entities;

import java.io.Serializable;

public class Score implements Serializable{

	private static final long serialVersionUID = -5279795957956310379L;
	
	private int score;
	
	public Score(int score) {
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public String toString(){
		return "Score: " + score;
	}

}
