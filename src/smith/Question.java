package smith;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Question {
	private File file;
	private int questionNumber;
	private int categoryNumber;
	private JButton button = new JButton();
	private static JLabel display = new JLabel();

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

	public Question(File f, int num, int num2) {
		file = f;
		questionNumber = num;
		categoryNumber = num2;
		
//		System.out.println(questionNumber + " " + categoryNumber);
		
		loadQuestion(file);
		button.setText("$" + Integer.toString(money));
		button.setBackground(Color.BLUE);
		button.setForeground(Color.YELLOW);
		button.setFont(font);
		button.setBorder(Category.border);
		button.addActionListener(a);
	}

	@SuppressWarnings("resource")
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
		Main.main.r();
		Question.display = new JLabel(question, SwingConstants.CENTER);
		Question.display.setOpaque(true);
		Question.display.setBackground(Color.BLUE);
		Question.display.setForeground(Color.WHITE);
		Question.display.setFont(new Font("SansSerif Bold", Font.PLAIN, 50));
		Main.main.getFrame().addKeyListener(Main.main.getK());
		Main.main.getFrame().add(Question.display);
		Main.main.getFrame().requestFocus();
		Main.main.getFrame().revalidate();
		Main.main.getFrame().repaint();
		Main.main.setN1(categoryNumber);
		Main.main.setN2(questionNumber);
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

	public Component getDisplay() {
		return display;
	}
}
