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
	int realCornerX, realCornerY;
	
	
	ProceduralHallway[] hArchive;
	int hCounter;
	ProceduralRoom[] rArchive;
	int rCounter;
	
	boolean repeat;
	Random rand = new Random();
	
	
	public ProceduralGeneration ( int x, int y ) {
		rArchive =  new ProceduralRoom[20];
		hArchive =  new ProceduralHallway[20];
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
		xDraw = ((xSize-1)/2)-(xObject/2);
		yDraw = ((ySize-1)/2)-(yObject/2);
		direction = 0;
		generateRoom( xDraw, yDraw );
		
		while (hCounter+rCounter < 20)//some condition for ending Generation
		{
			//determine which to generate
			if ( (rand.nextDouble()*rCounter+hCounter) < rCounter)
				nextGenChar = 'h';
			else
				nextGenChar = 'r';
			
	
			//determine which to build from
			repeat = true;
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
						xTemp = rArchive[nextSrcInt].getX()+rArchive[nextSrcInt].getWidth();
						genMap[xTemp][yTemp].tile.setEWall(2);
						xTemp += 1;
						genMap[xTemp][yTemp].tile.setWWall(2);
						break;
					case 2: //Heading South
						xTemp = rArchive[nextSrcInt].pickBottomEdge();
						yTemp = rArchive[nextSrcInt].getY()+rArchive[nextSrcInt].getHeight();
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
				rArchive[nextSrcInt].addconnection();
			}
			else {
				//set things off a hallway
				hArchive[nextSrcInt].pickStart();
				direction = hArchive[nextSrcInt].getDirection();
				xTemp = (hArchive[nextSrcInt].absoluteStart[0]) + hArchive[nextSrcInt].getX();
				yTemp = (hArchive[nextSrcInt].absoluteStart[1]) + hArchive[nextSrcInt].getY();
				System.out.print("temps of hall: " + xTemp + " , " +yTemp + '\n');
				switch(direction){
					case 0: //Heading North
						genMap[xTemp][yTemp].tile.setNWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(0);
						yTemp -= 1;
						genMap[xTemp][yTemp].tile.setSWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(2);
						break;
					case 1: //Heading East
						genMap[xTemp][yTemp].tile.setEWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(1);
						xTemp += 1;
						genMap[xTemp][yTemp].tile.setWWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(3);
						break;
					case 2: //Heading South
						genMap[xTemp][yTemp].tile.setSWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(2);
						yTemp += 1;	
						genMap[xTemp][yTemp].tile.setNWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(0);
						break;
					case 3: //Heading West
						genMap[xTemp][yTemp].tile.setWWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(3);
						xTemp -= 1;
						genMap[xTemp][yTemp].tile.setEWall(2);
						genMap[xTemp][yTemp].tile.dirIndex.removeElement(1);
						break;
				}
			}
			
			//Create
			if (nextGenChar =='r'){
				generateRoom ( xTemp, yTemp);
			}
			else if (nextGenChar == 'h'){
				generateHallway (xTemp, yTemp);
			}
			
		}//end of WHILE LOOP
		
