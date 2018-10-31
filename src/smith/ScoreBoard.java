package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard {
	private JPanel panel = new JPanel();
	
	private static int playerNumber = 0;
	
	public int scores[];
	public JLabel labelScores[];
	
	private Font font = new Font("SansSerif Bold", Font.PLAIN, 20);
	
	public ScoreBoard(int playerNumber) {
		this.playerNumber = playerNumber;
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
	
	public void getHighestScore() {
		
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
