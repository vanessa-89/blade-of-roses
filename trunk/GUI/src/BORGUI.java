
/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
public class BORGUI {
	MapCanvas map;
	PlayerCharacter pc;
	
	public static void main(String[] args) {
		BORGUI borgui = new BORGUI();
		borgui.go();
	}
	
	public void go() {
		map = new MapCanvas();
		map.setSize(800, 600);
		map.setVisible(true);
	}
	
}
