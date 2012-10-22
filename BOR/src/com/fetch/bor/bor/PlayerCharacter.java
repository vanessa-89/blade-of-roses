package com.fetch.bor.bor;

import java.awt.Image;

import com.fetch.bor.gui.Game;

public class PlayerCharacter extends BORCharacter {
	
	int experience;
	int skillPointsTotal;
	int skillPointsSpent;
	
	private Game game;
	
	PlayerCharacter(){
		xPos = 3;
		yPos = 3;
		targetX = 3;
		targetY = 4;
	    level = 1;
	    experience = 0;
	    skillPointsTotal = 0;
	    skillPointsSpent = 0;
		stats.setStrength(10);
		stats.setDexterity(10);
		stats.setDamage(0);
		stats.setHit(0);
		stats.setWillpower(10);
		stats.setIntelligence(10);
		stats.setSpellDamage(0);
		stats.setDodge(0);
		stats.setParry(0);
		stats.setCounterspell(0);
		stats.setBlock(0);
		stats.setShield(0);
		stats.setArmor(0);
		stats.setResist(0);
		stats.setMaxBody(100);
		stats.setMaxSoul(100);
		stats.setBody(stats.getMaxBody());
		stats.setSoul(stats.getMaxSoul());
	}
	
	public PlayerCharacter(int x, int y, Image image) {
		xPos = x;
		yPos = y;
		targetX = 3;
		targetY = 4;
		sprite = image;
		level = 1;
		stats.setStrength(10);
		stats.setDexterity(10);
		stats.setDamage(0);
		stats.setHit(0);
		stats.setWillpower(10);
		stats.setIntelligence(10);
		stats.setSpellDamage(0);
		stats.setDodge(0);
		stats.setParry(0);
		stats.setCounterspell(0);
		stats.setBlock(0);
		stats.setShield(0);
		stats.setArmor(0);
		stats.setResist(0);
		stats.setMaxBody(100);
		stats.setMaxSoul(100);
		stats.setBody(stats.getMaxBody());
		stats.setSoul(stats.getMaxSoul());
	}

	public PlayerCharacter(Game game, String ref, int x, int y) {
		super(ref, x, y);
		this.game = game;
	}

	@Override
	public void collidedWith(BORCharacter other) {
		// TODO Auto-generated method stub
		
	}
	
}
