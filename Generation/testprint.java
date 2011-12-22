
public class testprint {

	public static void main(){
	GenerationArray GA = new GenerationArray();
	GA.Generate();
	for (int i=0; i<200; i++){
		for (int j=0; i<200; i++){
			if (GA.maparray[i][j].walkable)
				System.out.print('W');
			else
				System.out.print('U');
			}
		}
	}
}
