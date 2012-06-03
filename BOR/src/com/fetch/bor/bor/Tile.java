package com.fetch.bor.bor;

/**
 * 
 * @author MattFMorris
 *
 */
public class Tile {
	public int[][] tile;
	
	public Tile() {
		tile = new int[3][3];
		tile[1][1] = 0;
	}
	public Tile(int[][] inTile) {
		tile = inTile;
	}
	
	public int getFloor() {
		return tile[1][1];
	}
	
	public int getNWall() {
		return tile[1][0];
	}
	
	public int getSWall() {
		return tile[1][2];
	}
	
	public int getEWall() {
		return tile[2][1];
	}
	
	public int getWWall() {
		return tile[0][1];
	}
	
	public int getNWCorner() {
		return tile[0][0];
	}
	
	public int getNECorner() {
		return tile[2][0];
	}
	
	public int getSWCorner() {
		return tile[0][2];
	}
	
	public int getSECorner() {
		return tile[2][2];
	}
}
