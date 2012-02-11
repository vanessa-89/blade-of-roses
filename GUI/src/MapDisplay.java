import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapDisplay extends Component implements ImageObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final ImageObserver ImageObserver = 1;

	// Stores the .png of the tiles.
	static BufferedImage dungeonTiles;

	// Map array
	int[][] dmap;
	
	// Initializes the JPanel and loads the image.
	public static void initialize() {	
		try {
			File dtFile = new File("BOR-DungeonTiles.png");
			dungeonTiles = ImageIO.read(dtFile);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("BOR could not load the image.");
		}
	}
	
	// Loads the generated map onto the screen using the provided tileset.
	public void loadMap(int[][] map) {
		dmap = map;
		for (int i = 0; i < dmap.length; i++) {
			for (int j = 0; j < dmap[i].length; j++) {
				System.out.print(dmap[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// Required method to perform any sort of drawing.
	// Called by MapDisplay.repaint()
	public void paint(Graphics g) {
		for (int i = 0; i < dmap.length; i++) {
			for (int j = 0; j < dmap[i].length; j++) {
				// Calculates destination coordinates index of array.
				int dx1 = j*65;
				int dy1 = i*65;
				int dx2 = dx1 + 65;
				int dy2 = dy1 + 65;
				
				// Calculates source coordinates based on value in array.
				int mapVal = dmap[i][j];
				int count = 0;
				while (mapVal >= 12) { // May want to change this eventually to avoid Magic Numbers...
					mapVal -= 12;
					count++;
				}
				int sy1 = count*65 + count*2 + 2;
				int sx1 = mapVal*65 + mapVal*2;
				int sy2 = sy1 + 65;
				int sx2 = sx1 + 65;
				
				// Draws the images to screen.
				g.drawImage(dungeonTiles,dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,this);
			}
		}
	}
	
}
