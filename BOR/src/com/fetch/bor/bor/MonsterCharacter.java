package com.fetch.bor.bor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import com.fetch.bor.gui.Game;

public class MonsterCharacter extends BORCharacter{
	
	private Game game;
	private int step = 0;
	private int stepDuration = 300;

	public MonsterCharacter() {
		xPos = 3;
		yPos = 3;
		targetX = 3;
		targetY = 4;
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
	
		//image = ImageLoader.DefaultCharacter
	}
	
	/**
	 * Constructs a new C at the specified position with the specified Sprite Sheet.
	 * @param x The C's horizontal position.
	 * @param y The C's vertical position.
	 * @param image The C's Sprite Sheet.
	 */
	public MonsterCharacter(int x, int y, Image image) {
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

	public MonsterCharacter(Game game, String ref, int x, int y) {
		super(ref, x, y);
		this.game = game;
	}

	@Override
	public void collidedWith(BORCharacter other) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move(long delta) {
		step += delta;
		if (step > stepDuration) {
			step = 0;
			Random r = new Random();
			int moveX = r.nextInt(3) - 1;
			int moveY = r.nextInt(3) - 1;
			if (xPos + moveX >= 0) {
				boolean validX = false;
				if (moveX == -1) {
					validX = game.map.moveIsValid(moveX + xPos, yPos, Map.WEST);
				} else if (moveX == 1) {
					validX = game.map.moveIsValid(moveX + xPos, yPos, Map.EAST);
				}
				if (validX) {
					xPos += moveX;
				}
			}
			if (yPos + moveY >= 0) {
				boolean validY = false;
				if (moveY == -1) {
					validY = game.map
							.moveIsValid(xPos, moveY + yPos, Map.NORTH);
				} else if (moveX == 1) {
					validY = game.map
							.moveIsValid(xPos, moveY + yPos, Map.SOUTH);
				}
				if (validY) {
					yPos += moveY;
				}
			}
			System.out.println("MONSTER X:" + xPos + " Y:" + yPos);
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (game.map.isInView(xPos, yPos)) {
			
			spriteH.draw(g, (int) game.map.getRelativeX(xPos) * 64, (int) game.map.getRelativeY(yPos) * 64);
		}
	}

}
