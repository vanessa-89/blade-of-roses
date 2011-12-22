
public class testprint {

	public static void main(String[] args){
	GenerationArray GA = new GenerationArray();
	GA.Generate();
	for (int i=0; i<200; i++){
		for (int j=0; j<200; j++){
			if (GA.mapArray[i][j].walkable)
				System.out.print('W');
			else
				System.out.print('U');
			}
		}
	}
}
