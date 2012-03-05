/**
 * 
 * @author MattFMorris
 *
 */
public class TestDungeon {
	int[][] dungeon;
	PlayerCharacter pc;
	public TestDungeon() {
		dungeon = new int[][]{
						{12,25,25,25,25,13},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{24,85,85,85,85,26},
						{15,27,27,27,27,14}
					};
		pc = new PlayerCharacter();
	}
	
	public int[][] getDungeon() {
		return dungeon;
	}
	
	public PlayerCharacter getPC() {
		return pc;
	}
}
