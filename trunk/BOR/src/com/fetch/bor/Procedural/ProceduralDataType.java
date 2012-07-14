package com.fetch.bor.Procedural;

import java.util.Stack;

import com.fetch.bor.bor.Tile;

public class ProceduralDataType {
		public Stack<Character> typeIndex;
		public Stack<Integer> intIndex;
		public Stack<Integer> dirIndex;
		public boolean connection;
		public Tile tile;
		public int overlaps;
		
		public ProceduralDataType() {
			typeIndex = new Stack<Character>();
			intIndex = new Stack<Integer>();
			dirIndex = new Stack<Integer>();
			connection = false;
			tile = new Tile();
			overlaps = 0;
		}
		
		
		
		
}
