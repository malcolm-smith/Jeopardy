package smith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;

public class Main {
	private static Game game;
	
	private static ScoreBoard score;

	private static JFrame frame = new JFrame();
	
	public static JLabel display = new JLabel();

	public static JLayeredPane pane = new JLayeredPane();
	public static JSplitPane s = new JSplitPane();
	
	public static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed");
			display.setBackground(Color.RED);
			display.setText("QWBEFUIQWBUB");
			display.setOpaque(true);
			frame.revalidate();
			frame.repaint();
		}
	});
	
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
		display.setBackground(Color.RED);
		display.addKeyListener(k);
		display.isFocusable();
		pane.setLayout(new BorderLayout());
		//pane.add(display);
		pane.add(s);
		pane.setLayer(s, 1);
		frame.add(pane);
		frame.setVisible(true);
	}
	
	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
