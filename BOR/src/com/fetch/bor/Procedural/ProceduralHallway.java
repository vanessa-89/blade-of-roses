package com.fetch.bor.Procedural;

import java.util.Random;

public class ProceduralHallway{
	
	int index;
	int size;
	static final int MAXLENGTH= 50;
	int [][] xydTrack = new int[3][MAXLENGTH*2];
	int[] connection = new int[20]; // "doorways"
	int connectionCounter;
	int[] absoluteStart = new int[2];
	int trueLength;
	Random rand = new Random();
	int start;

	
	
	public ProceduralHallway(int initial){
		for (int i=0; i<50; i++) {
			xydTrack[0][i] = 0;
			xydTrack[1][i] = 0;
		}
		int direction = initial;
		int previous;
		for (int i=1; i<MAXLENGTH; i++) {
			previous = direction;
			int length = rand.nextInt(7)+rand.nextInt(7)+rand.nextInt(7)+1;
			for (int j=0; j<length; j++){
				switch (direction) {
				case 0: //up
					xydTrack[0][i+j]=xydTrack[0][i+j-1];
					xydTrack[1][i+j]=xydTrack[1][i+j-1]+1;
					xydTrack[2][i+j]=direction;
					break;
				case 1: //right
					xydTrack[0][i+j]=xydTrack[0][i+j-1]+1;
					xydTrack[1][i+j]=xydTrack[1][i+j-1];
					xydTrack[2][i+j]=direction;
					break;
				case 2: //down
					xydTrack[0][i+j]=xydTrack[0][i+j-1];
					xydTrack[1][i+j]=xydTrack[1][i+j-1]-1;
					xydTrack[2][i+j]=direction;
					break;
				case 3: //left
					xydTrack[0][i+j]=xydTrack[0][i+j-1]-1;
					xydTrack[1][i+j]=xydTrack[1][i+j-1];
					xydTrack[2][i+j]=direction;
					break;
				}	
			}
			while (direction == previous){
				direction = rand.nextInt(4);
			}
			i = i+length-1;
			trueLength = i;
			if ( i >= MAXLENGTH )
				i = MAXLENGTH - 1;
			if ( rand.nextInt(MAXLENGTH-i) < MAXLENGTH/5 ) {
				i = MAXLENGTH;
			}	
		}
		
		
	}

	public void pickStart(){
		start = rand.nextInt(trueLength);
	}
	public int getDirection(){
		return (xydTrack[2][start]-1);
	}
	public int getX(){
		return xydTrack[0][start];
	}
	public int getY(){
		return xydTrack[1][start];
	}
	public int getLength(){
		return trueLength;
	}
	
}
