package smith;

import javax.swing.JFrame;

public class Main {
	private static Game game;
	
	private static JFrame frame = new JFrame();

	public static void main(String args[]) {
		game = new Game();
		new Main().initGUI();
		frame.add(game.getPanel());
	}
	
	public void initGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
