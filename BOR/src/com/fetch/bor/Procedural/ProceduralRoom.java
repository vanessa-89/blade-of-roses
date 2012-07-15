package com.fetch.bor.Procedural;

import java.util.Random;
//import java.util.Stack;

public class ProceduralRoom{

	
	int width, height;
	int realX, realY;
	Random rand = new Random();
	int connections;
	
	public ProceduralRoom(){
		connections=0;
		width = (rand.nextInt(5)+1);
		height = (rand.nextInt(5)+1);
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}	
	public void pushX(int newX){
		realX = newX;
	}
	public int getX(){
		return realX;
	}
	public void pushY(int newY){
		realY = newY;
	}
	public int getY(){
		return realY;
	}

	public int pickTopEdge(){
		return (realX + (rand.nextInt(width)));
	}

	public int pickBottomEdge(){
		return (realX + (rand.nextInt(width)));
	}

	public int pickLeftEdge(){
		return (realY + (rand.nextInt(height)));
	}

	public int pickRightEdge(){
		return (realY + (rand.nextInt(height)));
	}
	public int getconnections(){
		return connections;
	}
	public void addconnection(){
		connections++;
	}
	
	
	
	
}
