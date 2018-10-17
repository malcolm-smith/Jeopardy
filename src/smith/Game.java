package smith;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class Game {
	public static int score = 0;

	private final int catNumber = 6;

	private static JPanel panel = new JPanel();
	private Category categories[] = new Category[catNumber];

	public Game() {
		initGUI();
	}

	private void initGUI() {
		panel.setLayout(new GridLayout(1, catNumber));

		// randomize order of categories
		ArrayList<Integer> list = new ArrayList<Integer>(); // create list of numbers 1 to 6
		for (int i = 1; i <= 6; i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list); // shuffle the list into a random order

		for (int i = 0; i < categories.length; i++) {
			categories[i] = new Category(new File("./src/files/questions/" + list.get(i)), list.get(i));
			panel.add(categories[i].getPanel());
		}

		panel.setVisible(true);
	}

	// setters and getters
	public JPanel getPanel() {
		return panel;
	}

	public Category getCategories(int n) {
		return categories[n];
	}

	public void setCategories(Category categories[]) {
		this.categories = categories;
	}
}
