package com.fetch.bor.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.fetch.bor.bor.TestArea;


/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
public class BORGUI {
	
	static BufferedImage icon;
	
	
	public static void main(String[] args) {
		try {
			File iconFile = new File("ICON_ROSE.gif");
			icon = ImageIO.read(iconFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFrame window = new JFrame("Blade of Roses");
		window.setIconImage(icon);
		
		TestArea ta = new TestArea(10);
		
		MapCanvas mapCanvas = new MapCanvas();
		mapCanvas.setPreferredSize(new Dimension(800, 800));
		mapCanvas.loadTileset("TestTileSet.png");
		mapCanvas.loadMap(ta.getTiles());
		window.getContentPane().add(mapCanvas);
		window.setLocation(100, 100);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
