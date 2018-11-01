package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard { // this class keeps track of the player's scores
	private JPanel panel = new JPanel();

	private int playerNumber = 0;

	public int scores[]; // array of Integers that holds the amount of money each player has
	public JLabel labelScores[]; // JLabels containing the values of each player's score

	private Font font = new Font("SansSerif Bold", Font.PLAIN, 20);

	// contructor; is called when a ScoreBoard instance is created
	public ScoreBoard(int playerNumber) {
		this.playerNumber = playerNumber; // gets number of players in the game
		createScoreBoard();
	}

	private void createScoreBoard() {
		scores = new int[playerNumber];
		labelScores = new JLabel[playerNumber];
		panel.setLayout(new GridLayout(playerNumber, 1));
		for (int i = 0; i < playerNumber; i++) {
			labelScores[i] = new JLabel("Player " + (i + 1) + ": " + Integer.toString(scores[i]));
			labelScores[i].setOpaque(true);
			labelScores[i].setBackground(Color.BLUE);
			labelScores[i].setForeground(Color.YELLOW);
			labelScores[i].setFont(font);
			panel.add(labelScores[i]);
		}
	}

	public int getHighestScore() { // used in the gameOver() method in the superclass
		int temp = 0;
		int p = 0;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > temp) {
				temp = scores[i];
				p = i;
			}
		}
		return p;
	}

	// setters and getters

	public JPanel getPanel() {
		return panel;
	}
}
