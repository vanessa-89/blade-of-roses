
public class testprint {

	public static void main(String[] args){
		int size = 300;
	GenerationArray GA = new GenerationArray(size);
	GA.Generate();
	for (int i=0; i<size; i++){
		for (int j=0; j<size; j++){
			if (GA.mapArray[i][j].walkable)
				System.out.print('W');
			else
				System.out.print('U');
			}
		System.out.println();
		}
	System.out.print("Rooms: " + GA.roomCounter + "Hallways: " + GA.hallwayCounter);
	}	
}
