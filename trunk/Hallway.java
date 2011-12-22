import java.util.Random;

public class Hallway {

	static final int MAXLENGTH= 50;
	int [][] xyTrack = new int[2][MAXLENGTH];
	int[] connection = new int[10]; // "doorways"
	int connectionCounter;
	int[] absoluteStart = new int[2];
	int trueLength;
	Random rand = new Random();
	
	Hallway(){
		connectionCounter = 1;
		for (int i=0; i<50; i++) {
			xyTrack[0][i] = 0;
			xyTrack[1][i] = 0;
		}
		for (int i=0; i<10; i++) {
			connection[i] = 0;
		}
	}
	
	void Generate(int initial) {
		int direction = initial;
		int previous;
		for (int i=1; i<MAXLENGTH; i++) {
			previous = direction;
			int length = rand.nextInt(15)+1;
			for (int j=0; j<length; j++){
				switch (direction) {
				case 0: //up
					xyTrack[0][i+j]=xyTrack[0][i+j-1];
					xyTrack[1][i+j]=xyTrack[1][i+j-1]+1;
					break;
				case 1: //right
					xyTrack[0][i+j]=xyTrack[0][i+j-1]+1;
					xyTrack[1][i+j]=xyTrack[1][i+j-1];
					break;
				case 2: //down
					xyTrack[0][i+j]=xyTrack[0][i+j-1];
					xyTrack[1][i+j]=xyTrack[1][i+j-1]-1;
					break;
				case 3: //left
					xyTrack[0][i+j]=xyTrack[0][i+j-1]-1;
					xyTrack[1][i+j]=xyTrack[1][i+j-1];
					break;
				}	
			}
			while (direction == previous){
				direction = rand.nextInt(4);
			}
			i = i+length;
			trueLength = i;
			if ( rand.nextInt(MAXLENGTH-i) < MAXLENGTH/5 ) {
				i = MAXLENGTH;
			}	
		}
	}
	
	void SetAbsolute(int x, int y) {
		absoluteStart[0] = x;
		absoluteStart[1] = y;
	}
	
	void SetConnection(int x, int y) {
		x = x - absoluteStart[0];
		y = y - absoluteStart[1];
		for (int i=0; i<trueLength; i++) {
			if (xyTrack[0][i]==x && xyTrack[1][i]==y)
				connection[connectionCounter]= i;
		}
	}
	

	
}
	
	

