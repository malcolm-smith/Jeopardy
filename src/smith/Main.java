package smith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class Main {
	private static Game game;
	
	private static ScoreBoard score;

	public static JFrame frame = new JFrame();
	
	public static JLabel display = new JLabel("", SwingConstants.CENTER);

	public static JLayeredPane pane = new JLayeredPane();
	public static JSplitPane s = new JSplitPane();
	
	public static Question selectedQuestion;
	
	public static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			// TODO: implement this as a method that accepts a player score object
			if (e.getKeyText(e.getKeyCode()).equals("A")) {
				JOptionPane.showMessageDialog(null, "PLAYER 1");
				if (JOptionPane.showInputDialog(selectedQuestion.getString()).toUpperCase().equals(selectedQuestion.getAnswer())) {
					score.scores[0] += selectedQuestion.getMoney();
					score.labelScores[0].setText("Player 1: " + Integer.toString(score.scores[0]));
					JOptionPane.showMessageDialog(null, "CORRECT!");
				} else {
					score.scores[0] -= selectedQuestion.getMoney();
					if (score.scores[0] < 0) {
						score.scores[0] = 0;
					}
					JOptionPane.showMessageDialog(null, "INCORRECT...");
				}
				JOptionPane.showMessageDialog(null, score.scores[0]);
				pane.setLayer(display, 1);
				pane.setLayer(s, 2);
				frame.removeKeyListener(k);
			} else if (e.getKeyText(e.getKeyCode()).equals("B")) {
				// TODO: implement more methods and additional player scoring
			}
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
		s.setDividerLocation(1750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		
		display.setBackground(Color.BLUE);
		display.setOpaque(true);
		display.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		display.setForeground(Color.WHITE);
		display.setFont(new Font("Sanserif Bold", Font.BOLD, 50));
		
		s.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
//		pane.setLayout(new GridLayout(2, 1));
		pane.add(display);
		pane.setLayer(display, new Integer(1));
		pane.add(s);
		pane.setLayer(s, new Integer(2));
		pane.setVisible(true);
		pane.setBackground(Color.GREEN);
		
		frame.add(pane);
		frame.setVisible(true);
	}
	
	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
