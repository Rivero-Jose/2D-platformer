import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MAINS {
	public static void main(String args[]){
//		Thread thread = new Thread();
		JFrame frame = new JFrame("ARCHETALE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		try{
//			thread.start();
			Muse M = new Muse();
			M.play();
//		}catch(Exception e){
//			
//		}
	}

}
