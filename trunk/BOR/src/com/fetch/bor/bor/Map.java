package com.fetch.bor.bor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fetch.bor.gui.Sprite;
import com.fetch.bor.gui.SpriteStore;

public class Map {

	private static final int TILE_SIZE = 64;
	private BufferedImage tileset;
	
	private int cameraX;
	private int cameraY;
	
	private Tile[][] map;
	private boolean newMap;
	private BufferedImage buffer;

	public Map(String ref, int x, int y, Tile[][] map) {
		try {
			File file = new File(ref);
			tileset = ImageIO.read(file);
		} catch (IOException ioe) {
			System.out.println("BOR could not load the image.");
			ioe.printStackTrace();
		}
		this.cameraX = x;
		this.cameraY = y;
		this.map = map;
		
		newMap = true;
	}
	
	public void moveNorth() {
		System.out.println("PC - North?");
		System.out.println("X: " + cameraX);
		System.out.println("Y: " + cameraY);
		System.out.println(map[cameraX][cameraY].dirIndex.toString());
		if(cameraY > 0 && !map[cameraX][cameraY].dirIndex.contains(0)) {
			cameraY--;
			System.out.println("PC - North: Yes");
		}
	}
	
	public void moveEast() {
		System.out.println("PC - East?");
		System.out.println("X: " + cameraX);
		System.out.println("Y: " + cameraY);
		System.out.println(map[cameraX][cameraY].dirIndex.toString());
		if(cameraX < map.length - 2 && !map[cameraX][cameraY].dirIndex.contains(1)) {
			cameraX++;
			System.out.println("PC - East: Yes");
		}
	}
	
	public void moveWest() {
		System.out.println("PC - West?");
		System.out.println("X: " + cameraX);
		System.out.println("Y: " + cameraY);
		System.out.println(map[cameraX][cameraY].dirIndex.toString());
		if(cameraX > 0 && !map[cameraX][cameraY].dirIndex.contains(3)) {
			cameraX--;
			System.out.println("PC - West: Yes");
		}
	}
	
	public void moveSouth() {
		System.out.println("PC - South?");
		System.out.println("X: " + cameraX);
		System.out.println("Y: " + cameraY);
		System.out.println(map[cameraX][cameraY].dirIndex.toString());
		if(cameraY < map[0].length - 2 && !map[cameraX][cameraY].dirIndex.contains(2)) {
			cameraY++;
			System.out.println("PC - South: Yes");
		}
	}
	
