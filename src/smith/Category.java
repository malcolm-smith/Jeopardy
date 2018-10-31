package smith;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class Category {
	// adds a black border to each category title
	public static Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

	private String name = "DEFAULT";

	// number of questions per category
	private final int questionNumber = 5;

	private Question arr[] = new Question[questionNumber];

	private JPanel panel = new JPanel();
	private JLabel catName = new JLabel(name, SwingConstants.CENTER);

	public Category(File f) { // passes a file where questions are loaded from
		createCategory(f);
	}

	private void createCategory(File f) { // creates a category; vertical column
		panel.setLayout(new GridLayout(questionNumber + 1, 1));
		panel.add(catName);
		catName.setBackground(Color.BLUE); // styling
		catName.setForeground(Color.LIGHT_GRAY);
		catName.setFont(new Font("SansSerif Bold", Font.PLAIN, 25));
		catName.setOpaque(true);
		catName.setBorder(border);

		try {
			Scanner in = new Scanner(f);
			name = in.nextLine().toUpperCase(); // gets the name of the category from the first line of the file
//			System.out.println("CATEGORY : " + name);
			catName.setText(name);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// creates a question object, which is created using each two lines of the file
		for (int i = 0; i < questionNumber; i++) {
			arr[i] = new Question(f, i);
			panel.add(arr[i].getButton());
		}
	}

	public JPanel getPanel() {
		return panel;
	}
}
