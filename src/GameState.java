import java.awt.Graphics;


public abstract class GameState {
	
	protected GameStateManager gsm;
	public static double xOffSet;
	public static double yOffSet;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		
		xOffSet = 0;
		yOffSet = 0;
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
