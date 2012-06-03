package com.fetch.bor.bor;

import java.awt.Image;

/**
 * 
 * @author MattFMorris
 *
 */
public class Character {

	
	int level;
	MasterStatArray stats = new MasterStatArray();
	
	// Sprite Sheet for character, must follow strict formating.
	private Image sprite;
	
	// Location
	private int xPos, yPos;
	
	/**
	 * Constructs a generic PC.
	 */
	public Character() {
		xPos = 3;
		yPos = 3;
        level = 1;
		stats.setStrength(10);
		stats.setDexterity(10);
		stats.setDamage(0);
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
	public Character(int x, int y, Image image) {
		xPos = x;
		yPos = y;
		sprite = image;
	}
	
	/**
	 * Moves the C NORTH. Map handler should test before making this call.
	 */
	public void moveNorth() {
		yPos--;
	}
	
	/**
	 * Moves the C SOUTH. Map handler should test before making this call.
	 */
	public void moveSouth() {
		yPos++;	
	}
	
	/**
	 * Moves the C EAST. Map handler should test before making this call.
	 */
	public void moveEast() {
		xPos++;
	}
	
	/**
	 * Moves the C WEST. Map handler should test before making this call.
	 */
	public void moveWest() {
		xPos--;
	}
	
	/**
	 * Returns the C's horizontal position.
	 * @return
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * Returns the C's vertical position.
	 * @return
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * Returns the C's sprite sheet to be used in animations.
	 * @return sprite
	 */
	public Image getSprite() {
		return sprite;
	}
	
	
	
	
}
