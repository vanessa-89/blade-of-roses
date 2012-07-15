package com.fetch.bor.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.fetch.bor.bor.Character;
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
	
	private static BufferedImage ui;
	
	private static BufferedImage tileset;
	private Tile[][] map;
	private Character pc;
//	private boolean needToRedrawMap;
	
	private DirectionKeyListener dirKeyLis = new DirectionKeyListener();

	private boolean newMap = true;
	private BufferedImage buffer;
	
	private ArrayList<Character> elements = new ArrayList<Character>();
	
	/**
	 * 
	 */
	public MapCanvas() {
		this.addKeyListener(dirKeyLis);
//		needToRedrawMap = true;
		
		try {
			File file = new File("BORUI.png");
			ui = ImageIO.read(file);
		} catch (IOException ioe) {
			System.out.println("BOR could not load the image.");
			ioe.printStackTrace();
		}
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
	public void addCharacter(Character newPC) {
		pc = newPC;
	}
	
	public void addElement(Character newElement) {
		elements.add(newElement);
	}
	
	/**
	 * Removes the specified element from the drawn objects.
	 * Note: the param must be the exact object to be removed.
	 * @param remove
	 */
	public void removeElement(Character remove) {
		elements.remove(remove);
	}
	
	/**
	 * 
	 * @param newMap The map to display.
	 */
	public void loadMap(Tile[][] newMap) {
		map = newMap;
		this.newMap = true;
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g) {
		if (newMap) {
			buffer = new BufferedImage(map.length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics gBuffer = buffer.getGraphics();
			processMap(gBuffer);
		}
		g.drawImage(render(buffer), 0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE,
				0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE, this);		
	}
	
	private BufferedImage render(BufferedImage background) {
		BufferedImage buf = new BufferedImage(map.length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buf.getGraphics();
		g.drawImage(background, 0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE,
				0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE, this);
		
		int locx1 = pc.getX() * TILE_SIZE;
		int locx2 = locx1 + TILE_SIZE;
		int locy1 = pc.getY() * TILE_SIZE;
		int locy2 = locy1 + TILE_SIZE;
		g.drawImage(pc.getSprite(), locx1, locy1, locx2, locy2, 0, 0, 64, 64, this);
		
		for (Character c : elements) {
			g.drawImage(c.getSprite(), c.getX() * TILE_SIZE, c.getY() * TILE_SIZE, c.getX() * TILE_SIZE + 64, c.getY() * TILE_SIZE + 64, 0, 0, 64, 64, this);
		}
		
		g.drawImage(ui, 0, 0, 800, 600, 0, 0, 800, 600, this);
		
		return buf;
	}

	private void processMap(Graphics g) {
		for (int i = 0; i < map.length; i ++) {
			for (int j = 0; j < map[i].length; j++) {
				int dx1 = i * TILE_SIZE;
				int dx2 = dx1 + TILE_SIZE;
				int dy1 = j * TILE_SIZE;
				int dy2 = dy1 + TILE_SIZE;
				
				if (g.hitClip(dx1, dy1, dx2, dy2)) {
					// Draw Floor
					if (map[i][j].getFloor() != 0) {
						g.drawImage(tileset,dx1,dy1,dx2,dy2,0,0,64,64,this);
					
						// Draw Walls
						if (map[i][j].getNWall() != 0) {
							// Wall and Corners
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
								// Open Door
								g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 448, 64, 512, this);
							}
							if (map[i][j].getNWall() == 3) {
								// Closed Door
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
								g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 448, 128, 448, this);
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
								g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 448, 192, 448, this);
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
						
						// Draw Characters
							
						
					} else {
						g.drawImage(tileset,dx1,dy1,dx2,dy2,64,0,128,64,this);
					}
				}
			}
		}
		
		newMap = false;
	}

	private class DirectionKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_UP) {
				if (!map[pc.getX()][pc.getY()].dirIndex.contains(0)) {
					pc.moveNorth();
				}
				if (!map[elements.get(0).getX()][elements.get(0).getY()].dirIndex.contains(3)) {
					elements.get(0).moveWest();
				}
				repaint();
			} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				if (!map[pc.getX()][pc.getY()].dirIndex.contains(3)) {
					pc.moveWest();
				}
				if (!map[elements.get(0).getX()][elements.get(0).getY()].dirIndex.contains(2)) {
					elements.get(0).moveSouth();
				}
				repaint();
			} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (!map[pc.getX()][pc.getY()].dirIndex.contains(1)) {
					pc.moveEast();
				}
				if (!map[elements.get(0).getX()][elements.get(0).getY()].dirIndex.contains(0)) {
					elements.get(0).moveNorth();
				}
				repaint();
			} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				if (!map[pc.getX()][pc.getY()].dirIndex.contains(2)) {
					pc.moveSouth();
				}
				if (!map[elements.get(0).getX()][elements.get(0).getY()].dirIndex.contains(1)) {
					elements.get(0).moveEast();
				}
				repaint();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
