package com.fetch.bor.bor;



public class TestArea {
	Tile[][] arena;
	int arenasize;
	
	public TestArea(int size){
		arenasize = size;
		arena = new Tile[arenasize][arenasize];
	
		for (int i = 0; i < arenasize; i ++) {
			for (int j = 0; j < arenasize; j ++) {
				arena[i][j] = new Tile();
			}
		}
		
		for (int i=1; i<arenasize-1; i++) {
			for (int j=1; j<arenasize-1; j++) {
				arena[i][j].setFloor(1);
			}
		}
		
		for (int i=1; i<arenasize-1; i++) {
			arena[i][1].setNWall(1);
			arena[i][arenasize-2].setSWall(1);
			arena[1][i].setWWall(1);
			arena[arenasize-2][i].setEWall(1);
			arena[(arenasize-1)/2][i].setEWall(1);
			arena[(arenasize-1)/2+1][i].setWWall(1);
		}
		
		arena[1][1].setNWCorner(1);
		arena[(arenasize-1)/2+1][1].setNWCorner(1);
		arena[arenasize-2][1].setNECorner(1);
		arena[(arenasize-1)/2][1].setNECorner(1);
		arena[1][arenasize-2].setSWCorner(1);
		arena[(arenasize-1)/2+1][arenasize-2].setSWCorner(1);
		arena[arenasize-2][arenasize-2].setSECorner(1);
		arena[(arenasize-1)/2][arenasize-2].setSECorner(1);
		
		//doors
		arena[(arenasize-1)/2][(arenasize-1)/2].setEWall(2);
		arena[(arenasize-1)/2+1][(arenasize-1)/2].setWWall(2);
		
	}
	
	public Tile[][] getTiles(){
		return arena;
	}
}

