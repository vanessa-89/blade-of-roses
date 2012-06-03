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
		stats.setStrength(0);
		stats.setDexterity(0);
		stats.setDamage(0);
		stats.setWillpower(0);
		stats.setIntelligence(0);
		stats.setSpellDamage(0);
		stats.setDodge(0);
		stats.setParry(0);
		stats.setCounterspell(0);
		stats.setBlock(0);
		stats.setShield(0);
		stats.setArmor(0);
		stats.setResist(0);
		stats.setMaxBody(0);
		stats.setMaxSoul(0);
		stats.setBody(stats.getMaxBody());
		stats.setSoul(stats.getMaxSoul());
	}
	
	
	
}
