package com.fetch.bor.generation;

public class testhall {

	
	public static void main(String[] args){
		int direction = 0;
		Hallway hall = new Hallway();
		hall.Generate(direction);
		for (int i = 0; i < hall.trueLength; i++){
			System.out.print(hall.xyTrack[0][i] + " , " + hall.xyTrack[1][i] + " , " + hall.xyTrack[2][i] + '\n');
		}
	}	
}
