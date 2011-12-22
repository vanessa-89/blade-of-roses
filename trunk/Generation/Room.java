import java.util.Random;


public class Room {
	int height;
	int width;
	int[][] connection = new int[2][10]; // "doorways"
	int connectionCounter;
	int[] absoluteStart = new int[2];
	Random rand = new Random();
	
	Room(){ //height and width are 3-9 
		height = rand.nextInt(7)+3;
		width = rand.nextInt(7)+3;
		for (int i=0; i<10; i++) {
			connection[0][i] = 0;
			connection[1][i] = 0;
		}
		connectionCounter = 0;
//		connectionCounter = 1;
//		connection[0][0] = x;
//		connection[1][0] = y;
		
	}
	// FindEdge takes an int based on what side it is attaching to
	// starting on the left side of room, going clockwise or
	// starting on the right side of current, going counterclockwise
	void FindEdge(int direction){
		switch (direction) {
		case 3: //door on left side, attach to right side of current
			connection[0][connectionCounter] = 0;
			connection[1][connectionCounter] = rand.nextInt(height);
			break;
		case 2: //door on top side, attach to bottom side of current
			connection[0][connectionCounter] = rand.nextInt(width);
			connection[1][connectionCounter] = 0;
			break;
		case 1: //door on right side, attach to left side of current
			connection[0][connectionCounter] = width-1;
			connection[1][connectionCounter] = rand.nextInt(height);
			break;
		case 0: //door on bottom side, attach to top side of current
			connection[0][connectionCounter] = rand.nextInt(width);
			connection[1][connectionCounter] = height-1;
			break;
		}	
		if (direction < 4){
			connectionCounter++;
		}
	}
	
	void SetAbsolute(int x, int y) {
		absoluteStart[0] = x;
		absoluteStart[1] = y;
	}
	
	void SetConnection(int x, int y) {
		connection[0][connectionCounter]=x-absoluteStart[0];
		connection[1][connectionCounter]=y-absoluteStart[1];
	}
	

}
