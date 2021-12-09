import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Player extends Rectangle {
	private boolean animateR = false, animateL = false, animateJR = false, animateJL = false, animateJ = false;
	private int frameDelay = 0;
	private int frameR = 2, frameL = 6, frameJR = 10, frameJL = 12, frameJ = 10;
	private boolean right = false, left = false, jumping = false, falling = false, running = false,fix = false,restart = false,time2 = false;
	private boolean topCollision = false;
	
	static final long serialVersionUID =1L;
	
	
	public static int lives = 3;
	public static double x,y;
	private int width,height;
	private int frameRate = 5;
	private double jumpSpeed = 6.5;
	private double currentJumpSpeed = jumpSpeed;
	public static double maxFallSpeed = 6.5;
	 public static double currentFallSpeed = .1;
	private double moveSpeed = 4;
	private double runSpeed = 3;
	
	private Thread thread = new Thread();
	private boolean bbb = true;
	private boolean bb = true;
	public Player( int width, int height){
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
		
	}
	
	public void tick(Block[][] b){
		if(restart){
			GameState.yOffSet = 529;
			GameState.xOffSet = 80;
		}
		if(fix){
			GameState.yOffSet -= .1;
			GameState.xOffSet -= .1;
		}
		if(GameState.yOffSet > 900){
			if(GameState.xOffSet > 11239 &&  lives >= 0){
				lives--;
				GameState.xOffSet = 11329;
				GameState.yOffSet = 520;
			}else if(GameState.xOffSet > 4700 &&  lives >= 0){
				lives--;
				GameState.xOffSet = 4700;
				GameState.yOffSet = 520;
			}else if(GameState.yOffSet > 0 &&  lives >= 0){
				GameState.yOffSet = 529;
				GameState.xOffSet = 80;
				lives--;
			}else{
				lives = 3;
				GameState.yOffSet = 529;
				GameState.xOffSet = 80;
				JOptionPane.showMessageDialog(null, "You have used up all your lives setting lives back to 3");
			
			}
		}
		if(lives == -1){
			lives = 3;
			GameState.yOffSet = 529;
			GameState.xOffSet = 80;
			JOptionPane.showMessageDialog(null, "You have used up all your lives setting lives back to 3");
		}
		int iX = (int)x;
		int iY = (int)y;
		if(GameState.yOffSet > 900){
			GameState.yOffSet = 529;
			GameState.xOffSet = 80;
			lives--;
		}
		if(GameState.xOffSet > 11631){
			if(bb){
				JOptionPane.showMessageDialog(null, "You have cleared the level, now have fun in this room forever, \n unless you know what your supposed to do...\n" +
						"Hint: try jumping and see what happens, then hit the 'K' key, and repeat something you just did");
				bb = false;
			}
			currentFallSpeed = 1;
			maxFallSpeed = -100;
			if(time2){
				GameState.xOffSet = 2082;
				GameState.yOffSet = -420;
				time2 = false;
			}
		}
		if(GameState.xOffSet > 11940 && GameState.yOffSet  < -150 && GameState.xOffSet < 12000 && GameState.yOffSet  > -300){
			if(bbb){
				bbb = false;
				ImageIcon picture = new ImageIcon(Player.class.getResource("Ending.png"));
				JOptionPane.showMessageDialog(null, null, "", 0, picture);
				JOptionPane.showMessageDialog(null, "This ends the 2D Side Scroller portion of this game");
				JOptionPane.showMessageDialog(null, "Congratz on making it to the end!\n" +
						"Was it hard? To get all the way over here,\n" +
						"Well theres one more thing you have to do.\n" +
						"Fight this angel, Good Luck~\n" +
						"The controls are Arrow keys for movement \n" +
						"and 'Z' for shooting" +
						"NOTE: It can only be played once, Good Luck");
				Game G = new Game();
				G.start();
			}
			
		}
		for(int i = 0; i < b.length; i++){
			for(int j = 0; j < b[i].length; j++){
			if(b[i][j].getID() == 6){
				if(Collision.playerBlock(new Point(iX +(int)GameState.xOffSet - 1, iY +(int)GameState.yOffSet + 2),b[i][j]) || 
						Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet - 1, iY + height +(int)GameState.yOffSet - 1),b[i][j] )){
					left = false;
				}
			}
			if(b[i][j].getID() != 0 && b[i][j].getID() != 4 && b[i][j].getID() != 6 && b[i][j].getID() != 9){
			//right
			if(Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet + 2, iY +(int)GameState.yOffSet + 2),b[i][j]) || 
					Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet + 2, iY + height +(int)GameState.yOffSet - 1),b[i][j] )){
				right = false;
			}
			//left
			if(Collision.playerBlock(new Point(iX +(int)GameState.xOffSet - 1, iY +(int)GameState.yOffSet + 2),b[i][j]) || 
					Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet - 1, iY + height +(int)GameState.yOffSet - 1),b[i][j] )){
				left = false;
			}
			if(Collision.playerBlock(new Point(iX +(int)GameState.xOffSet + 1, iY +(int)GameState.yOffSet),b[i][j]) || 
					Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet - 2, iY+(int)GameState.yOffSet),b[i][j] )){
				jumping = false;
				falling = true;
			}
			if(Collision.playerBlock(new Point(iX +(int)GameState.xOffSet + 3, iY + height +(int)GameState.yOffSet + 2),b[i][j]) || 
					Collision.playerBlock(new Point(iX + width +(int)GameState.xOffSet - 3, iY + height +(int)GameState.yOffSet + 2),b[i][j] )){
				y = b[i][j].getY() - height - GameState.yOffSet;
				falling = false;
				topCollision = true;
			}else{
				if(!topCollision && !jumping){
					falling = true;
				}
			}
		}
		}
		}
		
		
	
		
		
		
		topCollision = false;
		if(right){
			GameState.xOffSet+= moveSpeed;
//			System.out.println(GameState.xOffSet + "   " + GameState.yOffSet);
		}if(left){
			GameState.xOffSet-= moveSpeed;
//			System.out.println(GameState.xOffSet + "   " + GameState.yOffSet);
		}if(jumping){
			GameState.yOffSet -= currentJumpSpeed;
//			System.out.println(GameState.xOffSet + "   " + GameState.yOffSet);
			currentJumpSpeed -= .1;
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			
		}if(falling){
			GameState.yOffSet += currentFallSpeed;
//			System.out.println(GameState.xOffSet + "   " + GameState.yOffSet);
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += .1;
						
			}
