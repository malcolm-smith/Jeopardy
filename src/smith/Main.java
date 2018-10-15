package smith;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Main {
	private static Game game;
	
	private static ScoreBoard score;

	private static JFrame frame = new JFrame();

	private static JSplitPane s = new JSplitPane();
	
	public static void main(String args[]) {
		game = new Game();
		score = new ScoreBoard(3);
		Main main = new Main();
		main.initGUI();
		s.add(game.getPanel());
		s.add(score.getPanel());
		main.refresh();
	}

	public void initGUI() {
		s = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		s.setOpaque(true);
		s.setOneTouchExpandable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(s);
		frame.setVisible(true);
	}
	
	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
