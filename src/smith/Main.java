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
	// used in static context since only one will be used at a time
	private static Game game;
	public static Main main;
	
	// keeps track of the score
	private static ScoreBoard score;

	public static JFrame frame = new JFrame();
	
	// question shown on screen after button is pressed
	public static JLabel display = new JLabel("", SwingConstants.CENTER);

	public static JLayeredPane pane = new JLayeredPane();
	public static JSplitPane s = new JSplitPane();
	
	// audio
	public static AudioInputStream themeSong;
	public static Clip themeSongClip;
	public static AudioInputStream buzzer;
	public static Clip buzzerClip;
	public static AudioInputStream correct; 
	public static Clip correctClip;
	public static AudioInputStream incorrect;
	public static Clip incorrectClip;
	
	// holds the question selected by the user
	public static Question selectedQuestion;
	
	// keeps track of how many questions have been opened; used to determine the end of the game
	public static int counter = 0;
	
	// number of players in the game
	public static int numberOfPlayers = 4;
	
	// called when a player's buzzer is pressed
	public static KeyListener k = (new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			try {
				themeSongClip.stop();
				buzzerClip.setMicrosecondPosition(0);
				buzzerClip.start();
				int playerNum = Integer.parseInt(e.getKeyText(e.getKeyCode()));
				JOptionPane.showMessageDialog(null, "Player " + (playerNum + 1));
				score.scores[playerNum] = selectedQuestion.checkResponse(score.scores[playerNum]);
				if (score.scores[playerNum] < 0) {
					score.scores[playerNum] = 0;
				}
				score.labelScores[playerNum].setText("Player " + (playerNum + 1) + ": $" + Integer.toString(score.scores[playerNum]));
				pane.setLayer(display, 1);
				pane.setLayer(s, 2);
				frame.removeKeyListener(k);
				counter++;
				if (counter == 30) {
					gameOver();
				}
			} catch (NumberFormatException ex) {}
		}
	});
	
	public static void main(String args[]) {
		// TODO: add game setup feature, with player number selection, player names, etc.
		game = new Game();
		score = new ScoreBoard(numberOfPlayers);
		main = new Main();
		main.initGUI();
		main.initAudio();
		main.refresh();
	}
	
	public static void initAudio() { //this method plays a sound
		try {
			themeSong = AudioSystem.getAudioInputStream(new File("./src/files/audio/Jeopardy-theme-song.wav")); //the audio file must be in .wav format
			themeSongClip = AudioSystem.getClip();
			buzzer = AudioSystem.getAudioInputStream(new File("./src/files/audio/ding-sound-effect.wav")); //the audio file must be in .wav format
			buzzerClip = AudioSystem.getClip();
			correct = AudioSystem.getAudioInputStream(new File("./src/files/audio/ding-sound-effect.wav")); //the audio file must be in .wav format
			correctClip = AudioSystem.getClip();
			incorrect = AudioSystem.getAudioInputStream(new File("./src/files/audio/wrong-buzzer.wav")); //the audio file must be in .wav format
			incorrectClip = AudioSystem.getClip();
			
			themeSongClip.open(themeSong);
			buzzerClip.open(buzzer);
			correctClip.open(correct);
			incorrectClip.open(incorrect);
			
			//clip.open(audioin);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void playAudio(Clip clip) {
		clip.start();
	}
	
	public static void stopAudio(Clip clip) {
		clip.stop();
	}
	
	// initialize the graphical user interface
	public void initGUI() {
		// JSplitPane is used to separate the score panel from the game (questions) panel
		s = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		s.setOpaque(true);
		s.setOneTouchExpandable(false);
		s.add(game.getPanel());
		s.add(score.getPanel());
		s.setDividerLocation(1750); // where the divider will be placed on the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		
		// makes the game fullscreen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		// creating the question shown display, since it is not an object, it is done here
		display.setBackground(Color.BLUE);
		display.setOpaque(true);
		display.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		display.setForeground(Color.WHITE);
		display.setFont(new Font("Sanserif Bold", Font.BOLD, 35));
		
		s.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		pane.add(display);
		pane.setLayer(display, new Integer(1)); // determines the order of JComponents on the screen
		pane.add(s);
		pane.setLayer(s, new Integer(2));
		pane.setVisible(true); // bug fixing
		
		frame.add(pane);
		frame.setVisible(true);
	}
	
	// this method is called when the game is over (no questions remaining)
	public static void gameOver() {
		JOptionPane.showMessageDialog(null, "PLAYER " + (score.getHighestScore() + 1) + " WINS!");
		frame.setVisible(false);
		JOptionPane.showMessageDialog(null, "GAME OVER");
		System.exit(0);
	}
	
	// used after a big change is done to the JFrame and its contents
	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
