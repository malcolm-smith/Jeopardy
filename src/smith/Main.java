package smith;

import java.awt.Color;
import java.awt.Font;
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
	public static AudioInputStream themeSong;
	public static AudioInputStream buzzer;
	public static AudioInputStream correct;
	public static AudioInputStream incorrect;
	
	public static Question selectedQuestion;
	
	public static int counter = 0;
	
	public static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			try {
				int playerNum = Integer.parseInt(e.getKeyText(e.getKeyCode()));
				score.labelScores[playerNum].setText("Player " + (playerNum + 1) + ": " + Integer.toString(selectedQuestion.checkResponse(score.scores[playerNum])));
				pane.setLayer(display, 1);
				pane.setLayer(s, 2);
				frame.removeKeyListener(k);
				counter++;
				if (counter == 30) {
					JOptionPane.showMessageDialog(null, "GAME OVER");
					// TODO: add winner'c circle feature
					System.exit(0);
				}
			} catch (NumberFormatException ex) {}
		}
	});
	
	public static void main(String args[]) {
		// TODO: add game setup feature, with player number selection, player names, etc.
		game = new Game();
		score = new ScoreBoard(2);
		Main main = new Main();
		main.initGUI();
		main.initAudio();
		s.add(game.getPanel());
		s.add(score.getPanel());
		main.refresh();
	}
	
	public void initAudio() { //this method plays a sound
		try {
			themeSong = AudioSystem.getAudioInputStream(new File("./src/files/audio/Jeopardy-theme-song.wav")); //the audio file must be in .wav format
			
			clip = AudioSystem.getClip();
			//clip.open(audioin);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playAudio(AudioInputStream audioin) {
		try {
			clip.open(audioin);
			clip.start();
			Thread.sleep(5000);
			clip.stop();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
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
