package smith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	public static Clip clip;
	
	public static Question selectedQuestion;
	
	public static int counter = 0;
	
	public static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			// TODO: implement this as a method that accepts a player score object
			if (e.getKeyText(e.getKeyCode()).equals("A")) {
				//Main.clip.stop();
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
				//JOptionPane.showMessageDialog(null, score.scores[0]);
				pane.setLayer(display, 1);
				pane.setLayer(s, 2);
				frame.removeKeyListener(k);
				counter++;
			} else if (e.getKeyText(e.getKeyCode()).equals("L")) {
				// TODO: implement more methods and additional player scoring
				JOptionPane.showMessageDialog(null, "PLAYER 2");
				if (JOptionPane.showInputDialog(selectedQuestion.getString()).toUpperCase().equals(selectedQuestion.getAnswer())) {
					score.scores[1] += selectedQuestion.getMoney();
					score.labelScores[1].setText("Player 2: " + Integer.toString(score.scores[1]));
					JOptionPane.showMessageDialog(null, "CORRECT!");
				} else {
					score.scores[1] -= selectedQuestion.getMoney();
					if (score.scores[1] < 0) {
						score.scores[1] = 0;
					}
					JOptionPane.showMessageDialog(null, "INCORRECT...");
				}
				//JOptionPane.showMessageDialog(null, score.scores[1]);
				pane.setLayer(display, 1);
				pane.setLayer(s, 2);
				frame.removeKeyListener(k);
				counter++;
			}
			if (counter == 30) {
				JOptionPane.showMessageDialog(null, "GAME OVER");
				// TODO: add winner'c circle feature
				System.exit(0);
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
	
	public static void playAudio() { //this method plays a sound
		try {
			AudioInputStream audioin = AudioSystem.getAudioInputStream(new File("./src/files/audio/Jeopardy-theme-song.wav")); //the audio file must be in .wav format
			Clip clip = AudioSystem.getClip();
			clip.open(audioin);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
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
		display.setFont(new Font("Sanserif Bold", Font.BOLD, 35));
		
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
