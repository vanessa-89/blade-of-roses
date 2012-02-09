import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

/**
 * 
 * @author Matt Morris
 *
 * This class handles the over GUI for Blade of Roses.
 */
public class RGLUI {
	
	// Here all the GUI components are declared.
	// This isn't the only way to do this, but I've found it
	// to be the simplest and most sensible.
	JFrame frame;	// The object that holds all other GUI components.
	MapDisplay gameDisplay;	// This is an extension of JPanel with extra functions to handle map display.
	JPanel playerDisplay, quickBar;	// These JPanels are intended to hold the player info and the hotkey buttons.
	JScrollPane updateScroll;	// This creates a scroll bar for a JPanel.
	JTextArea updateDisplay, statsDisplay;	// These are used to display text.
	JButton quick1, quick2, quick3, quick4, quick5, quick6, quick7, quick8, quick9, quick0; // These are buttons.
	JMenuBar menuBar; // This creates a menu bar for the window.
	JMenu fileMenu;	// This populates the above menu bar.
	//JMenuItem
	ImageIcon anIcon = new ImageIcon("C:/Users/Matt/workspace/borGUI/src/testicon.gif"); // This handles the icons for the hotkeys.
	Insets zeroBorder = new Insets(0, 0, 0, 0); // Also for icons... a little odd.
	
