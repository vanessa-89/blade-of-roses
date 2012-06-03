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
		
		for (int i=1; i<arenasize-2; i++) {
			for (int j=1; j<arenasize-2; j++) {
				arena[i][j].tile[1][1] = 1;
			}
		}
		
		for (int i=1; i<arenasize-2; i++) {
			arena[1][i].tile[1][0] = 1;
			arena[arenasize-2][i].tile[1][2] = 1;
			arena[i][1].tile[0][1] = 1;
			arena[i][arenasize-2].tile[2][1] = 1;
			arena[arenasize/2][i].tile[2][1] = 1;
			arena[arenasize/2+1][i].tile[0][1] = 1;		
		}
		
		//top left corners
		arena[1][1].tile[0][0] = 1;
		arena[arenasize/2+1][1].tile[0][0] = 1;
		//top right corners
		arena[arenasize-2][1].tile[2][0] = 1;
		arena[arenasize/2][1].tile[2][0] = 1;
		//bottom left corners
		arena[1][arenasize-2].tile[0][2] = 1;
		arena[arenasize/2+1][arenasize-2].tile[0][2] = 1;
		//bottom right corners
		arena[arenasize-2][arenasize-2].tile[2][2] = 1;
		arena[arenasize/2][arenasize-2].tile[2][2] = 1;
		
		//doors
		arena[arenasize/2][arenasize/2].tile[0][1] = 2;
		arena[arenasize/2+1][arenasize/2].tile[0][1] = 2;
		
	}
	
	public Tile[][] getTiles(){
		return arena;
	}
}

