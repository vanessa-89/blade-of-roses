import java.util.*;

public class GenerationDataType {
	public Stack<Character> structureTypeIndex;
	public Stack<Integer> structureIntIndex;
	public boolean walkable;
	public boolean connection;
	public int tile;
	public int overlaps;
	
	public GenerationDataType() {
		structureTypeIndex = new Stack<Character>();
		structureIntIndex = new Stack<Integer>();
		walkable = false;
		connection = false;
		tile = 0;
		overlaps = 0;
	}

}