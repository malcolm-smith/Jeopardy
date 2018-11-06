package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Question {
	private File file;
	private int questionNumber;
	private JButton button = new JButton();

	private String question;
	private String answer;
	private int money;

	private Font font = new Font("SansSerif Bold", Font.PLAIN, 50);

	// each question contains a JButton which calls the methods inside of this ActionListener
	private ActionListener a = (new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) { // called after a question's JButton is pressed
			Main.selectedQuestion = getQuestion(); // tells the game that this question was pressed by the user
			// brings the display to the front of the frame
			Main.pane.setLayer(Main.display, 2);
			Main.pane.setLayer(Main.s, 1);
			// displays the selected question to the audience/players via the display
			Main.display.setText(question);
			// enables the "buzzers" that each player will use
			Main.frame.addKeyListener(Main.k);
			// frame waits for key input
			Main.frame.requestFocus();
			
			// erases the question after it is selected
			((JButton) (e.getSource())).setText(null);
			((JButton) (e.getSource())).setEnabled(false);
		}
	});

	public Question(File f, int i) {
		file = f;
		questionNumber = i;
		loadQuestion(file);
		button.setText("$" + Integer.toString(money));
		// styling
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
			for (int i = 0; i < questionNumber * 2; i++) { // finds the question content by skipping irrelevant lines
				in.nextLine();
			}
			String line = in.nextLine();
			answer = in.nextLine();
			in = new Scanner(line);
			money = in.nextInt() * 100; // gets what the question is worth from the file
			question = line.substring(2); // gets the question itself from the file
		} catch (FileNotFoundException e) {
			// stops the program if no questions are found
			e.printStackTrace();
			System.out.println("\n\nERROR: PROGRAM TERMINATED");
			System.exit(0);
		}
	}
	
	public int checkResponse(int m) { // checks if what the player guessed is correct, and what money they win/lose
		String response = JOptionPane.showInputDialog(question).toUpperCase();
		if (response.equals(answer)) {
			JOptionPane.showMessageDialog(null, "CORRECT!");
			return m + this.money;
		} else {
			JOptionPane.showMessageDialog(null, "INCORRECT.");
			JOptionPane.showMessageDialog(null, "THE CORRECT ANSWER IS: " + answer);
			return m + (this.money * -1);
		}
	}
	
	// setters and getters
	
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
	public JButton getButton() {
		return button;
	}
}
