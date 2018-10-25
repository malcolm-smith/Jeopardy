package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Question {
	private File file;
	private int questionNumber;
	private JButton button = new JButton();

	private String question;
	private String answer;
	private int money;

	private Font font = new Font("SansSerif Bold", Font.PLAIN, 50);

	private ActionListener a = (new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.selectedQuestion = getQuestion();
			Main.pane.setLayer(Main.display, 2);
			Main.pane.setLayer(Main.s, 1);
			Main.display.setText(question);
			Main.frame.addKeyListener(Main.k);
			Main.frame.requestFocus();
			((JButton) (e.getSource())).setText(null);
			((JButton) (e.getSource())).setEnabled(false);
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
	
	public boolean isCorrect(Question q, String response) {
		if (q.answer.equals(response)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Question getQuestion() {
		return this;
	}
	
	public String getString() {
		return this.question;
	}

	public String getAnswer() {
		return this.answer;
	}
	
	public int getMoney() {
		return this.money;
	}
	// setters and getters
	public JButton getButton() {
		return button;
	}
}
