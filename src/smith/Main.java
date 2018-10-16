package smith;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Main {
	private static Game game = new Game();
	
	private static ScoreBoard score = new ScoreBoard(3);

	public static JFrame frame = new JFrame();

	private static JSplitPane s = new JSplitPane();
	
	public static Main main = new Main();
	
	private KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			int answerer = 0;
			String f = KeyEvent.getKeyText(e.getKeyCode()).toUpperCase();
			System.out.println(f);
			if (f.equals("E")) {
				answerer = 1;
			} else if (f.equals("P")) {
				answerer = 2;
			}
			//Question.getAnswer(answerer);0
			frame.removeAll();
			main.addGame();
		}
	});
	
	public static void main(String args[]) {
		main.initGUI();
		main.addGame();
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
	
	public void addGame() {
		s.add(game.getPanel());
		s.add(score.getPanel());
		main.refresh();
	}
	
	public static void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
