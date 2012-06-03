package com.fetch.bor.bor;

import java.awt.Image;

/**
 * 
 * @author MattFMorris
 *
 */
public class PlayerCharacter {

	
	int level;
	int experience;
	MasterStatArray stats = new MasterStatArray();
	int abilityPoints;
	
	// Sprite Sheet for character, must follow strict formating.
	private Image sprite;
	
	// Location
	private int xPos, yPos;
	
	/**
	 * Constructs a generic PC.
	 */
	public PlayerCharacter() {
		xPos = 3;
		yPos = 3;
		level=1;
		experience = 0;
		abilityPoints = 0;
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
	 * Constructs a new PC at the specified position with the specified Sprite Sheet.
	 * @param x The PC's horizontal position.
	 * @param y The PC's vertical position.
	 * @param image The PC's Sprite Sheet.
	 */
	public PlayerCharacter(int x, int y, Image image) {
		xPos = x;
		yPos = y;
		sprite = image;
	}
	
	/**
	 * Moves the PC NORTH. Map handler should test before making this call.
	 */
	public void moveNorth() {
		yPos--;
	}
	
	/**
	 * Moves the PC SOUTH. Map handler should test before making this call.
	 */
	public void moveSouth() {
		yPos++;	
	}
	
	/**
	 * Moves the PC EAST. Map handler should test before making this call.
	 */
	public void moveEast() {
		xPos++;
	}
	
	/**
	 * Moves the PC WEST. Map handler should test before making this call.
	 */
	public void moveWest() {
		xPos--;
	}
	
	/**
	 * Returns the PC's horizontal position.
	 * @return
	 */
	public int getX() {
		return xPos;
	}
	
	/**
	 * Returns the PC's vertical position.
	 * @return
	 */
	public int getY() {
		return yPos;
	}
	
	/**
	 * Returns the PC's sprite sheet to be used in animations.
	 * @return sprite
	 */
	public Image getSprite() {
		return sprite;
	}
	
	
	
	
}
