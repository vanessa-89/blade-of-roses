
public class MasterStatArray {

	public int[] statArray = new int[12];

	// adds another array to this array
	void modifyArray (int[] modifier) {
		for (int i = 0; i < 12; i++) {
			statArray[i] += modifier[i];
		}
	}
	
	void setHealth ( int newHealth ) {
		statArray[0] = newHealth;
	}
	
	int getHealth () {
		return statArray[0];
	}
	
	void modifyHealth ( int modifier ) {
		statArray[0] += modifier;
	}
	
	void setMana ( int newMana ) {
		statArray[1] = newMana;
	}
	
	int getMana () {
		return statArray[1];
	}
	
	void modifyMana ( int modifier ) {
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
	
	void setIntelligence ( int newIntelligence ) {
		statArray[4] = newIntelligence;
	}
	
	int getIntelligence () {
		return statArray[4];
	}
	
	void setDamage ( int newDamage ) {
		statArray[5] = newDamage;
	}
	
	int getDamage () {
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
	
	void setBlock ( int newBlock ) {
		statArray[8] = newBlock;
	}
	
	int getBlock () {
		return statArray[8];
	}
	
	void setArmor ( int newArmor ) {
		statArray[9] = newArmor;
	}
	
	int getArmor () {
		return statArray[9];
	}
	
	void setMaxHP ( int newMaxHP ) {
		statArray[10] = newMaxHP;
	}
	
	int getMaxHP () {
		return statArray[10];
	}
	
	void setMaxMP ( int newMaxMP ) {
		statArray[11] = newMaxMP;
	}
	
	int getMaxMP () {
		return statArray[11];
	}
/*	0  health
	1  mana
    2  strength
    3  dexterity
    4  intelligence
    5  damage
    6  dodge
    7  parry
    8  block
    9  armor
    10 Max HP
    11 Max MP
*/    
     
}
