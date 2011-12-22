import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class RGLUI {
	
	JFrame frame;
	JPanel gameDisplay, playerDisplay, quickBar;
	JTextArea updateDisplay, statsDisplay;
	JButton quick1, quick2, quick3, quick4, quick5, quick6, quick7, quick8, quick9, quick0;
	JMenuBar menuBar;
	JMenu fileMenu;
	//JMenuItem
	ImageIcon anIcon;
	
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
		new RGLUI().view();
	}

	public void view() {
		frame = new JFrame("RGL");
		// BLAH BLAH BLAH
		gameDisplay = new JPanel();
		
		updateDisplay = new JTextArea();
		updateDisplay.setColumns(20);
		updateDisplay.setEditable(false);
		updateDisplay.setText("MESSAGE!");
		updateDisplay.setLineWrap(true);
		
		playerDisplay = new JPanel();
		statsDisplay = new JTextArea();
		statsDisplay.setRows(15);
		statsDisplay.setColumns(15);
		statsDisplay.setEditable(false);
		statsDisplay.setText("HP:\nMANA:\n\nSTR:\nDEX:\nINT:");
		playerDisplay.add(statsDisplay, BorderLayout.CENTER);

		
		quickBar = new JPanel();
		
		// Quick Slot 1
		quick1 = new JButton("1");
		//quick1.getInputMap().put(KeyStroke.getKeyStroke("1"), "1press");
		//quick1.getActionMap().put("1press", HKOne);
		quick1.setAction(HKOne);

		// Quick Slot 2
		quick2 = new JButton("2");
		//quick2.getInputMap().put(KeyStroke.getKeyStroke("2"), "2press");
		//quick2.getActionMap().put("2press", HKTwo);
		quick2.setAction(HKTwo);
		
		// Quick Slot 3
		quick3 = new JButton("3");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("3"), "3press");
		//gameDisplay.getActionMap().put("3press", hkThree);
		quick3.setAction(HKThree);
		
		// Quick Slot 4
		quick4 = new JButton("4");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("4"), "4press");
		//gameDisplay.getActionMap().put("4press", hkFour);
		quick4.setAction(HKFour);
		
		// Quick Slot 5
		quick5 = new JButton("5");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("5"), "5press");
		//gameDisplay.getActionMap().put("5press", hkFive);
		quick5.setAction(HKFive);
		
		// Quick Slot 6
		quick6 = new JButton("6");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("6"), "6press");
		//gameDisplay.getActionMap().put("6press", hkSix);
		quick6.setAction(HKSix);
		
		// Quick Slot 7
		quick7 = new JButton("7");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("7"), "7press");
		//gameDisplay.getActionMap().put("7press", hkSeven);
		quick7.setAction(HKSeven);
		
		// Quick Slot 8
		quick8 = new JButton("8");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("8"), "8press");
		//gameDisplay.getActionMap().put("8press", hkEight);
		quick8.setAction(HKEight);
		
		// Quick Slot 9
		quick9 = new JButton("9");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("9"), "9press");
		//gameDisplay.getActionMap().put("9press", hkNine);
		quick9.setAction(HKNine);
		
		// Quick Slot 0
		quick0 = new JButton("0");
		//gameDisplay.getInputMap().put(KeyStroke.getKeyStroke("0"), "0press");
		//gameDisplay.getActionMap().put("0press", hkZero);
		quick0.setAction(HKZero);
		
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
        frame.getContentPane().add(updateDisplay, BorderLayout.WEST);
        frame.getContentPane().add(playerDisplay, BorderLayout.EAST);
        frame.getContentPane().add(quickBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		frame.setSize(800, 600);
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

