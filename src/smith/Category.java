package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class Category {
	public static Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

	private String name = "DEFAULT";

	private final int questionNumber = 5;

	private Question arr[] = new Question[questionNumber];

	private JPanel panel = new JPanel();
	private JLabel catName = new JLabel(name, SwingConstants.CENTER);
	
	private int catNumber;

	public Category(File f, int i) {
		catNumber = i;
		createCategory(f);
	}

	private void createCategory(File f) { // creates a category; vertical column
		panel.setLayout(new GridLayout(questionNumber + 1, 1));
		panel.add(catName);
		catName.setBackground(Color.BLUE);
		catName.setForeground(Color.LIGHT_GRAY);
		catName.setFont(new Font("SansSerif Bold", Font.PLAIN, 25));
		catName.setOpaque(true);
		catName.setBorder(border);

		try {
			Scanner in = new Scanner(f);
			name = in.nextLine().toUpperCase();
			System.out.println("CATEGORY : " + name);
			catName.setText(name);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < questionNumber; i++) {
			arr[i] = new Question(f, i, catNumber);
			panel.add(arr[i].getButton());
		}
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public Question getArr(int n) {
		return arr[n];
	}
}
