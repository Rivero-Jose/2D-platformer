import java.awt.Graphics;
import java.awt.Rectangle;


public class Block extends Rectangle {
	
	
	private static final long serialVersionUID = 1L;
	public static final int blockSize = 64;
	
	private int id;
	
	public Block(int x, int y, int id){
		setBounds(x,y, blockSize, blockSize);
		this.id = id;
		
		
	}
	
	public void tick(){
		
		
	}
	public void draw(Graphics g){
		if(id != 0){
		g.drawImage(Images.blocks[id - 1],x - (int)GameState.xOffSet,y - (int)GameState.yOffSet, width, height,null);
		}
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public int changeID(){
		return 0;
	}
}
