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
	//temporary stack
	public Stack<Integer> structIndex;
	
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
		if (!dirIndex.contains(0) && value==1)
			dirIndex.push(0);
		else if (value == 0)
			dirIndex.removeElement(0);
		else if (value==2)
			dirIndex.removeElement(0);
	}
	
	public int getSWall() {
		return tile[1][2];
	}
	
	public void setSWall(int value) {
		tile[1][2] = value;
		if (!dirIndex.contains(2) && value==1)
			dirIndex.push(2);
		else if (value == 0)
			dirIndex.removeElement(2);
		else if (value==2)
			dirIndex.removeElement(2);
	}
	
	public int getEWall() {
		return tile[2][1];
	}
	
	public void setEWall(int value) {
		tile[2][1] = value;
		if (!dirIndex.contains(1) && value==1)
			dirIndex.push(1);
		else if (value == 0)
			dirIndex.removeElement(1);
		else if (value==2)
			dirIndex.removeElement(1);
	}
	
	public int getWWall() {
		return tile[0][1];
	}
	
	public void setWWall(int value) {
		tile[0][1] = value;
		if (!dirIndex.contains(3) && value==1)
			dirIndex.push(3);
		else if (value == 0)
			dirIndex.removeElement(3);
		else if (value==2)
			dirIndex.removeElement(3);
	}
	
	public int getNWCorner() {
		if (tile[0][1] != 0 && tile[1][0] != 0) {
			tile[0][0] = 1;
		}
		return tile[0][0];
	}
	
	public void setNWCorner(int value) {
		tile[0][0] = value;
	}
	
	public int getNECorner() {
		if (tile[2][1] != 0 && tile[1][0] != 0) {
			tile[2][0] = 1;
		}
		return tile[2][0];
	}
	
	public void setNECorner(int value) {
		tile[2][0] = value;
	}
	
	public int getSWCorner() {
		if (tile[0][1] != 0 && tile[1][2] != 0) {
			tile[0][2] = 1;
		}
		return tile[0][2];
	}
	
	public void setSWCorner(int value) {
		tile[0][2] = value;
	}
	
	public int getSECorner() {
		if (tile[2][1] != 0 && tile[1][2] != 0) {
			tile[2][2] = 1;
		}
		return tile[2][2];
	}
	
	public void setSECorner(int value) {
		tile[2][2] = value;
	}
	
}


