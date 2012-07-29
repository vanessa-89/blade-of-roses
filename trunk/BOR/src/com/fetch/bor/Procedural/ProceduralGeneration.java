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
	public int boundsX1, boundsX2, boundsY1, boundsY2;
	public int xStart, yStart;
	
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
		boundsX1 = xSize-1;
		boundsX2 = 0;
		boundsY1 = ySize-1;
		boundsY2 = 0;
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
		xStart = rArchive[0].realX;
		yStart = rArchive[0].realY;
		while (hCounter+rCounter < 15)//some condition for ending Generation
		{
			//determine which to generate
			if ( (rand.nextDouble()*((rCounter+hCounter))) < rCounter)
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
						makeNDoor(xTemp,yTemp);
						break;
					case 1: //Heading East
						yTemp = rArchive[nextSrcInt].pickRightEdge();
						xTemp = rArchive[nextSrcInt].getX()+rArchive[nextSrcInt].getWidth();
						makeEDoor(xTemp,yTemp);
						break;
					case 2: //Heading South
						xTemp = rArchive[nextSrcInt].pickBottomEdge();
						yTemp = rArchive[nextSrcInt].getY()+rArchive[nextSrcInt].getHeight();
						makeSDoor(xTemp,yTemp);
						break;
					case 3: //Heading West
						yTemp = rArchive[nextSrcInt].pickLeftEdge();
						xTemp = rArchive[nextSrcInt].getX();
						makeWDoor(xTemp,yTemp);
						break;
				}
				rArchive[nextSrcInt].addconnection();
			}
			else {
				//set things off a hallway
				boolean inRoom = true;
				while (inRoom){
					hArchive[nextSrcInt].pickStart();
					xTemp = (hArchive[nextSrcInt].absoluteStart[0]) + hArchive[nextSrcInt].getX();
					yTemp = (hArchive[nextSrcInt].absoluteStart[1]) + hArchive[nextSrcInt].getY();
					if (!genMap[xTemp][yTemp].typeIndex.contains('r')){
						inRoom = false;
					}
				}
				System.out.print("temps of hall: " + xTemp + " , " +yTemp + " , " + genMap[xTemp][yTemp].tile.getFloor() + '\n');
//				System.out.print("dir of Hall: "+ genMap[xTemp][yTemp].tile.dirIndex.peek());
				if (genMap[xTemp][yTemp].tile.dirIndex.isEmpty()){
					System.out.print("Empty" + '\n');
				}
				direction = genMap[xTemp][yTemp].tile.dirIndex.size();
				direction = rand.nextInt(direction);
				direction = genMap[xTemp][yTemp].tile.dirIndex.get(direction);
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
		for (int i=realCornerX+1 ; i<=realCornerX+xObject-1; i++){
			for (int j=realCornerY+1 ; j<=realCornerY+yObject-1; j++){
					putFloor(i,j);
					makeEOpen(i,j);
					makeWOpen(i,j);
					makeNOpen(i,j);
					makeSOpen(i,j);
					clearCorners(i,j);
			}
		}
		for (int i=realCornerX ; i<=realCornerX+xObject; i++){
			for (int j=realCornerY ; j<=realCornerY+yObject; j++){
				putFloor(i,j);
				clearCorners(i,j);
				if (!genMap[i][j].typeIndex.contains('r')){
					genMap[i][j].typeIndex.push('r');
					genMap[i][j].intIndex.push(rCounter+hCounter);
					putFloor(i,j);
					clearCorners(i,j);
					if (j==realCornerY){
						makeEOpen(i,j);
						makeWOpen(i,j);
						makeSOpen(i,j);
						makeNWall(i,j);
						if (genMap[i][j-1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeNDoor(i,j);
						}
					}
					else
						makeNOpen(i,j);
					
					if (j==realCornerY+yObject){
						makeEOpen(i,j);
						makeWOpen(i,j);
						makeNOpen(i,j);
						makeSWall(i,j);
						if (genMap[i][j+1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeSDoor(i,j);
						}
					}
					else
						makeSOpen(i,j);
					
					if (i==realCornerX) {
						makeEOpen(i,j);
						makeNOpen(i,j);
						makeSOpen(i,j);
						makeWWall(i,j);
						if (genMap[i-1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeWDoor(i,j);
						}
					}
					else
						makeWOpen(i,j);
					
					if (i==realCornerX+xObject) {
						makeWOpen(i,j);
						makeNOpen(i,j);
						makeSOpen(i,j);
						makeEWall(i,j);
						if (genMap[i+1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeEDoor(i,j);
						}
					}
					else
						makeEOpen(i,j);
					if (j==realCornerY && i==realCornerX+xObject){
						makeWOpen(i,j);
						makeSOpen(i,j);
						makeEWall(i,j);
						makeNWall(i,j);
						if (genMap[i][j-1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeNDoor(i,j);
						}
						if (genMap[i+1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeEDoor(i,j);
						}
						else {
							genMap[i][j].tile.setNECorner(1);
						}
					}
					if (j==realCornerY && i==realCornerX){
						makeEOpen(i,j);
						makeSOpen(i,j);
						makeWWall(i,j);
						makeNWall(i,j);
						if (genMap[i][j-1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeNDoor(i,j);
						}
						if (genMap[i-1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeWDoor(i,j);
						}
						else {
							genMap[i][j].tile.setNWCorner(1);
						}
					}
					if (j==realCornerY+yObject && i==realCornerX+xObject){
						makeWOpen(i,j);
						makeNOpen(i,j);
						makeEWall(i,j);
						makeSWall(i,j);
						if (genMap[i][j+1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeSDoor(i,j);
						}
						if (genMap[i+1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeEDoor(i,j);
						}
						else {
							genMap[i][j].tile.setSECorner(1);
						}
					}
					if (j==realCornerY+yObject && i==realCornerX){
						makeNOpen(i,j);
						makeEOpen(i,j);
						makeSWall(i,j);
						makeWWall(i,j);
						if (genMap[i][j+1].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeSDoor(i,j);
						}
						if (genMap[i-1][j].typeIndex.contains('h') && genMap[i][j].typeIndex.contains('h')){
							makeWDoor(i,j);
						}
						else {
							genMap[i][j].tile.setSWCorner(1);
						}
					}
				}
				else {
					genMap[i][j].typeIndex.push('r');
					genMap[i][j].intIndex.push(rCounter+hCounter);
				}
			}
		}
		rCounter++;
		System.out.print("rCounter: " + rCounter + '\n');
	//	printMap();
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
			direction = hArchive[hCounter].xydTrack[2][i];
			if (genMap[xTemp][yTemp].typeIndex.isEmpty()){
				putFloor(xTemp,yTemp);
				makeEWall(xTemp,yTemp);
				makeWWall(xTemp,yTemp);
				makeNOpen(xTemp,yTemp);
				makeSOpen(xTemp,yTemp);
				clearCorners(xTemp,yTemp);
				genMap[xTemp][yTemp].typeIndex.push('h');
				genMap[xTemp][yTemp].intIndex.push(hCounter+rCounter);
				switch(direction){
					case 0: //Heading North
						makeNWall(xTemp,yTemp);
						makeEWall(xTemp,yTemp);
						makeWWall(xTemp,yTemp);
						makeSOpen(xTemp,yTemp);
						if (genMap[xTemp][yTemp+1].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeSDoor(xTemp,yTemp);
						}
						if (genMap[xTemp][yTemp-1].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeNDoor(xTemp,yTemp);
						}
						break;
					case 1: //Heading East
						makeNWall(xTemp,yTemp);
						makeEWall(xTemp,yTemp);
						makeSWall(xTemp,yTemp);
						makeWOpen(xTemp,yTemp);
						if (genMap[xTemp-1][yTemp].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeWDoor(xTemp,yTemp);
						}
						if (genMap[xTemp-1][yTemp].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeEDoor(xTemp,yTemp);
						}
						break;
					case 2: //Heading South
						makeSWall(xTemp,yTemp);
						makeEWall(xTemp,yTemp);
						makeWWall(xTemp,yTemp);
						makeNOpen(xTemp,yTemp);
						if (genMap[xTemp][yTemp-1].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeNDoor(xTemp,yTemp);
						}
						if (genMap[xTemp][yTemp+1].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeSDoor(xTemp,yTemp);
						}
						break;
					case 3: //Heading West
						makeNWall(xTemp,yTemp);
						makeSWall(xTemp,yTemp);
						makeWWall(xTemp,yTemp);
						makeEOpen(xTemp,yTemp);
						if (genMap[xTemp+1][yTemp].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeEDoor(xTemp,yTemp);
						}
						if (genMap[xTemp-1][yTemp].typeIndex.contains('r') && !genMap[xTemp][yTemp].typeIndex.contains('r')){
							makeWDoor(xTemp,yTemp);
						}
						break;
				}
			}//end of if (!genMap[xTemp][yTemp].typeIndex.contains('r'))
			else if (genMap[xTemp][yTemp].typeIndex.contains('h')){
				genMap[xTemp][yTemp].intIndex.push(hCounter+rCounter);
				genMap[xTemp][yTemp].typeIndex.push('h');
				switch(direction){
				case 0: //Heading North
					makeNWall(xTemp,yTemp);
					makeSOpen(xTemp,yTemp);
					break;
				case 1: //Heading East
					makeEWall(xTemp,yTemp);
					makeWOpen(xTemp,yTemp);
					break;
				case 2: //Heading South
					makeSWall(xTemp,yTemp);
					makeNOpen(xTemp,yTemp);
					break;
				case 3: //Heading West
					makeWWall(xTemp,yTemp);
					makeEOpen(xTemp,yTemp);
					break;
				}
			}
			else {
				genMap[xTemp][yTemp].intIndex.push(hCounter+rCounter);
				genMap[xTemp][yTemp].typeIndex.push('h');
			}
		}
		hCounter++;
		System.out.print("hCounter: " + hCounter + '\n');
	//	printMap();
	}
	
	public Tile[][] convertOut(){
		findBounds();
		Tile[][] tileOut= new Tile[(boundsX2-boundsX1)+2][(boundsY2-boundsY1)+2];
		for (int i = 0; i<(boundsX2-boundsX1)+2; i++){
			for (int j = 0; j<(boundsY2-boundsY1)+2; j++){
				tileOut[i][j] = genMap[boundsX1+i+1][boundsY1+j+1].tile;
				tileOut[i][j].structIndex = genMap[boundsX1+i+1][boundsY1+j+1].intIndex;
				if (tileOut[i][j] == null){
					tileOut[i][j] = new Tile();
				}
			}
		}
		return tileOut;
	}
	
	public void findBounds(){
		boundsX1 = xSize-1;
		boundsX2 = 0;
		boundsY1 = ySize-1;
		boundsY2 = 0;
		for (int j = 0; j < ySize; j++){
			for (int i = 0; i<xSize; i++){
				if (genMap[i][j].tile.getFloor()==1){
					if (i<=boundsX1){
						boundsX1 = i;
					}
					if (i>=boundsX2){
						boundsX2 = i;
					}
					if (j<=boundsY1){
						boundsY1 = j;
					}
					if (j>=boundsY2){
						boundsY2 = j;
					}
				}
			}
		}
	}

	public void printMap(){
		findBounds();
		System.out.print("Bounds: " + boundsX1 + " , " + boundsX2 + " , " + boundsY1 + " , " + boundsY2 + '\n');
		for (int i = boundsY1; i<boundsY2; i++){
			for (int j = boundsX1; j<boundsX2; j++){
				System.out.print( genMap[j][i].tile.getFloor() + " ");
			}
			System.out.print( '\n' );
		}
		for (int i = 0; i<ySize; i++){
			for (int j = 0; j<xSize; j++){
				System.out.print( genMap[j][i].tile.getFloor() + " ");
			}
			System.out.print( '\n' );
		}
	}
	public void makeNWall(int x, int y){
		genMap[x][y].tile.setNWall(1);
		genMap[x][y-1].tile.setSWall(1);
	}
	public void makeNDoor(int x, int y){
		genMap[x][y].tile.setNWall(2);
		genMap[x][y-1].tile.setSWall(2);
	}
	public void makeNOpen(int x, int y){
		genMap[x][y].tile.setNWall(0);
		genMap[x][y-1].tile.setSWall(0);
	}
	public void makeSWall(int x, int y){
		genMap[x][y].tile.setSWall(1);
		genMap[x][y+1].tile.setNWall(1);
	}
	public void makeSDoor(int x, int y){
		genMap[x][y].tile.setSWall(2);
		genMap[x][y+1].tile.setNWall(2);
	}
	public void makeSOpen(int x, int y){
		genMap[x][y].tile.setSWall(0);
		genMap[x][y+1].tile.setNWall(0);
	}
	public void makeEWall(int x, int y){
		genMap[x][y].tile.setEWall(1);
		genMap[x+1][y].tile.setWWall(1);
	}
	public void makeEDoor(int x, int y){
		genMap[x][y].tile.setEWall(2);
		genMap[x+1][y].tile.setWWall(2);
	}
	public void makeEOpen(int x, int y){
		genMap[x][y].tile.setEWall(0);
		genMap[x+1][y].tile.setWWall(0);
	}
	public void makeWWall(int x, int y){
		genMap[x][y].tile.setWWall(1);
		genMap[x-1][y].tile.setEWall(1);
	}
	public void makeWDoor(int x, int y){
		genMap[x][y].tile.setWWall(2);
		genMap[x-1][y].tile.setEWall(2);
	}
	public void makeWOpen(int x, int y){
		genMap[x][y].tile.setWWall(0);
		genMap[x-1][y].tile.setEWall(0);
	}
	public void clearCorners(int x, int y){
		genMap[x][y].tile.setNWCorner(0);
		genMap[x][y].tile.setNECorner(0);
		genMap[x][y].tile.setSECorner(0);
		genMap[x][y].tile.setSWCorner(0);
	}
	public void putFloor(int x, int y){
		genMap[x][y].tile.setFloor(1);
	}
}
