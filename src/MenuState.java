import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class MenuState extends GameState{

	private String[] options = {"Start","Help","Credits","Quit"};
	private int currentSelection = 0;
//	private ImageIcon picture = new ImageIcon(MenuState.class.getResource("dotpict_20170218_124011.png"));
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}


	public void init() {
	
	
	}


	public void tick() {
	
	
	}
	
	public BufferedImage set(Graphics g){
		BufferedImage[] background ;
		background = new BufferedImage[1];
		try {
			background[0] = ImageIO.read(this.getClass().getResourceAsStream("/Alt Startup.png"));
		} catch (IOException e) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		return background[0];
	}
	public void draw2(Graphics g){
		g.drawImage(set(g),0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);		
	}
	public void draw(Graphics g) {		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		draw2(g);
		for(int i = 0; i < options.length; i++){
			if(i == currentSelection){
				g.setColor(Color.MAGENTA);
			}else{
				g.setColor(Color.WHITE);
			}

			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 60));
			g.drawString(options[i], GamePanel.WIDTH / 2 - 70, 300 + i * 100);
		}
		g.setColor(Color.MAGENTA);
		g.drawString("ARCHETALE", 210, 100);
	}


	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN){
			currentSelection++;
			if(currentSelection >= options.length){
				currentSelection = 0;
			}
		}else if(k == KeyEvent.VK_UP){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = options.length -1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER){
			if(currentSelection == 0){
				gsm.states.push(new Level1State (gsm));
			}else if(currentSelection == 1){
//				JOptionPane.showMessageDialog(null, null, "", 1);
				JOptionPane.showMessageDialog(null, "Welcome to ARCHETALE an 8-Bit 2d Side Scroller\n" +
						"The controls are\n" +
						"Use the WAD keys for movement\n" +
						"A for Moving Left\n" +
						"D for Moving Right\n" +
						"W for Jumping\n" +
						"L for Dashing\n\n" +
						"I in case you want to restart the level\n" +
						"J in case you are using a lower end computer");
				JOptionPane.showMessageDialog(null,"Your goal is to reach the end of the stage\n" +
						"Using your mad skills to avoid obstacles\n" +
						"There is a checkpoint to help you out,\n" +
						"Good Luck!");
			}else if(currentSelection == 2){
				JOptionPane.showMessageDialog(null,"Credits\n" +
						"Programmer: Jose Rivero\n" +
						"Background Images: Jose Rivero" +
						"Image Creation Program used: DotPict\n" +
						"Sprite Sheet Images: The Mighty Gunvolt\n" +
						"Disclamer: I do not own the Mighty Gunvolt, all rights resevered for them." +
						"Music: Jose Rivero\n" +
						"Music Creation Program used: Medly\n" +
						"Front cover and Ending pictures from: DotPict" +
						"Special thanks to Youtubers and Guides for help\n" +
						"This 2D Side Scroller was made for Rico's Ulimate Challenge");
			}else if(currentSelection == 3){
				System.exit(0);
			}
		}
	}


	public void keyReleased(int k) {


	}

}
