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
	
	void setWillpower ( int newWillpower ) {
		statArray[4] = newWillpower;
	}
	
	int getWillpower () {
		return statArray[4];
	}
	
	void setIntelligence ( int newIntelligence ) {
		statArray[5] = newIntelligence;
	}
	
	int getIntelligence () {
		return statArray[5];
	}
	
	void setDodge ( int newDodge ) {
		statArray[6] = newDodge;
	}
	
	int getDodge () {
		return statArray[6];
	}
	
	void setParry ( int newParry ) {
		statArray[7] = newParry;
	}
	
	int getParry () {
		return statArray[7];
	}
	
	void setCounterspell ( int newCounterspell ) {
		statArray[8] = newCounterspell;
	}
	
	int getCounterspell () {
		return statArray[8];
	}
	
	void setBlock ( int newBlock ) {
		statArray[9] = newBlock;
	}
	
	int getBlock () {
		return statArray[9];
	}
	
	void setShield ( int newShield ) {
		statArray[10] = newShield;
	}
	
	int getShield () {
		return statArray[10];
	}
	
	void setArmor ( int newArmor ) {
		statArray[11] = newArmor;
	}
	
	int getArmor () {
		return statArray[11];
	}
	
	void setResist ( int newResist ) {
		statArray[12] = newResist;
	}
	
	int getResist () {
		return statArray[12];
	}
	
	void setMaxBody ( int newMaxBody ) {
		statArray[13] = newMaxBody;
	}
	
	int getMaxBody () {
		return statArray[13];
	}
	
	void setMaxSoul ( int newMaxSoul ) {
		statArray[14] = newMaxSoul;
	}
	
	int getMaxSoul () {
		return statArray[14];
	}
/*	0  body
	1  soul
    2  strength
    3  dexterity
    4  willpower
    5  intelligence
    6  dodge
    7  parry
    8  counterspell
    9  block
    10 shield
    11 armor
    12 resist
    13 Max Body
    14 Max Soul
*/    
     
}
