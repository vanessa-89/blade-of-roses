import java.util.Random;

public class GenerationArray {

	GenerationDataType[][] mapArray = new GenerationDataType[200][200];
	Hallway[] hallwayArchive;
	int hallwayCounter;
	Room[] roomArchive;
	int roomCounter;
	Random rand = new Random();
	
	char nextSpawnSource;
	int nextSpawnNum;
	int nextConnection;
	int nextDirection;
	int tempX;
	int tempY;
	
	
	GenerationArray() {
		hallwayCounter = 0;
		roomCounter = 0;
		for (int i=0; i<200; i++) {
			for (int j=0; j<200; j++) {
				mapArray[i][j].connection = false;
				mapArray[i][j].walkable = false;
				mapArray[i][j].tile = 0;
				mapArray[i][j].overlaps = 0;
			}
		}
	}
	
	void Generate () {
		CreateRoom (100,100,4);
		while (roomCounter*4+hallwayCounter*4 < 200) {
			nextSpawnSource = HallOrRoom();
			switch (nextSpawnSource){
			case 'h':
				nextSpawnNum = rand.nextInt(hallwayCounter);
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
				nextConnection = (rand.nextInt(roomArchive[nextSpawnNum].width*2) +
												roomArchive[nextSpawnNum].height*2);
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
		roomArchive[roomCounter]=new Room();		
//		roomArchive[roomCounter].FindEdge(direction);
		roomArchive[roomCounter].SetAbsolute(x,y);
		tempX = roomArchive[roomCounter].connection[0][roomArchive[roomCounter].connectionCounter];
		tempY = roomArchive[roomCounter].connection[1][roomArchive[roomCounter].connectionCounter];
		for (int i=0; i<roomArchive[roomCounter].width; i++){
			for (int j=0; j<roomArchive[roomCounter].height; j++){
				mapArray[x+i-tempX][y+j-tempY].walkable = true;
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
		hallwayArchive[hallwayCounter]= new Hallway();
		hallwayArchive[hallwayCounter].Generate(direction);
		hallwayArchive[hallwayCounter].SetAbsolute(x, y);
		for (int i=0; i<hallwayArchive[hallwayCounter].trueLength; i++){
			relativeX = x+hallwayArchive[hallwayCounter].xyTrack[0][i];
			relativeY = y+hallwayArchive[hallwayCounter].xyTrack[1][i];
			mapArray[relativeX][relativeY].walkable = true;
			mapArray[relativeX][relativeY].tile=1;
			mapArray[relativeX][relativeY].structureIntIndex.push(hallwayCounter);
			mapArray[relativeX][relativeY].structureTypeIndex.push('h');
			mapArray[relativeX][relativeY].overlaps++;
		}
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
		return result;
	}
	
	void HallOrRoom ( int x, int y, int direction) {
		int boundary;
		boundary = hallwayCounter / 
			(hallwayCounter + roomCounter);
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
		switch (rand.nextInt(100)/boundary){
		case 1:
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
			CreateHallway( x, y, direction);
			break;
		case 0:
			
			CreateRoom( x, y, direction);
			break;
		}
	}
}
