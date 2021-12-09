import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
	static final long serialVersionUID =1L;
	
	public Thread thread;// = new Thread(this);
	private boolean isRunning = false;
	public Thread Music = new Thread();
	private int FPS = 60;
	private long targetTime = 1000/ FPS;
	
	private GameStateManager gsm;
	
	public GamePanel(){
		
		new Images();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	public void run(){
		long start,elpased,wait;
		
		gsm = new GameStateManager();
		while(isRunning){
			start = System.nanoTime();
			
			tick();
			repaint();
			
			elpased = System.nanoTime() - start;
			wait = targetTime - elpased / 1000000;
			
			if(wait < 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void tick(){
		gsm.tick();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//g.clearRect(0, 0, WIDTH, HEIGHT);
		gsm.draw(g);
	}
	
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
		
	}

	
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
		
	}

	
	public void keyTyped(KeyEvent e) {


	}
}
