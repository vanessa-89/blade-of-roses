import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
@SuppressWarnings("serial")
public class MapCanvas extends Canvas {

	static BufferedImage tileset;
	Tile[][] map;
	PlayerCharacter pc;
	
	/**
	 * 
	 */
	public MapCanvas() {
		
	}
	
	/**
	 * Loads the tileset to draw from.
	 * @param filename The tileset file.
	 */
	public void loadTileset(String filename) {
		try {
			File file = new File(filename);
			tileset = ImageIO.read(file);
		} catch (IOException ioe) {
			System.out.println("BOR could not load the image.");
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Add a character to the display
	 * @param pc The PlayerCharacter to add to the display.
	 */
	public void addCharacter(PlayerCharacter newPC) {
		pc = newPC;
	}
	
	/**
	 * 
	 * @param newMap The map to display.
	 */
	public void loadMap(Tile[][] newMap) {
		map = newMap;
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g) {
		int h = this.getHeight()/64;
		int w = this.getWidth()/64;
		int pcx = pc.getX();
		int pcy = pc.getY();
		int centerY, centerX;
		
		// Finds center of drawing region.
		//if (pcy is within Y range from edge) {
		//	centerY = pc.getY;
		//} else { //Character is close to edge
		//	centerY = h/2 + additional needed to keep centered;
		//}	
		//if (pcx is within X range from edge) {
		//	centerX = pc.getX;
		//} else { //Character is close to edge
		//	centerX = w/2 + additional needed to keep centered;
		//}
		
		//for (int i = centerY - h/2; i < centerY + h/2; i++) {
		//	for (int j = centerX - w/2; j < centerX + w/2; j++) {
				// Draw Floor
		
				// Draw Walls
		
				// Draw Objects
		
				// Draw Characters
		//	}
		//}
	}
}
