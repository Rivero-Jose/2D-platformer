import java.awt.Point;


public class Collision {
	public Collision(){
		
	}
	public static boolean playerBlock(Point p, Block b){
		return b.contains(p);
	}
	
//	public static boolean playerMovingBlock(Point p, MovingBlock b){
//		return b.contains(p);
//	}
}
