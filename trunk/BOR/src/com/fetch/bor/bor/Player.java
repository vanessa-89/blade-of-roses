package com.fetch.bor.bor;


public class Player {

	
	int level;
	int experience;
	MasterStatArray stats = new MasterStatArray();
	int[] location = new int[2];
	int abilityPoints;
	
	Player () {
		level=1;
		experience = 0;
		abilityPoints = 0;
		stats.setArmor(0);
		stats.setBlock(0);
		stats.setDamage(0);
		stats.setDexterity(0);
		stats.setDodge(0);
		stats.setIntelligence(0);
		stats.setMaxHP(0);
		stats.setMaxMP(0);
		stats.setHealth(stats.getMaxHP());
		stats.setMana(stats.getMaxMP());
	}
	
	
	
}
