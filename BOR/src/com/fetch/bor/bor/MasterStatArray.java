package com.fetch.bor.bor;


public class MasterStatArray {

	public int[] statArray = new int[18];

	// adds another array to this array
	void modifyArray (int[] modifier) {
		for (int i = 0; i < 18; i++) {
			statArray[i] += modifier[i];
		}
	}
	
	void setBody ( int newBody ) {
		statArray[0] = newBody;
	}
	
	int getBody () {
		return statArray[0];
	}
	
	void modifyBody ( int modifier ) {
		statArray[0] += modifier;
	}
	
	void setSoul ( int newSoul ) {
		statArray[1] = newSoul;
	}
	
	int getSoul () {
		return statArray[1];
	}
	
	void modifySoul ( int modifier ) {
		statArray[1] += modifier;
	}
	
	void setStrength ( int newStrength ) {
		statArray[2] = newStrength;
	}
	
	int getStrength () {
		return statArray[2];
	}
	
	void setDexterity ( int newDexterity ) {
		statArray[3] = newDexterity;
	}
	
	int getDexterity () {
		return statArray[3];
	}
	
	void setDamage ( int newDamage ) {
		statArray[4] = newDamage;
	}
	
	int getDamage () {
		return statArray[4];
	}
	
	void setHit (int newHit) {
		statArray[5] = newHit;
	}
	
	int getHit () {
		return statArray[5];
	}
	void setWillpower ( int newWillpower ) {
		statArray[6] = newWillpower;
	}
	
	int getWillpower () {
		return statArray[6];
	}
	
	void setIntelligence ( int newIntelligence ) {
		statArray[7] = newIntelligence;
	}
	
	int getIntelligence () {
		return statArray[7];
	}
	
	void setSpellDamage ( int newSpellDamage ) {
		statArray[8] = newSpellDamage;
	}
	
	int getSpellDamage () {
		return statArray[8];
	}
	
	void setDodge ( int newDodge ) {
		statArray[9] = newDodge;
	}
	
	int getDodge () {
		return statArray[9];
	}
	
	void setParry ( int newParry ) {
		statArray[10] = newParry;
	}
	
	int getParry () {
		return statArray[10];
	}
	
	void setCounterspell ( int newCounterspell ) {
		statArray[11] = newCounterspell;
	}
	
	int getCounterspell () {
		return statArray[11];
	}
	
	void setBlock ( int newBlock ) {
		statArray[12] = newBlock;
	}
	
	int getBlock () {
		return statArray[12];
	}
	
	void setShield ( int newShield ) {
		statArray[13] = newShield;
	}
	
	int getShield () {
		return statArray[13];
	}
	
	void setArmor ( int newArmor ) {
		statArray[14] = newArmor;
	}
	
	int getArmor () {
		return statArray[14];
	}
	
	void setResist ( int newResist ) {
		statArray[15] = newResist;
	}
	
	int getResist () {
		return statArray[15];
	}
	
	void setMaxBody ( int newMaxBody ) {
		statArray[16] = newMaxBody;
	}
	
	int getMaxBody () {
		return statArray[16];
	}
	
	void setMaxSoul ( int newMaxSoul ) {
		statArray[17] = newMaxSoul;
	}
	
	int getMaxSoul () {
		return statArray[17];
	}
/*	0  body
	1  soul
    2  strength
    3  dexterity
    4  damage
    5  hit
    6  willpower
    7  intelligence
    8  spell damage
    9  dodge
    10  parry
    11 counterspell
    12 block
    13 shield
    14 armor
    15 resist
    16 Max Body
    17 Max Soul
*/    
     
}
