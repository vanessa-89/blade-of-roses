package com.fetch.bor.gui;

import javax.swing.JFrame;


/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
public class BORGUI {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.getContentPane().add(new MapCanvas());
		window.setLocation(100, 100);
		window.pack();
		window.setVisible(true);
	}
}
