import javax.swing.JFrame;


public class Game {
	
	public void start(){
		
		JFrame window = new JFrame("BULLET HELL");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		window.setContentPane(new GamePanelHell());
		
		window.pack();
		window.setVisible(true);
		
	}
	
}