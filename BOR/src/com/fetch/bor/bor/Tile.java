package com.fetch.bor.bor;

/**
 * 
 * @author MattFMorris
 *
 */
public class Tile {
	int[][] tile;
	int floor;
	
	public Tile() {
		tile = new int[3][3];
		floor = 0;
	}
	public Tile(int[][] inTile) {
		tile = inTile;
		floor = tile[1][1];
	}
}
