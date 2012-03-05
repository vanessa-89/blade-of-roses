import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * @author MattFMorris
 * @version 0.2
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
	JTextArea updateDisplay, extraDisplay;	// These are used to display text.
	JTextField healthBar, soulBar;
	JLabel healthLab, soulLab;
	JButton quick1, quick2, quick3, quick4, quick5, quick6, quick7, quick8, quick9, quick0; // These are buttons.
	JMenuBar menuBar; // This creates a menu bar for the window.
	JMenu fileMenu;	// This populates the above menu bar.
	JMenu testMenu;
	//JMenuItem exitItem;
	JMenuItem dungeonItem, forestItem, cityItem, reloadItem, loadTestItem;
	ImageIcon anIcon = new ImageIcon("testicon.gif"); // This handles the icons for the hotkeys.
	
	Insets zeroBorder = new Insets(0, 0, 0, 0); // Also for icons... a little odd.
	
	// Here are all the hot keys listeners.
	String strHK1, strHK2, strHK3, strHK4, strHK5, strHK6, strHK7, strHK8, strHK9, strHK0;
	HotKey HKOne = new HotKey("1", anIcon, "1", KeyEvent.VK_1);
	HotKey HKTwo = new HotKey("2", anIcon, "2", KeyEvent.VK_2);
	HotKey HKThree = new HotKey("3", anIcon, "3", KeyEvent.VK_3);
	HotKey HKFour = new HotKey("4", anIcon, "4", KeyEvent.VK_4);
	HotKey HKFive = new HotKey("5", anIcon, "5", KeyEvent.VK_5);
	HotKey HKSix = new HotKey("6", anIcon, "6", KeyEvent.VK_6);
	HotKey HKSeven = new HotKey("7", anIcon, "7", KeyEvent.VK_7);
	HotKey HKEight = new HotKey("8", anIcon, "8", KeyEvent.VK_8);
	HotKey HKNine = new HotKey("9", anIcon, "9", KeyEvent.VK_9);
	HotKey HKZero = new HotKey("0", anIcon, "0", KeyEvent.VK_0);

	
	ReloadMap reloadMap = new ReloadMap();
	LoadTestMap loadTestMap = new LoadTestMap();
	
	Movement moveUp = new Movement("NORTH");
	Movement moveDown = new Movement("SOUTH");
	Movement moveLeft = new Movement("WEST");
	Movement moveRight = new Movement("EAST");
	
	
	int curHealth = 100;
	int maxHealth = 100;
	StringBuilder health = new StringBuilder(curHealth+"/"+maxHealth);	
	int curSoul = 100;
	int maxSoul = 100;
	StringBuilder soul = new StringBuilder(curSoul+"/"+maxSoul);
	
	static BufferedImage icon;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Load the Icon
		try {
			File iconFile = new File("ICON_ROSE.gif");
			icon = ImageIO.read(iconFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Initiate the GUI
		new RGLUI().view(); // I use this because main has to be static.
							// Static can cause some programming difficulties.
	}

	/**
	 * Called by main, does the actual GUI setup.
	 */
	@SuppressWarnings("static-access")
	void view() {
		// Setup the window here.
		frame = new JFrame("The Blade of Roses");
		frame.setIconImage(icon);
		

		// Creates the MapDisplay. I do all the instantiation here.
		gameDisplay = new MapDisplay();
		// Initializes the map display
		gameDisplay.initialize();
		
		
		// Create and setup the Message Area.
		updateDisplay = new JTextArea();
		updateDisplay.setColumns(20);
		updateDisplay.setEditable(false);
		updateDisplay.setLineWrap(true);
		updateDisplay.setForeground(Color.RED);
		updateDisplay.setBackground(Color.BLACK);
		updateScroll = new JScrollPane(updateDisplay);

		
		// Create and setup the Player panel.
		playerDisplay = new JPanel();
		
		healthLab = new JLabel("Health: ");
		healthBar = new JTextField(10);
		healthBar.setEditable(false);
		healthBar.setText(health.toString());
		soulLab = new JLabel("Soul: ");
		soulBar = new JTextField(10);
		soulBar.setEditable(false);
		soulBar.setText(soul.toString());
		
		playerDisplay.add(healthLab);
		playerDisplay.add(healthBar);
		playerDisplay.add(soulLab);
		playerDisplay.add(soulBar);

		
		// Create the quick bar.
		quickBar = new JPanel();
		quickBar.setBackground(Color.BLACK);

		
		// HOT FUCKIN' KEYS!!!!!!!!!!!!!!
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "do1");
		quickBar.getActionMap().put("do1", HKOne);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "do2");
		quickBar.getActionMap().put("do2", HKTwo);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "do3");
		quickBar.getActionMap().put("do3", HKThree);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"), "do4");
		quickBar.getActionMap().put("do4", HKFour);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("5"), "do5");
		quickBar.getActionMap().put("do5", HKFive);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"), "do6");
		quickBar.getActionMap().put("do6", HKSix);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("7"), "do7");
		quickBar.getActionMap().put("do7", HKSeven);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("8"), "do8");
		quickBar.getActionMap().put("do8", HKEight);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("9"), "do9");
		quickBar.getActionMap().put("do9", HKNine);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("0"), "do0");
		quickBar.getActionMap().put("do0", HKZero);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "doUp");
		quickBar.getActionMap().put("doUp", moveUp);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "doDown");
		quickBar.getActionMap().put("doDown", moveDown);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "doLeft");
		quickBar.getActionMap().put("doLeft", moveLeft);
		quickBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "doRight");
		quickBar.getActionMap().put("doRight", moveRight);
		// HOT FUCKIN' KEYS!!!!!!!!!!!!!!
		
		// Populate the quick bar.
		// Quick Slot 1
		quick1 = new JButton("1");
		quick1.setAction(HKOne);
		quick1.setIconTextGap(-24);
		quick1.setBorder(BorderFactory.createEmptyBorder());
		quick1.setBackground(Color.BLACK);

		// Quick Slot 2
		quick2 = new JButton("2");
		quick2.setAction(HKTwo);
		quick2.setIconTextGap(-24);
		quick2.setBorder(BorderFactory.createEmptyBorder());
		quick2.setBackground(Color.BLACK);
		
		// Quick Slot 3
		quick3 = new JButton("3");
		quick3.setAction(HKThree);
		quick3.setIconTextGap(-24);
		quick3.setBorder(BorderFactory.createEmptyBorder());
		quick3.setBackground(Color.BLACK);
		
		// Quick Slot 4
		quick4 = new JButton("4");
		quick4.setAction(HKFour);
		quick4.setIconTextGap(-24);
		quick4.setBorder(BorderFactory.createEmptyBorder());
		quick4.setBackground(Color.BLACK);
		
		// Quick Slot 5
		quick5 = new JButton("5");
		quick5.setAction(HKFive);
		quick5.setIconTextGap(-24);
		quick5.setBorder(BorderFactory.createEmptyBorder());
		quick5.setBackground(Color.BLACK);
		
		// Quick Slot 6
		quick6 = new JButton("6");
		quick6.setAction(HKSix);
		quick6.setIconTextGap(-24);
		quick6.setBorder(BorderFactory.createEmptyBorder());
		quick6.setBackground(Color.BLACK);
		
		// Quick Slot 7
		quick7 = new JButton("7");
		quick7.setAction(HKSeven);
		quick7.setIconTextGap(-24);
		quick7.setBorder(BorderFactory.createEmptyBorder());
		quick7.setBackground(Color.BLACK);
		
		// Quick Slot 8
		quick8 = new JButton("8");
		quick8.setAction(HKEight);
		quick8.setIconTextGap(-24);
		quick8.setBorder(BorderFactory.createEmptyBorder());
		quick8.setBackground(Color.BLACK);
		
		// Quick Slot 9
		quick9 = new JButton("9");
		quick9.setAction(HKNine);
		quick9.setIconTextGap(-24);
		quick9.setBorder(BorderFactory.createEmptyBorder());
		quick9.setBackground(Color.BLACK);
		
		// Quick Slot 0
		quick0 = new JButton("0");
		quick0.setAction(HKZero);
		quick0.setIconTextGap(-24);
		quick0.setBorder(BorderFactory.createEmptyBorder());
		//quick0.setMargin(zeroBorder); //Embossed Style Buttons
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
		
		testMenu = new JMenu("Test");
		reloadItem = new JMenuItem("Reload",KeyEvent.VK_R);
		reloadItem.addActionListener(reloadMap);

		loadTestItem = new JMenuItem("Load Test Map", KeyEvent.VK_T);
		loadTestItem.addActionListener(loadTestMap);
		testMenu.add(reloadItem);
		testMenu.add(loadTestItem);
		menuBar.add(testMenu);
		
		
		//Frame Layout
		frame.setJMenuBar(menuBar);
        frame.getContentPane().add(gameDisplay, BorderLayout.CENTER);
        frame.getContentPane().add(updateScroll, BorderLayout.WEST);
        frame.getContentPane().add(playerDisplay, BorderLayout.EAST);
        frame.getContentPane().add(quickBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //GraphicsDevice device;
        //if (device.isFullScreenSupported()) {
        	
        //} else {
        	frame.setSize(1200, 900);
        //}
		
		
		
		/**********************************************************************
		 * TEST OF MAP DISPLAY
		 * 
		 * This test generates a random array of numbers within the allowed range
		 *  for the tileset. The purpose here is to verify that the tile placement
		 *  and identification algorithm works.
		 *  
		 *  End of tile range is for now 85
		 *  Absolute Null Ranges:
		 *  16,20-23,28-35,38-47,52-59,68-71,76-83
		 *********************************************************************/
        int[][] map = new int[12][9];
		Random random = new Random();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j] = random.nextInt(20);
			}
		}
		gameDisplay.loadMap(map);
		gameDisplay.repaint();
		/**********************************************************************
		 * END TEST OF MAP DISPLAY
		 *********************************************************************/
	}
	
	/** Hot Keys  */
	@SuppressWarnings("serial")
	class HotKey extends AbstractAction {
	    public HotKey(String text, ImageIcon icon, String desc, Integer mnemonic) {
	        super(text, icon);
	        putValue(SHORT_DESCRIPTION, desc);
	        putValue(MNEMONIC_KEY, mnemonic);
	    }
	    public void actionPerformed(ActionEvent e) {
	        updateDisplay.append("\nPlayer casts " + e.getActionCommand() + "!");
	    }
	}
	
	/** Reload Map  */
	@SuppressWarnings("serial")
	class ReloadMap extends AbstractAction {
	    public void actionPerformed(ActionEvent e) {
	    	int h = gameDisplay.getHeight()/65;
	    	int w = gameDisplay.getWidth()/65;
	    	int[][] map = new int[h][w];
			Random random = new Random();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = random.nextInt(86);
				}
			}
			gameDisplay.loadMap(map);
			gameDisplay.repaint();
	    }
	}
	
	/** Loads a testing map  */
	@SuppressWarnings("serial")
	class LoadTestMap extends AbstractAction {
	    public void actionPerformed(ActionEvent e) {
	    	TestDungeon test = new TestDungeon();
	    	int[][] map = test.getDungeon();
			gameDisplay.loadMap(map);
			gameDisplay.repaint();
	    }
	}

	
	
	@SuppressWarnings("serial")
	class Movement extends AbstractAction {
		String dir;
		public Movement(String text) {
			super(text);
			dir = text;
		}
		public void actionPerformed(ActionEvent e) {
			updateDisplay.append("\nPlayer moves " + dir + ".");
		}
	}
	
	
	
}