//			if(currentFallSpeed > maxFallSpeed){
//				currentFallSpeed = 0;
//				falling = false;
//			}
		}
		if(!falling){
			currentFallSpeed = .1;
		}
		if(right && running){
			GameState.xOffSet+= runSpeed;
		}if(left && running){
			GameState.xOffSet-= runSpeed;
		}
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
	
	public void draw(Graphics g){
		g.setColor(Color.CYAN);
		g.drawImage(Images.playerIMG[0],(int)x, (int)y, width, height, null);
		try{
			thread.start();
		}catch(Exception e){
			
		}
		if((jumping && right)||(falling && right)){
			animateJR = true;
			try{
				g.drawImage(Images.playerIMG[frameJR],(int)x, (int)y, width, height, null);
			}catch(Exception e){
				
			}
		}else if((jumping && left)|| (falling && left)){
			animateJL = true;
			try{
				g.drawImage(Images.playerIMG[frameJL],(int)x, (int)y, width, height, null);
			}catch(Exception e){
				
			}
		}else if(jumping || falling){
			animateJ = true;
			try{
				g.drawImage(Images.playerIMG[frameJ],(int)x, (int)y, width, height, null);
			}catch(Exception e){
				
			}
		}else if(right){
			animateR = true;
			try{
				g.drawImage(Images.playerIMG[frameR],(int)x, (int)y, width, height, null);
			}catch(Exception e){
				
			}
		}else if(left){
			animateL = true;
			try{
				g.drawImage(Images.playerIMG[frameL],(int)x, (int)y, width, height, null);
				
			}catch(Exception e){
				
			}
		}else{
			animateR = false;
			animateL = false;
			animateJR = false;
			animateJL = false;
			animateJ = false;
			
		}
		if(animateR){
			frameDelay++;
				if(frameDelay >= frameRate){
					frameDelay = 0;
					frameR++;
					if(frameR == 5){
						frameR = 2;
				}
			}
		}
		if(animateL){
			frameDelay++;
				if(frameDelay >= frameRate){
					frameDelay = 0;
					frameL++;
					if(frameL == 9){
						frameL = 6;
				}
			}
		}
		if(animateJR){
			frameDelay++;
				if(frameDelay >= frameRate){
					frameDelay = 0;
					frameJR++;
					if(frameJR == 11){
						frameJR = 10;
				}
			}
		}
		if(animateJL){
			frameDelay++;
				if(frameDelay >= frameRate){
					frameDelay = 0;
					frameJL++;
					if(frameJL == 13){
						frameJL = 12;
				}
			}
		}
		if(animateJ){
			frameDelay++;
				if(frameDelay >= frameRate){
					frameDelay = 0;
					frameJ++;
					if(frameJ == 11){
						frameJ = 10;
				}
			}
		}
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_D){
			right = true;
		}else if(k == KeyEvent.VK_A){
			left = true;
		}else if(k == KeyEvent.VK_W && !jumping && !falling){
			jumping = true;
		}else if(k == KeyEvent.VK_L){
			running = true;
		}else if(k == KeyEvent.VK_I){
			restart = true;
		}else if(k == KeyEvent.VK_J){
			fix = true;
		}else if(k == KeyEvent.VK_K){
			time2 = true;
		}
	}
	
	public void keyReleased(int k){
		if(k == KeyEvent.VK_D){
			right = false;
		}else if(k == KeyEvent.VK_A){
			left = false;
		}else if(k == KeyEvent.VK_L){
			running = false;
		}else if(k == KeyEvent.VK_I){
			restart = false;
		}else if(k == KeyEvent.VK_J){
			fix = false;
		}else if(k == KeyEvent.VK_K){
			time2 = false;
		}
	}
}
