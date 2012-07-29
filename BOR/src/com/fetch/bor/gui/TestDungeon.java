package com.fetch.bor.gui;

import com.fetch.bor.bor.BORCharacter;

/**
 * 
 * @author MattFMorris
 *
 */
public class TestDungeon {
	int[][] dungeon;
	BORCharacter pc;
	public TestDungeon() {
		dungeon = new int[][]{
						{12,25,25,25,25,13},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{15,27,27,27,27,14}
					};
		pc = new BORCharacter();
	}
	
	public int[][] getDungeon() {
		return dungeon;
	}
	
	public BORCharacter getPC() {
		return pc;
	}
}
