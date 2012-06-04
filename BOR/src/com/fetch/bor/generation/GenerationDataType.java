package com.fetch.bor.generation;

import java.util.*;

import com.fetch.bor.bor.Tile;

public class GenerationDataType {
	public Stack<Character> structureTypeIndex;
	public Stack<Integer> structureIntIndex;
	public boolean connection;
	public Tile tile;
	public int overlaps;
	
	public GenerationDataType() {
		structureTypeIndex = new Stack<Character>();
		structureIntIndex = new Stack<Integer>();
		connection = false;
		tile = new Tile();
		overlaps = 0;
	}

}