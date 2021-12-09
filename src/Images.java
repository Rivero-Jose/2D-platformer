import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Images {
	
	public static BufferedImage[] blocks;
	public static BufferedImage[] playerIMG ;
	
	public Images(){
		blocks = new BufferedImage[9];
		playerIMG = new BufferedImage[14];
		try {
			blocks[0] = ImageIO.read(this.getClass().getResourceAsStream("/grass.png"));
			blocks[1] = ImageIO.read(this.getClass().getResourceAsStream("/rock.png"));
			blocks[2] = ImageIO.read(this.getClass().getResourceAsStream("/bricks.png"));
			blocks[3] = ImageIO.read(this.getClass().getResourceAsStream("/black.png"));
			blocks[4] = ImageIO.read(this.getClass().getResourceAsStream("/sky.png"));
			blocks[5] = ImageIO.read(this.getClass().getResourceAsStream("/black.png"));
			blocks[6] = ImageIO.read(this.getClass().getResourceAsStream("/red.png"));
			blocks[7] = ImageIO.read(this.getClass().getResourceAsStream("/Boss.png"));
			blocks[8] = ImageIO.read(this.getClass().getResourceAsStream("/checkpoint.png"));
			
			playerIMG[0] = ImageIO.read(this.getClass().getResourceAsStream("/Stand.png"));
			playerIMG[1] = ImageIO.read(this.getClass().getResourceAsStream("/StandL.png"));
			
			playerIMG[2] = ImageIO.read(this.getClass().getResourceAsStream("/Run1.png"));
			playerIMG[3] = ImageIO.read(this.getClass().getResourceAsStream("/Run2.png"));
			playerIMG[4] = ImageIO.read(this.getClass().getResourceAsStream("/Run3.png"));
			playerIMG[5] = ImageIO.read(this.getClass().getResourceAsStream("/Run4.png"));
			
			playerIMG[6] = ImageIO.read(this.getClass().getResourceAsStream("/Run1L.png"));
			playerIMG[7] = ImageIO.read(this.getClass().getResourceAsStream("/Run2L.png"));
			playerIMG[8] = ImageIO.read(this.getClass().getResourceAsStream("/Run3L.png"));
			playerIMG[9] = ImageIO.read(this.getClass().getResourceAsStream("/Run4L.png"));
			
			playerIMG[10] = ImageIO.read(this.getClass().getResourceAsStream("/Jump1.png"));
			playerIMG[11] = ImageIO.read(this.getClass().getResourceAsStream("/Jump2.png"));
			
			playerIMG[12] = ImageIO.read(this.getClass().getResourceAsStream("/Jump1L.png"));
			playerIMG[13] = ImageIO.read(this.getClass().getResourceAsStream("/Jump2L.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