	public void draw(Graphics2D g) {
		if (newMap) {
			buffer = new BufferedImage(map.length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics gBuffer = buffer.getGraphics();
			renderMap(gBuffer);
		}
		g.drawImage(render(buffer), 0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE,
				0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE, null);
	}
	
	private BufferedImage render(BufferedImage background) {
		BufferedImage buf = new BufferedImage(map.length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buf.getGraphics();
		
		int locx1 = cameraX * TILE_SIZE;
		int locx2 = locx1 + TILE_SIZE;
		int locy1 = cameraY * TILE_SIZE;
		int locy2 = locy1 + TILE_SIZE;
		
		g.drawImage(drawElements(background), 0, 0, 640, 576, locx1 - 5*64, locy1 - 4*64, locx2 + 4*64, locy2 + 4*64, null);
		
		return buf;
	}
	
	private BufferedImage drawElements(BufferedImage background) {
		BufferedImage buf = new BufferedImage(map.length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = buf.getGraphics();
		g.drawImage(background, 0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE,
				0, 0, map.length * TILE_SIZE, map.length * TILE_SIZE, null);		
		return buf;
	}
	
	private void renderMap(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int dx1 = i * TILE_SIZE;
				int dx2 = dx1 + TILE_SIZE;
				int dy1 = j * TILE_SIZE;
				int dy2 = dy1 + TILE_SIZE;
				// Draw Floor
				if (map[i][j].getFloor() != 0) {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 0, 64, 64, null);

					// Draw Walls
					if (map[i][j].getNWall() != 0) {
						// Wall and Corners
						if (map[i][j].getNECorner() == 0
								&& map[i][j].getNWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 64, 64,
									128, null);
						} else if (map[i][j].getNECorner() != 0
								&& map[i][j].getNWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 64,
									128, 128, null);
						} else if (map[i][j].getNECorner() == 0
								&& map[i][j].getNWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 64,
									192, 128, null);
						} else if (map[i][j].getNECorner() != 0
								&& map[i][j].getNWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 64,
									256, 128, null);
						}
						if (map[i][j].getNWall() == 2) {
							// Open Door
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 448,
									64, 512, null);
						}
						if (map[i][j].getNWall() == 3) {
							// Closed Door
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 384,
									64, 448, null);
						}
					}
					if (map[i][j].getWWall() != 0) {
						if (map[i][j].getNWCorner() == 0
								&& map[i][j].getSWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 128,
									64, 192, null);
						} else if (map[i][j].getNWCorner() != 0
								&& map[i][j].getSWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 128,
									128, 192, null);
						} else if (map[i][j].getNWCorner() == 0
								&& map[i][j].getSWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 128,
									192, 192, null);
						} else if (map[i][j].getNWCorner() != 0
								&& map[i][j].getSWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 128,
									256, 192, null);
						}
						if (map[i][j].getWWall() == 2) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 448,
									128, 512, null);
						}
						if (map[i][j].getWWall() == 3) {
							// Closed door
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 448,
									128, 448, null);
						}
					}
					if (map[i][j].getEWall() != 0) {
						if (map[i][j].getNECorner() == 0
								&& map[i][j].getSECorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 192,
									64, 256, null);
						} else if (map[i][j].getNECorner() != 0
								&& map[i][j].getSECorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 192,
									128, 256, null);
						} else if (map[i][j].getNECorner() == 0
								&& map[i][j].getSECorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 192,
									192, 256, null);
						} else if (map[i][j].getNECorner() != 0
								&& map[i][j].getSECorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 192,
									256, 256, null);
						}
						if (map[i][j].getEWall() == 2) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 448,
									192, 512, null);
						}
						if (map[i][j].getEWall() == 3) {
							// Closed door
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 448,
									192, 448, null);
						}
					}
					if (map[i][j].getSWall() != 0) {
						if (map[i][j].getSECorner() == 0
								&& map[i][j].getSWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 256,
									64, 320, null);
						} else if (map[i][j].getSECorner() != 0
								&& map[i][j].getSWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 256,
									128, 320, null);
						} else if (map[i][j].getSECorner() == 0
								&& map[i][j].getSWCorner() != 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 256,
									192, 320, null);
						} else if (map[i][j].getSECorner() != 0
								&& map[i][j].getSWCorner() == 0) {
							g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 256,
									256, 320, null);
						}
					}

					if (map[i][j].getNECorner() != 0
							&& map[i][j].getNWall() == 0
							&& map[i][j].getEWall() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 320, 128,
								384, null);
					}
					if (map[i][j].getNWCorner() != 0
							&& map[i][j].getNWall() == 0
							&& map[i][j].getWWall() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 0, 320, 64,
								384, null);
					}
					if (map[i][j].getSECorner() != 0
							&& map[i][j].getSWall() == 0
							&& map[i][j].getEWall() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 128, 320, 192,
								384, null);
					}
					if (map[i][j].getSWCorner() != 0
							&& map[i][j].getSWall() == 0
							&& map[i][j].getWWall() == 0) {
						g.drawImage(tileset, dx1, dy1, dx2, dy2, 192, 320, 192,
								384, null);
					}
					
					int temp = 20;
					int index = 0;
					while (index < map[i][j].dirIndex.size()) {
						g.setColor(Color.RED);
						g.drawString(Integer.toString(map[i][j].dirIndex.get(index)), dx1 + 10, dy1 + temp);
						temp += 10;
						index++;
					}
					temp = 20;
					g.drawString(Integer.toString(i), dx1 + 30, dy1 + temp);
					g.drawString(Integer.toString(j), dx1 + 30, dy1 + temp + 10);
					
				} else {
					g.drawImage(tileset, dx1, dy1, dx2, dy2, 64, 0, 128, 64,
							null);
				}
			}
		}
		
		newMap = false;
	}
}