/*		for (int i = 0; i < xSize; i++){
			for (int j = 0 ; j < ySize; j++){
				if
			}
		}
*/		
	}//end of Generate	
	
	public void generateRoom ( int startX, int startY ){
		rArchive[rCounter] = new ProceduralRoom();
		xObject = rArchive[rCounter].getWidth();
		yObject = rArchive[rCounter].getHeight();
		switch(direction){
		case 0: //Heading North
			realCornerX = startX-rand.nextInt(xObject);
			realCornerY = startY-yObject;
			break;
		case 1: //Heading East
			realCornerX = startX;
			realCornerY = startY-rand.nextInt(yObject);
			break;
		case 2: //Heading South
			realCornerX = startX-rand.nextInt(xObject);
			realCornerY = startY;
			break;
		case 3: //Heading West
			realCornerX = startX-xObject;
			realCornerY = startY-rand.nextInt(yObject);
			break;
		}
		System.out.print("real of Room: " + realCornerX + " , " +realCornerY + '\n');
		rArchive[rCounter].pushX(realCornerX);
		rArchive[rCounter].pushY(realCornerY);
		for (int i=realCornerX ; i<realCornerX+xObject; i++){
			for (int j=realCornerY ; j<realCornerY+yObject; j++){
				genMap[i][j].tile.setFloor(1);
				genMap[i][j].tile.setEWall(0);
				genMap[i][j].tile.setWWall(0);
				genMap[i][j].tile.setNWall(0);
				genMap[i][j].tile.setSWall(0);
				genMap[i][j].tile.setNWCorner(0);
				genMap[i][j].tile.setNECorner(0);
				genMap[i][j].tile.setSECorner(0);
				genMap[i][j].tile.setSWCorner(0);
				genMap[i][j].tile.dirIndex.clear();
				if (j==realCornerY){
					genMap[i][j].tile.dirIndex.push(0);
					genMap[i][j].tile.setNWallSafe(1);
				}
				else
					genMap[i][j].tile.setNWall(0);
				
				if (j==realCornerY+yObject){
					genMap[i][j].tile.dirIndex.push(2);
					genMap[i][j].tile.setSWallSafe(1);
				}
				else
					genMap[i][j].tile.setSWall(0);
				
				if (i==realCornerX) {
					genMap[i][j].tile.dirIndex.push(3);
					genMap[i][j].tile.setWWallSafe(1);
				}
				else
					genMap[i][j].tile.setWWall(0);
				
				if (i==realCornerX+xObject) {
					genMap[i][j].tile.dirIndex.push(1);
					genMap[i][j].tile.setEWallSafe(1);
				}
				else
					genMap[i][j].tile.setEWall(0);
					
				genMap[i][j].typeIndex.push('r');
				genMap[i][j].intIndex.push(rCounter);
			}
		}
		rCounter++;
		System.out.print("rCounter: " + rCounter + '\n');
	}
	
	public void generateHallway ( int startX, int startY) {
		hArchive[hCounter] = new ProceduralHallway(direction);
		xObject = hArchive[hCounter].getLength();
		realCornerX = startX;
		realCornerY = startY;
		hArchive[hCounter].absoluteStart[0]=startX;
		hArchive[hCounter].absoluteStart[1]=startY;
		System.out.print("real of Hall: " + realCornerX + " , " +realCornerY + '\n');
		xTemp = startX;
		yTemp = startY;
		for (int i = 0; i < xObject ; i++){
			xTemp = startX + hArchive[hCounter].xydTrack[0][i];
			yTemp = startY + hArchive[hCounter].xydTrack[1][i];
			genMap[xTemp][yTemp].tile.setFloor(1);
			genMap[xTemp][yTemp].tile.setEWall(0);
			genMap[xTemp][yTemp].tile.setWWall(0);
			genMap[xTemp][yTemp].tile.setNWall(0);
			genMap[xTemp][yTemp].tile.setSWall(0);
			genMap[xTemp][yTemp].tile.setNWCorner(0);
			genMap[xTemp][yTemp].tile.setNECorner(0);
			genMap[xTemp][yTemp].tile.setSECorner(0);
			genMap[xTemp][yTemp].tile.setSWCorner(0);
			genMap[xTemp][yTemp].tile.dirIndex.clear();
			genMap[xTemp][yTemp].typeIndex.push('h');
			genMap[xTemp][yTemp].intIndex.push(hCounter);
			direction = hArchive[hCounter].xydTrack[2][i];
			switch(direction){
				case 0: //Heading North
					genMap[xTemp][yTemp].tile.setNWall(1);
					genMap[xTemp][yTemp].tile.setEWall(1);
					genMap[xTemp][yTemp].tile.setWWall(1);
					genMap[xTemp][yTemp].tile.setSWCorner(1);
					genMap[xTemp][yTemp].tile.setSECorner(1);
					genMap[xTemp][yTemp].tile.dirIndex.push(0);
					genMap[xTemp][yTemp].tile.dirIndex.push(3);
					genMap[xTemp][yTemp].tile.dirIndex.push(1);
					yTemp += 1;
					genMap[xTemp][yTemp].tile.setNWall(0);
					genMap[xTemp][yTemp].tile.dirIndex.removeElement(0);
					break;
				case 1: //Heading East
					genMap[xTemp][yTemp].tile.setEWall(1);
					genMap[xTemp][yTemp].tile.setNWall(1);
					genMap[xTemp][yTemp].tile.setSWall(1);
					genMap[xTemp][yTemp].tile.setSWCorner(1);
					genMap[xTemp][yTemp].tile.setNWCorner(1);
					genMap[xTemp][yTemp].tile.dirIndex.push(0);
					genMap[xTemp][yTemp].tile.dirIndex.push(2);
					genMap[xTemp][yTemp].tile.dirIndex.push(1);
					xTemp -= 1;
					genMap[xTemp][yTemp].tile.setEWall(0);
					genMap[xTemp][yTemp].tile.dirIndex.removeElement(0);
					break;
				case 2: //Heading South
					genMap[xTemp][yTemp].tile.setSWall(1);
					genMap[xTemp][yTemp].tile.setEWall(1);
					genMap[xTemp][yTemp].tile.setWWall(1);
					genMap[xTemp][yTemp].tile.setNECorner(1);
					genMap[xTemp][yTemp].tile.setNWCorner(1);
					genMap[xTemp][yTemp].tile.dirIndex.push(3);
					genMap[xTemp][yTemp].tile.dirIndex.push(2);
					genMap[xTemp][yTemp].tile.dirIndex.push(1);
					yTemp += 1;	
					genMap[xTemp][yTemp].tile.setNWall(0);
					genMap[xTemp][yTemp].tile.dirIndex.removeElement(2);
					break;
				case 3: //Heading West
					genMap[xTemp][yTemp].tile.setWWall(1);
					genMap[xTemp][yTemp].tile.setNWall(1);
					genMap[xTemp][yTemp].tile.setSWall(1);
					genMap[xTemp][yTemp].tile.setNECorner(1);
					genMap[xTemp][yTemp].tile.setSECorner(1);
					genMap[xTemp][yTemp].tile.dirIndex.push(3);
					genMap[xTemp][yTemp].tile.dirIndex.push(0);
					genMap[xTemp][yTemp].tile.dirIndex.push(1);
					xTemp -= 1;
					genMap[xTemp][yTemp].tile.setEWall(0);
					genMap[xTemp][yTemp].tile.dirIndex.removeElement(3);
					break;
			}
			
		}
		hCounter++;
		System.out.print("hCounter: " + hCounter + '\n');
	}
	
	public Tile[][] convertOut(){
		Tile[][] tileOut= new Tile[xSize][ySize];
		for (int i = 0; i<xSize; i++){
			for (int j = 0; j<ySize; j++){
				tileOut[i][j] = genMap[i][j].tile;
			}
		}
		return tileOut;
	}

}
