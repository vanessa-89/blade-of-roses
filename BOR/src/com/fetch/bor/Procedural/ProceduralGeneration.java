package com.fetch.bor.Procedural;

import java.util.Random;

import com.fetch.bor.bor.Tile;

public class ProceduralGeneration {


	ProceduralDataType[][] genMap;
	
	int xSize, ySize;
	int xDraw, yDraw;
	int xObject, yObject;
	char nextGenChar;
	int nextSrcInt;
	char nextSrcChar;
	int xTemp, yTemp;
	int direction;
	
	
	ProceduralHallway[] hArchive;
	int hCounter;
	ProceduralRoom[] rArchive;
	int rCounter;
	
	boolean repeat;
	Random rand = new Random();
	
	
	public ProceduralGeneration ( int x, int y ) {
		xSize = x;
		ySize = y;
		xDraw = 0;
		yDraw = 0;
		hCounter = 0;
		rCounter = 0;
		genMap = new ProceduralDataType[xSize][ySize];
		for (int i = 0; i < xSize; i ++) {
			for (int j = 0; j < ySize; j ++) {
				genMap[i][j] = new ProceduralDataType();
			}
		}
		
		
	}
	
	
	public void Generate () {
		
		//Create a room centered in the middle.
		rArchive[rCounter] = new ProceduralRoom();
		xObject = rArchive[rCounter].getWidth();
		yObject = rArchive[rCounter].getHeight();
		xDraw = ((xSize-1)/2)-(xObject/2);
		yDraw = ((ySize-1)/2)-(yObject/2);
		rArchive[rCounter].pushX(xDraw);
		rArchive[rCounter].pushY(yDraw);
		for (int i=xDraw ; i<xDraw+xObject; i++){
			for (int j=yDraw ; j<yDraw+yObject; j++){
				genMap[i][j].tile.setFloor(1);
				genMap[i][j].tile.setEWall(0);
				genMap[i][j].tile.setWWall(0);
				genMap[i][j].tile.setNWCorner(0);
				genMap[i][j].tile.setNECorner(0);
				genMap[i][j].tile.setSECorner(0);
				genMap[i][j].tile.setSWCorner(0);
				if (i==xDraw){
					rArchive[rCounter].addTopEdge(i,j);
					genMap[i][j].tile.setNWallSafe(0);
				}
				else
					genMap[i][j].tile.setNWall(0);
				
				if (i==xDraw+xObject){
					rArchive[rCounter].addBottomEdge(i,j);
					genMap[i][j].tile.setSWallSafe(0);
				}
				else
					genMap[i][j].tile.setSWall(0);
				
				if (j==yDraw) {
					rArchive[rCounter].addLeftEdge(i,j);
					genMap[i][j].tile.setWWallSafe(0);
				}
				else
					genMap[i][j].tile.setWWall(0);
				
				if (j==yDraw+yObject) {
					rArchive[rCounter].addRightEdge(i,j);
					genMap[i][j].tile.setEWallSafe(0);
				}
				else
					genMap[i][j].tile.setEWall(0);
					
				genMap[i][j].typeIndex.push('r');
				genMap[i][j].intIndex.push(0);
			}
		}
		rCounter++;
		
		while ()//some condition for ending Generation
		{
			//determine which to generate
			if ( (rand.nextDouble()*rCounter+hCounter) < rCounter)
				nextGenChar = 'h';
			else
				nextGenChar = 'r';
			
			//determine which to build from
			while (repeat == true){
				nextSrcInt = rand.nextInt(rCounter+hCounter);
				if (nextSrcInt < rCounter){
					if (rand.nextInt(4) < rArchive[nextSrcInt].getconnections()){
						repeat = true;
					}
					else {
						repeat = false;
						nextSrcChar = 'r';
					}
				}
				else {
					nextSrcInt -= rCounter;
					repeat = false;
					nextSrcChar = 'h';
				}
			}
			
			//make door on both sides and set xTemp, yTemp, and direction correctly.
			if (nextSrcChar=='r'){
				direction = rand.nextInt(4);
				switch(direction){
					case 0: //Heading North
						xTemp = rArchive[nextSrcInt].pickTopEdge();
						yTemp = rArchive[nextSrcInt].getY();
						genMap[xTemp][yTemp].tile.setNWall(2);
						yTemp -= 1;
						genMap[xTemp][yTemp].tile.setSWall(2);
						break;
					case 1: //Heading East
						yTemp = rArchive[nextSrcInt].pickRightEdge();
						xTemp = rArchive[nextSrcInt].getX()+rArchive[nextSrcInt].width;
						genMap[xTemp][yTemp].tile.setEWall(2);
						xTemp += 1;
						genMap[xTemp][yTemp].tile.setWWall(2);
						break;
					case 2: //Heading South
						xTemp = rArchive[nextSrcInt].pickBottomEdge();
						yTemp = rArchive[nextSrcInt].gety()+rArchive[nextSrcInt].height;
						genMap[xTemp][yTemp].tile.setSWall(2);
						yTemp += 1;	
						genMap[xTemp][yTemp].tile.setNWall(2);
						break;
					case 3: //Heading West
						yTemp = rArchive[nextSrcInt].pickLeftEdge();
						xTemp = rArchive[nextSrcInt].getX();
						genMap[xTemp][yTemp].tile.setWWall(2);
						xTemp -= 1;
						genMap[xTemp][yTemp].tile.setEWall(2);
						break;
				}
			}
			else {
				//set things off a hallway
			}
			
			//Create
			if (nextGenChar =='r'){
				
			}
			
			
		}//end of WHILE LOOP
		
		
		
	}//end of Generate
	
	
	
	
}