	// Here are all the hotkeys.
	// This still needs some work 2/9/12
	HotKey HKOne = new HotKey("1", anIcon, "1", new Integer(KeyEvent.VK_1));
	HotKey HKTwo = new HotKey("2", anIcon, "2", new Integer(KeyEvent.VK_2));
	HotKey HKThree = new HotKey("3", anIcon, "3", new Integer(KeyEvent.VK_3));
	HotKey HKFour = new HotKey("4", anIcon, "4", new Integer(KeyEvent.VK_4));
	HotKey HKFive = new HotKey("5", anIcon, "5", new Integer(KeyEvent.VK_5));
	HotKey HKSix = new HotKey("6", anIcon, "6", new Integer(KeyEvent.VK_6));
	HotKey HKSeven = new HotKey("7", anIcon, "7", new Integer(KeyEvent.VK_7));
	HotKey HKEight = new HotKey("8", anIcon, "8", new Integer(KeyEvent.VK_8));
	HotKey HKNine = new HotKey("9", anIcon, "9", new Integer(KeyEvent.VK_9));
	HotKey HKZero = new HotKey("0", anIcon, "0", new Integer(KeyEvent.VK_0));

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RGLUI().view(); // I use this because main has to be static.
							// Static can cause some programming difficulties.
	}

	/**
	 * Called by main, does the actual GUI setup.
	 */
	void view() {
		// Setup the window here.
		frame = new JFrame("RGL");

		// Creates the MapDisplay. I do all the instantiation here.
		gameDisplay = new MapDisplay();
		
		// Create and setup the Message Area.
		updateDisplay = new JTextArea();
		updateDisplay.setColumns(20);
		updateDisplay.setEditable(false);
		updateDisplay.setText("MESSAGE!");
		updateDisplay.setLineWrap(true);
		updateScroll = new JScrollPane(updateDisplay);
		
		// Create and setup the Player panel.
		playerDisplay = new JPanel();
		statsDisplay = new JTextArea();
		statsDisplay.setRows(15);
		statsDisplay.setColumns(15);
		statsDisplay.setEditable(false);
		statsDisplay.setText("HP:\nMANA:\n\nSTR:\nDEX:\nINT:");
		playerDisplay.add(statsDisplay, BorderLayout.CENTER);

		// Create the quick bar.
		quickBar = new JPanel();
		
		// Populate the quick bar.
		// Quick Slot 1
		quick1 = new JButton("1");
		quick1.setAction(HKOne);
		quick1.setIconTextGap(-24);
		quick1.setMargin(zeroBorder);
		quick1.setBackground(Color.BLACK);

		// Quick Slot 2
		quick2 = new JButton("2");
		quick2.setAction(HKTwo);
		quick2.setIconTextGap(-24);
		quick2.setMargin(zeroBorder);
		quick2.setBackground(Color.BLACK);
		
		// Quick Slot 3
		quick3 = new JButton("3");
		quick3.setAction(HKThree);
		quick3.setIconTextGap(-24);
		quick3.setMargin(zeroBorder);
		quick3.setBackground(Color.BLACK);
		
		// Quick Slot 4
		quick4 = new JButton("4");
		quick4.setAction(HKFour);
		quick4.setIconTextGap(-24);
		quick4.setMargin(zeroBorder);
		quick4.setBackground(Color.BLACK);
		
		// Quick Slot 5
		quick5 = new JButton("5");
		quick5.setAction(HKFive);
		quick5.setIconTextGap(-24);
		quick5.setMargin(zeroBorder);
		quick5.setBackground(Color.BLACK);
		
		// Quick Slot 6
		quick6 = new JButton("6");
		quick6.setAction(HKSix);
		quick6.setIconTextGap(-24);
		quick6.setMargin(zeroBorder);
		quick6.setBackground(Color.BLACK);
		
		// Quick Slot 7
		quick7 = new JButton("7");
		quick7.setAction(HKSeven);
		quick7.setIconTextGap(-24);
		quick7.setMargin(zeroBorder);
		quick7.setBackground(Color.BLACK);
		
		// Quick Slot 8
		quick8 = new JButton("8");
		quick8.setAction(HKEight);
		quick8.setIconTextGap(-24);
		quick8.setMargin(zeroBorder);
		quick8.setBackground(Color.BLACK);
		
		// Quick Slot 9
		quick9 = new JButton("9");
		quick9.setAction(HKNine);
		quick9.setIconTextGap(-24);
		quick9.setMargin(zeroBorder);
		quick9.setBackground(Color.BLACK);
		
		// Quick Slot 0
		quick0 = new JButton("0");
		quick0.setAction(HKZero);
		quick0.setIconTextGap(-24);
		quick0.setMargin(zeroBorder);
		quick0.setBackground(Color.BLACK);
		
		// Add Quick Slot Buttons to GUI
		quickBar.add(quick1);
		quickBar.add(quick2);
		quickBar.add(quick3);
		quickBar.add(quick4);
		quickBar.add(quick5);
		quickBar.add(quick6);
		quickBar.add(quick7);
		quickBar.add(quick8);
		quickBar.add(quick9);
		quickBar.add(quick0);
		
		//Menu Bar
		menuBar = new JMenuBar();
		menuBar.setSize(frame.getWidth(), 100);
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		//Frame Layout
		frame.setJMenuBar(menuBar);
        frame.getContentPane().add(gameDisplay, BorderLayout.CENTER);
        frame.getContentPane().add(updateScroll, BorderLayout.WEST);
        frame.getContentPane().add(playerDisplay, BorderLayout.EAST);
        frame.getContentPane().add(quickBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		frame.setSize(800, 600);
		
		// Initializes the map display
		gameDisplay.initialize();
		
		/**********************************************************************
		 * TEST OF MAP DISPLAY
		 *********************************************************************/
		int[][] map = new int[10][10];
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = random.nextInt(86);
				System.out.print(map[i][j]);
			}
		}
		gameDisplay.loadMap(map);
		/**********************************************************************
		 * TEST OF MAP DISPLAY
		 *********************************************************************/
	}
	
	/** Hot Key One  */
	class HotKey extends AbstractAction {
	    public HotKey(String text, ImageIcon icon,
	                      String desc, Integer mnemonic) {
	        super(text, icon);
	        putValue(SHORT_DESCRIPTION, desc);
	        putValue(MNEMONIC_KEY, mnemonic);
	    }
	    public void actionPerformed(ActionEvent e) {
	        updateDisplay.append("\nPlayer casts " + e.getActionCommand() + "!");
	    }
	}

}

