package com.fetch.bor.bor;


public class MasterStatArray {

	public int[] statArray = new int[15];

	// adds another array to this array
	void modifyArray (int[] modifier) {
		for (int i = 0; i < 14; i++) {
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
	
	void setWillpower ( int newWillpower ) {
		statArray[5] = newWillpower;
	}
	
	int getWillpower () {
		return statArray[5];
	}
	
	void setIntelligence ( int newIntelligence ) {
		statArray[6] = newIntelligence;
	}
	
	int getIntelligence () {
		return statArray[6];
	}
	
	void setSpellDamage ( int newSpellDamage ) {
		statArray[7] = newSpellDamage;
	}
	
	int getSpellDamage () {
		return statArray[7];
	}
	
	void setDodge ( int newDodge ) {
		statArray[8] = newDodge;
	}
	
	int getDodge () {
		return statArray[8];
	}
	
	void setParry ( int newParry ) {
		statArray[9] = newParry;
	}
	
	int getParry () {
		return statArray[9];
	}
	
	void setCounterspell ( int newCounterspell ) {
		statArray[10] = newCounterspell;
	}
	
	int getCounterspell () {
		return statArray[10];
	}
	
	void setBlock ( int newBlock ) {
		statArray[11] = newBlock;
	}
	
	int getBlock () {
		return statArray[11];
	}
	
	void setShield ( int newShield ) {
		statArray[12] = newShield;
	}
	
	int getShield () {
		return statArray[12];
	}
	
	void setArmor ( int newArmor ) {
		statArray[13] = newArmor;
	}
	
	int getArmor () {
		return statArray[13];
	}
	
	void setResist ( int newResist ) {
		statArray[14] = newResist;
	}
	
	int getResist () {
		return statArray[14];
	}
	
	void setMaxBody ( int newMaxBody ) {
		statArray[15] = newMaxBody;
	}
	
	int getMaxBody () {
		return statArray[15];
	}
	
	void setMaxSoul ( int newMaxSoul ) {
		statArray[16] = newMaxSoul;
	}
	
	int getMaxSoul () {
		return statArray[16];
	}
/*	0  body
	1  soul
    2  strength
    3  dexterity
    4  damage
    5  willpower
    6  intelligence
    7  spell damage
    8  dodge
    9  parry
    10 counterspell
    11 block
    12 shield
    13 armor
    14 resist
    15 Max Body
    16 Max Soul
*/    
     
}
