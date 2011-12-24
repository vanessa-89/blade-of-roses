import java.util.Random;

public class GenerationArray {

	GenerationDataType[][] mapArray;
	Hallway[] hallwayArchive;
	int hallwayCounter;
	Room[] roomArchive;
	int roomCounter;
	Random rand = new Random();
	
	int MAXSIZE;
	char nextSpawnSource;
	int nextSpawnNum;
	int nextConnection;
	int nextDirection;
	int tempX;
	int tempY;
	GenerationDataType gdt;
	Room tempRoom;
	Hallway tempHallway;
	
	public GenerationArray(int size) {
		MAXSIZE=size;
		mapArray = new GenerationDataType[MAXSIZE][MAXSIZE];
		hallwayCounter = 0;
		roomCounter = 0;
		for (int i=0; i<MAXSIZE; i++) {
			for (int j=0; j<MAXSIZE; j++) {
				gdt = new GenerationDataType();
				mapArray[i][j] = gdt;
			}
		}
	}
	
	void Generate () {
		hallwayArchive = new Hallway[100];
		roomArchive = new Room[100];
		CreateRoom (MAXSIZE/2,MAXSIZE/2,rand.nextInt(4));
		while (roomCounter*5+hallwayCounter*3 < 100) {
			nextSpawnSource = HallOrRoom();
			switch (nextSpawnSource){
			case 'h':
				nextSpawnNum = ((hallwayCounter<2) ? 0:(rand.nextInt(hallwayCounter-1)));
				
				nextConnection = rand.nextInt(hallwayArchive[nextSpawnNum].trueLength);
				
				hallwayArchive[nextSpawnNum].SetConnection(nextConnection);
				
				nextDirection = (hallwayArchive[nextSpawnNum].xyTrack[2][nextConnection]
									+ ((rand.nextInt()%2)==1 ? 1:-1)) % 4;
				
				tempX = hallwayArchive[nextSpawnNum].xyTrack[0][nextConnection] 
						+ hallwayArchive[nextSpawnNum].absoluteStart[0];
				
				tempY = hallwayArchive[nextSpawnNum].xyTrack[1][nextConnection] 
						+ hallwayArchive[nextSpawnNum].absoluteStart[1];
				break;
			
			case 'r':
				nextSpawnNum = rand.nextInt(roomCounter);
//				System.out.println("Next Spawn Num: " + nextSpawnNum);
				nextConnection = (rand.nextInt(roomArchive[nextSpawnNum].width*2 +
												roomArchive[nextSpawnNum].height*2));
//				System.out.println("Next Connection: " + nextConnection);
//				System.out.println("Width and Height: " + roomArchive[nextSpawnNum].width + "  " + roomArchive[nextSpawnNum].height);
				
				if (nextConnection < roomArchive[nextSpawnNum].width)
					nextDirection = 0;
				
				else if (nextConnection < roomArchive[nextSpawnNum].width+
											roomArchive[nextSpawnNum].height)
					nextDirection = 1;
				
				else if (nextConnection < roomArchive[nextSpawnNum].width*2+
											roomArchive[nextSpawnNum].height)
					nextDirection = 2;
				
				else 
					nextDirection = 3;
				
				tempX = nextConnection - 
						((nextDirection/2)*roomArchive[nextSpawnNum].width) - 
						(((nextDirection+3)/2)-1)*roomArchive[nextSpawnNum].height;
				tempY = nextConnection - 
						(((nextDirection+2)/2)*roomArchive[nextSpawnNum].width) - 
						((nextDirection/2)*roomArchive[nextSpawnNum].height);
				roomArchive[nextSpawnNum].SetConnection( tempX, tempY);
				tempX = tempX + roomArchive[nextSpawnNum].absoluteStart[0];
				tempY = tempY + roomArchive[nextSpawnNum].absoluteStart[1];
				break;
			}
			HallOrRoom( tempX, tempY, nextDirection);		
		}
	}
	
	
	
	void CreateRoom (int x, int y, int direction){
		tempRoom = new Room();
		roomArchive[roomCounter] = tempRoom;
//		System.out.println("direction: " + direction);
		roomArchive[roomCounter].FindEdge(direction);
		roomArchive[roomCounter].SetAbsolute(x,y);
		tempX = roomArchive[roomCounter].connection[0][0];
		tempY = roomArchive[roomCounter].connection[1][0];
		for (int i=0; i<roomArchive[roomCounter].width; i++){
			for (int j=0; j<roomArchive[roomCounter].height; j++){
//				System.out.println("x " + x + " y " + y + " i " + i + " j " + j + " tempX " + tempX + " tempY " + tempY );
				mapArray[x+i-tempX][y+j-tempY].tile = 1;
				mapArray[x+i-tempX][y+j-tempY].structureIntIndex.push(roomCounter);
				mapArray[x+i-tempX][y+j-tempY].structureTypeIndex.push('r');
				mapArray[x+i-tempX][y+j-tempY].overlaps++;
			}
		}
		roomCounter++;
	}
	
	void CreateHallway(int x, int y, int direction){
		int relativeX;
		int relativeY;
		tempHallway = new Hallway();
		hallwayArchive[hallwayCounter]= tempHallway;
		hallwayArchive[hallwayCounter].Generate(direction);
		hallwayArchive[hallwayCounter].SetAbsolute(x, y);
		hallwayArchive[hallwayCounter].SetConnection(hallwayArchive[hallwayCounter].trueLength);
		for (int i=0; i<hallwayArchive[hallwayCounter].trueLength; i++){
			relativeX = x+hallwayArchive[hallwayCounter].xyTrack[0][i];
			relativeY = y+hallwayArchive[hallwayCounter].xyTrack[1][i];
			mapArray[relativeX][relativeY].walkable = true;
			mapArray[relativeX][relativeY].tile=1;
			mapArray[relativeX][relativeY].structureIntIndex.push(hallwayCounter);
			mapArray[relativeX][relativeY].structureTypeIndex.push('h');
			mapArray[relativeX][relativeY].overlaps++;
		}
		CreateRoom( hallwayArchive[hallwayCounter].xyTrack[0][hallwayArchive[hallwayCounter].trueLength]+x,
					hallwayArchive[hallwayCounter].xyTrack[1][hallwayArchive[hallwayCounter].trueLength]+y,
					(Math.abs((int)(hallwayArchive[hallwayCounter].xyTrack[2][hallwayArchive[hallwayCounter].trueLength] + ((((rand.nextInt(2)+1)*6)/3)-3)%4))));
		hallwayCounter++;
	}
	
	
	char HallOrRoom () {
		char result = 0; 
		switch (rand.nextInt(2)){
		case 0:
			result = 'r';
			break;
		case 1:
			result = 'h';
			break;
		}
		if (hallwayCounter == 0)
			result = 'r';
		return result;
	}
	
	void HallOrRoom ( int x, int y, int direction) {
		switch (direction){
		case 0:
			y++;
			break;
		case 1:
			x++;
			break;
		case 2:
			y--;
			break;
		case 3:
			x--;
			break;
		}
		int boundary;
		boundary = (hallwayCounter/3) / 
			(hallwayCounter + roomCounter);
		
		switch (((boundary==0) ? rand.nextInt(2):rand.nextInt(100)/boundary)){
		case 1:
			CreateHallway( x, y, direction);
			break;
		case 0:
			
			CreateRoom( x, y, direction);
			break;
		}
	}
}
