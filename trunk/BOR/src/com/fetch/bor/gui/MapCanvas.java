package com.fetch.bor.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fetch.bor.bor.PlayerCharacter;
import com.fetch.bor.bor.Tile;

/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
@SuppressWarnings("serial")
public class MapCanvas extends Canvas {
	static final int TILE_SIZE = 64;
	
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
//		int pcx = pc.getX();
//		int pcy = pc.getY();
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
		
//		for (int i = centerY - h/2; i < centerY + h/2; i++) {
//			for (int j = centerX - w/2; j < centerX + w/2; j++) {
		for (int i = 0; i < map.length; i ++) {
			for (int j = 0; j < map[i].length; j++) {
				int dx1 = i*TILE_SIZE;
				int dx2 = dx1 + TILE_SIZE;
				int dy1 = j*TILE_SIZE;
				int dy2 = dy1 + TILE_SIZE;
				
				// Draw Floor
				if (map[i][j].getFloor() != 0) {
					g.drawImage(tileset,dx1,dy1,dx2,dy2,0,0,64,64,this);
				}
				
				// Draw Walls
				if (map[i][j].getNWall() != 0) {
					if (map[i][j].getNECorner() == 0 && map[i][j].getNWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 64, 64, 128, this);
					} else if (map[i][j].getNECorner() != 0 && map[i][j].getNWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 64, 128, 128, this);
					} else if (map[i][j].getNECorner() == 0 && map[i][j].getNWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 64, 192, 128, this);
					} else if (map[i][j].getNECorner() != 0 && map[i][j].getNWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 64, 256, 128, this);
					}
					if (map[i][j].getNWall() == 2) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 448, 64, 512, this);
					}
					if (map[i][j].getNWall() == 3) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 384, 64, 448, this);
					}
				}
				if (map[i][j].getWWall() != 0) {
					if (map[i][j].getNWCorner() == 0 && map[i][j].getSWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 128, 64, 192, this);
					} else if (map[i][j].getNWCorner() != 0 && map[i][j].getSWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 128, 128, 192, this);
					} else if (map[i][j].getNWCorner() == 0 && map[i][j].getSWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 128, 192, 192, this);
					} else if (map[i][j].getNWCorner() != 0 && map[i][j].getSWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 128, 256, 192, this);
					}
					if (map[i][j].getWWall() == 2) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 448, 128, 512, this);
					}
					if (map[i][j].getWWall() == 3) {
						// Closed door
					}
				}
				if (map[i][j].getEWall() != 0) {
					if (map[i][j].getNECorner() == 0 && map[i][j].getSECorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 192, 64, 256, this);
					} else if (map[i][j].getNECorner() != 0 && map[i][j].getSECorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 192, 128, 256, this);
					} else if (map[i][j].getNECorner() == 0 && map[i][j].getSECorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 192, 192, 256, this);
					} else if (map[i][j].getNECorner() != 0 && map[i][j].getSECorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 192, 256, 256, this);
					}
					if (map[i][j].getEWall() == 2) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 448, 192, 512, this);
					}
					if (map[i][j].getEWall() == 3) {
						// Closed door
					}
				}
				if (map[i][j].getSWall() != 0) {
					if (map[i][j].getSECorner() == 0 && map[i][j].getSWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 256, 64, 320, this);
					} else if (map[i][j].getSECorner() != 0 && map[i][j].getSWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 256, 128, 320, this);
					} else if (map[i][j].getSECorner() == 0 && map[i][j].getSWCorner() != 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 256, 192, 320, this);
					} else if (map[i][j].getSECorner() != 0 && map[i][j].getSWCorner() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 256, 256, 320, this);
					}
				}
				
				if (map[i][j].getNECorner() != 0 && map[i][j].getNWall() == 0 && map[i][j].getEWall() == 0) {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 320, 128, 384, this);
				}
				if (map[i][j].getNWCorner() != 0 && map[i][j].getNWall() == 0 && map[i][j].getWWall() == 0) {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 320, 64, 384, this);
				}
				if (map[i][j].getSECorner() != 0 && map[i][j].getSWall() == 0 && map[i][j].getEWall() == 0) {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 320, 192, 384, this);
				}
				if (map[i][j].getSWCorner() != 0 && map[i][j].getSWall() == 0 && map[i][j].getWWall() == 0) {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 320, 192, 384, this);
				}
				
				// Draw Objects
				g.drawImage(tileset,dx1,dy1,dx2,dy2, 0, 448, 64, 512, this);
				
				// Draw Characters
			}
		}
	}
}
