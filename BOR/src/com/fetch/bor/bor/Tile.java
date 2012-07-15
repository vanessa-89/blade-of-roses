package com.fetch.bor.bor;

import java.util.Stack;

/**
 * 
 * @author MattFMorris
 *
 */
public class Tile {
	public int[][] tile;
	public Stack<Integer> dirIndex;
	
	public Tile() {
		tile = new int[3][3];
		tile[1][1] = 0;
		dirIndex = new Stack<Integer>();
	}
	public Tile(int[][] inTile) {
		tile = inTile;
	}
	
	public int getFloor() {
		return tile[1][1];
	}
	
	public void setFloor(int value) {
		tile[1][1] = value;
	}
	
	public int getNWall() {
		return tile[1][0];
	}
	
	public void setNWall (int value) {
		tile[1][0] = value;
	}
	
	public int getSWall() {
		return tile[1][2];
	}
	
	public void setSWall(int value) {
		tile[1][2] = value;
	}
	
	public int getEWall() {
		return tile[2][1];
	}
	
	public void setEWall(int value) {
		tile[2][1] = value;
	}
	
	public int getWWall() {
		return tile[0][1];
	}
	
	public void setWWall(int value) {
		tile[0][1] = value;
	}
	
	public int getNWCorner() {
		return tile[0][0];
	}
	
	public void setNWCorner(int value) {
		tile[0][0] = value;
	}
	
	public int getNECorner() {
		return tile[2][0];
	}
	
	public void setNECorner(int value) {
		tile[2][0] = value;
	}
	
	public int getSWCorner() {
		return tile[0][2];
	}
	
	public void setSWCorner(int value) {
		tile[0][2] = value;
	}
	
	public int getSECorner() {
		return tile[2][2];
	}
	
	public void setSECorner(int value) {
		tile[2][2] = value;
	}
	
	public void setNWallSafe (int value) {
		if (tile[1][0] != 2){
			tile[1][0] = value;
		}
	}
	
	public void setSWallSafe(int value) {
		if (tile[1][2] != 2)
		tile[1][2] = value;
	}
	
	public void setEWallSafe(int value) {
		if (tile[2][1] != 2)
		tile[2][1] = value;
	}
	
	public void setWWallSafe(int value) {
		if (tile[0][1] != 2)
		tile[0][1] = value;
	}
	
	
	
}


