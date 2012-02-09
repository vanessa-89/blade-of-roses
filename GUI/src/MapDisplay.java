import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapDisplay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final ImageObserver ImageObserver = null;

	// Stores the .png of the tiles.
	static BufferedImage dungeonTiles;
	Graphics2D g2d = (Graphics2D)getGraphics();
	
	// Initializes the JPanel and loads the image.
	public static void initialize() {	
		try {
			File dtFile = new File("/borGUI/src/BOR-DungeonTiles.png");
			System.out.println(dtFile.exists());
			dungeonTiles = ImageIO.read(dtFile);
			System.out.println("IO");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Uh-oh!");
		}	
	}
	
	// 
	public void loadMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; i++) {
				int dx1 = j*65;
				int dy1 = i*65;
				int dx2 = dx1 + 65;
				int dy2 = dy1 + 65;
				
				int mapVal = map[i][j];
				int count = 0;
				while (mapVal >= 12) {
					mapVal -= 12;
					count++;
				}
				int sx1 = count*65 + count*2 + 2;
				int sy1 = mapVal*65 + mapVal*2;
				int sx2 = sx1 + 65;
				int sy2 = sy1 + 65;
				
				g2d.drawImage(dungeonTiles,dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,ImageObserver);
			}
		}
	}
	
}
