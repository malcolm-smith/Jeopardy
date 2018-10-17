package smith;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Main {
	private Game game = new Game();

	private static ScoreBoard score = new ScoreBoard(3);

	private static JFrame frame = new JFrame();

	public static JSplitPane splitPane = new JSplitPane();

	public static Main main = new Main();
	
	public Question question;
	
	private int n1;
	private int n2;

	private static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed");
			// frame.removeAll();
			main.addGame();
		}
	});

	public static void main(String args[]) {
		main.initGUI();
		main.addGame();
	}

	public void initGUI() {
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOpaque(true);
		splitPane.setOneTouchExpandable(true);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(1000, 1000);
		getFrame().setLocationRelativeTo(null);
		getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		getFrame().isFocusable();
		getFrame().setFocusable(true);
		getFrame().setVisible(true);
	}

	public void addGame() {
		getFrame().add(splitPane);
		
		splitPane.add(game.getPanel());
		splitPane.add(score.getPanel());
		// Main.refresh();
	}

	public static void refresh() {
		main.getFrame().revalidate();
		main.getFrame().repaint();
	}

	public void r() {
		splitPane.setVisible(false);
		game.getPanel().setVisible(false);
		score.getPanel().setVisible(false);
	}

	public void r2() {
		splitPane.setVisible(true);
		game.getPanel().setVisible(true);
		score.getPanel().setVisible(true);
	}

	// setters and getters
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		Main.frame = frame;
	}

	public KeyListener getK() {
		return k;
	}

	public void setK(KeyListener k) {
		Main.k = k;
	}

	public int getN1() {
		return n1;
	}
	
	public int getN2() {
		return n2;
	}

	public void setN1(int n1) {
		Main.main.n1 = n1;
	}

	public void setN2(int questionNumber) {
		Main.main.n2 = questionNumber;
	}
}
