package com.fetch.bor.bor;

import java.awt.Graphics2D;
import java.awt.Image;

import com.fetch.bor.gui.Game;
import com.fetch.bor.gui.Sprite;
import com.fetch.bor.gui.SpriteStore;

public class PlayerCharacter extends BORCharacter {
	
	int experience;
	int skillPointsTotal;
	int skillPointsSpent;
	
	private Game game;
	private Sprite[] frames;
	private int frameNumber;
	
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
		frames = new Sprite[4];
		frames[0] = SpriteStore.get().getSprite(ref, 64, 64, 0, 0);
		frames[1] = SpriteStore.get().getSprite(ref, 64, 64, 64, 0);
		frames[2] = SpriteStore.get().getSprite(ref, 64, 64, 0, 64);
		frames[3] = SpriteStore.get().getSprite(ref, 64, 64, 64, 64);
		
		frameNumber = 0;
	}

	@Override
	public void collidedWith(BORCharacter other) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void moveNorth() {
		super.moveNorth();
		if (frameNumber == 0) {
			frameNumber = 1;
		} else {
			frameNumber = 0;
		}
	}
	
	@Override
	public void moveSouth() {
		super.moveSouth();
		if (frameNumber == 2) {
			frameNumber = 3;
		} else {
			frameNumber = 2;
		}
	}
	
	@Override
	public void moveEast() {
		super.moveEast();
	}
	
	@Override
	public void moveWest() {
		super.moveWest();
	}
	
	@Override
	public void draw(Graphics2D g) {
		frames[frameNumber].draw(g, (int) xPos * 64, (int) yPos * 64);
	}
	
}
