import java.util.Random;

public class GenerationArray {

	GenerationDataType[][] mapArray = new GenerationDataType[200][200];
	Hallway[] hallwayArchive;
	int hallwayCounter;
	Room[] roomArchive;
	int roomCounter;
	Random rand = new Random();
	
	char nextSpawnSource;
	
	
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
				//randomconnectionspot
				//hallorroom(with params)
				
				
				break;
			case 'r':
				//randomconnectionspot
				//hallorroom(with params)
				
				
				break;
			}
			
			
		}
		
		
	}
	
	
	
	void CreateRoom (int x, int y, int direction){
		roomArchive[roomCounter]=new Room();		
		roomArchive[roomCounter].FindEdge(direction);
		roomArchive[roomCounter].SetAbsolute(x,y);
		for (int i=0; i<roomArchive[roomCounter].width; i++){
			for (int j=0; j<roomArchive[roomCounter].height; j++){
				mapArray[x+i][y+j].walkable = true;
				mapArray[x+i][y+j].tile = 1;
				mapArray[x+i][y+j].structureIntIndex.push(roomCounter);
				mapArray[x+i][y+j].structureTypeIndex.push('r');
				mapArray[x+i][y+j].overlaps++;
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
			mapArray[x+i][y+j].overlaps++;
		}
		hallwayCounter++;
	}
	
	
	// MAKE BASED ON PERCENT
	char HallOrRoom () {
		char result = 0;
		float boundary;
		boundary = (hallwayCounter / 
			(hallwayCounter + roomCounter))*100;
		switch (((rand.nextInt(100)/boundary)/100)){
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
		switch (rand.nextInt(100)/boundary){
		case 1:
			CreateHallway( x, y, direction);
			break;
		case 0:
			CreateRoom( x, y, direction);
			break;
		}
	}
}
