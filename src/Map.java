
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Map {
	
	private String path;
	private String line;
	
	private int width,height;
	
	public static Block[][] blocks;
//	private ArrayList<MovingBlock> movingBlocks;
	
	public Map(String loadPath){
		path = loadPath;
		loadMap();
	}
	
	public void draw(Graphics g){
		for(int r = 0; r < blocks.length; r++){
			for(int c = 0; c < blocks[r].length; c++){
				blocks[r][c].draw(g);
			}
		}
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public void loadMap(){
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		try{
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());
			
			blocks = new Block[height][width];
			
			
			for(int i = 0; i < height;i++){
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				for(int j = 0; j < width; j++){
				blocks[i][j] = new Block(j* Block.blockSize, i * Block.blockSize, Integer.parseInt(tokens[j]));
				}
		}
		}catch(NumberFormatException | IOException e){
			e.printStackTrace();
		}
	}
	
//	public ArrayList<MovingBlock> getMovingBlocks(){
//		return movingBlocks;
//	}
}
