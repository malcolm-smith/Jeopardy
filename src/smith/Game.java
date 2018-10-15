package smith;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

public class Game {
	private final int catNumber = 6;
	
	private JPanel panel = new JPanel();
	private Category categories[] = new Category[catNumber];
	
	public Game() {
		initGUI();
	}
	
	private void initGUI() {
		panel.setLayout(new GridLayout(1, catNumber));
		
		for (int i = 0; i < categories.length; i++) {
			categories[i] = new Category(new File("./src/files/questions/" + Integer.toString(i + 1)));
			panel.add(categories[i].getPanel());
		}
		
		panel.setVisible(true);
	}
	
	//setters and getters
	public JPanel getPanel() {
		return panel;
	}
}
