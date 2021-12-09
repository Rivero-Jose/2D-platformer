import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Level1State extends GameState {
	
	private Player player;
	private Map map;
	
	public Level1State(GameStateManager gsm){
		super(gsm);
		init();
	}

	public void init() {
		
		map = new Map("/map1.map");
		player = new Player(30,30);
		xOffSet = 80;
		yOffSet = 529;
		
		
	}
	
	public void tick() {
		
		
		player.tick(map.getBlocks());
	}

	public void draw(Graphics g) {
		
		player.draw(g);
		map.draw(g);
		g.setColor(Color.RED);
		g.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
		g.drawString("Lives : " + Player.lives, 10, 20);
		
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
		}

	
	public void keyReleased(int k) {
		player.keyReleased(k);	
	}
}
