import java.awt.BorderLayout;

import javax.swing.*;


public class RGLUI {
	
	JFrame frame;
	JPanel gameDisplay, playerDisplay, quickBar;
	JTextArea updateDisplay, statsDisplay;
	JButton quick1, quick2, quick3, quick4, quick5, quick6, quick7, quick8, quick9, quick0;
	JMenuBar menuBar;
	JMenu fileMenu;
	//JMenuItem

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
		quick1 = new JButton("1");
		quick2 = new JButton("2");
		quick3 = new JButton("3");
		quick4 = new JButton("4");
		quick5 = new JButton("5");
		quick6 = new JButton("6");
		quick7 = new JButton("7");
		quick8 = new JButton("8");
		quick9 = new JButton("9");
		quick0 = new JButton("0");
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
}
