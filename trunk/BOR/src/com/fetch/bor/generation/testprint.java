package com.fetch.bor.generation;


public class testprint {

	public static void main(String[] args){
		int size = 100;
	GenerationArray GA = new GenerationArray(size);
	GA.Generate();
	GA.FindBounds();
	GA.PrintArea();
	GA.PrintAll();
	System.out.print("Rooms: " + GA.roomCounter + "Hallways: " + GA.hallwayCounter);
	}	
}
