package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Question {
	private File file;
	private int questionNumber;
	private JButton button = new JButton();
	private JLabel display = new JLabel();

	private String question;
	private String answer;
	private int money;

	private Font font = new Font("SansSerif Bold", Font.PLAIN, 50);

	private ActionListener a = (new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton) (e.getSource())).setText(null);
			((JButton) (e.getSource())).setEnabled(false);
			System.out.println("button pressed");
			displayQuestion();
//			String response = JOptionPane.showInputDialog(question);
//			if (response.toUpperCase().equals(answer)) {
//				JOptionPane.showMessageDialog(null, "CORRECT", "CORRECT", JOptionPane.PLAIN_MESSAGE);
//				Game.score += money;
//				JOptionPane.showMessageDialog(null, "SCORE: " + Game.score, "SCORE", JOptionPane.PLAIN_MESSAGE);
//			} else {
//				JOptionPane.showMessageDialog(null, "INCORRECT", "INCORRECT", JOptionPane.ERROR_MESSAGE);
//			}
		}
	});

	public Question(File f, int i) {
		file = f;
		questionNumber = i;
		loadQuestion(file);
		button.setText("$" + Integer.toString(money));
		button.setBackground(Color.BLUE);
		button.setForeground(Color.YELLOW);
		button.setFont(font);
		button.setBorder(Category.border);
		button.addActionListener(a);
	}

	private void loadQuestion(File f) {
		try {
			Scanner in = new Scanner(f);
			in.nextLine();
			for (int i = 0; i < questionNumber * 2; i++) {
				in.nextLine();
			}
			String line = in.nextLine();
			answer = in.nextLine();
			in = new Scanner(line);
			money = in.nextInt() * 100;
			question = line.substring(2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("\n\nERROR: PROGRAM TERMINATED");
			System.exit(0);
		}
	}

	private void displayQuestion() {
		Main.frame.removeAll();
		display = new JLabel(question);
		display.setOpaque(true);
		display.setBackground(Color.BLUE);
		display.setForeground(Color.WHITE);
		display.setFont(new Font("SansSerif Bold", Font.PLAIN, 100));
		
		Game.getPanel().add(display);
		Main.refresh();
	}
	
	public void getAnswer(int playerNumber) {
		String response = JOptionPane.showInputDialog(question);
		if (response.toUpperCase().equals(answer)) {
			JOptionPane.showMessageDialog(null, "CORRECT", "CORRECT", JOptionPane.PLAIN_MESSAGE);
			ScoreBoard.setScore(playerNumber, money);
		} else {
			JOptionPane.showMessageDialog(null, "INCORRECT", "INCORRECT", JOptionPane.ERROR_MESSAGE);
			ScoreBoard.setScore(playerNumber, (-1 * money));
		}
		JOptionPane.showMessageDialog(null, "SCORE: " + Game.score, "SCORE", JOptionPane.PLAIN_MESSAGE);
	}
	
	// setters and getters
	public JButton getButton() {
		return button;
	}
}
