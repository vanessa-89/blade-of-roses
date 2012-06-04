package com.fetch.bor.bor;

import java.awt.Image;
import java.util.Random;

/**
 * 
 * @author MattFMorris
 *
 */
/**
 * @author Ranger
 *
 */
/**
 * @author Ranger
 *
 */
/**
 * @author Ranger
 *
 */
public class Character {

	Random rand = new Random();
	int level;
	MasterStatArray stats = new MasterStatArray();
	
	// Sprite Sheet for character, must follow strict formating.
	protected Image sprite;
	
	// Location
	protected int xPos, yPos;
	
	//Facing
	public int targetX, targetY;
	
	/**
	 * Constructs a generic PC.
	 */
	public Character() {
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
	public Character(int x, int y, Image image) {
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
	
	/**
	 * Moves the C NORTH. Map handler should test before making this call.
	 */
	public void moveNorth() {
		yPos--;
		targetX = xPos;
		targetY = yPos-1;
	}
	
	/**
	 * Moves the C SOUTH. Map handler should test before making this call.
	 */
	public void moveSouth() {
		yPos++;
		targetX = xPos;
		targetY = yPos+1;
	}
	
	/**
	 * Moves the C EAST. Map handler should test before making this call.
	 */
	public void moveEast() {
		xPos++;
		targetX = xPos+1;
		targetY = yPos;
	}
	
	/**
	 * Moves the C WEST. Map handler should test before making this call.
	 */
	public void moveWest() {
		xPos--;
		targetX = xPos-1;
		targetY = yPos;
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
	
 
	/*public void WeaponAttack() {
		Character target = MasterArray.getCharacter( targetX, targetY );
		int attStr = stats.getStrength();
        int attDex = stats.getDexterity();
		int attDmg = stats.getDamage();
		int defDdg = target.stats.getDodge()+target.stats.getDexterity()/5;
		int defPry = target.stats.getParry();
		int defBlk = target.stats.getBlock();
		int defAmr = target.stats.getArmor();
		int hit = rand.nextInt(attDex)+stats.getHit();
		if (hit < defDdg)
			return;
		if (rand.nextInt(100)<Math.max(defPry, defBlk))
			return;
		if (rand.nextInt(100)<(Math.min(defPry, defBlk))/2)
			return;
		int damageDealt = rand.nextInt(attDmg) + attStr/5 - defAmr;
		damageDealt *= -1;
		target.stats.modifyBody(damageDealt);
	}*/
	
	/*public void MagicAttack() {
		Character target = MasterArray.getCharacter( magicTargetX, magicTargetY );
		int attWpr = stats.getWillpower();
		int attInt = stats.getIntelligence();
		int attSpD = stats.getSpellDamage();
		int defDdg = target.stats.getDodge()+target.stats.getDexterity()/5;
		int defCtS = target.stats.getCounterSpell();
		int defShd = target.stats.getShield();
		int defRst = target.stats.getResist();
		int hit = rand.nextInt(attInt)+stats.getSpD();
		if (hit < defDdg)
			return;
		if (rand.nextInt(100)<Math.max(defCts, defShd))
			return;
		if (rand.nextInt(100)<(Math.min(defCtS, defShd))/2)
			return;
		int damageDealt = rand.nextInt(attSpD) + attWpr/5 - defRst;
		damageDealt *= -1;
		target.stats.modifyBody(damageDealt);
}*/
	
	
}
