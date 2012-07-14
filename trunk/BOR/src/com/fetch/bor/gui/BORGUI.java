package com.fetch.bor.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.fetch.bor.bor.MonsterCharacter;
import com.fetch.bor.bor.PlayerCharacter;
import com.fetch.bor.bor.TestArea;


/**
 * 
 * @author MattFMorris
 * @version 0.1
 *
 */
public class BORGUI {
	
	static BufferedImage icon;
	static BufferedImage sprite;
	static PlayerCharacter pc;
	
	
	public static void main(String[] args) {
		try {
			File iconFile = new File("ICON_ROSE.gif");
			icon = ImageIO.read(iconFile);
			File spriteFile = new File("Player1.png");
			sprite = ImageIO.read(spriteFile);
			pc = new PlayerCharacter(3, 3, sprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFrame window = new JFrame("Blade of Roses");
		window.setIconImage(icon);
		
		TestArea ta = new TestArea(14);
		int size = 14 * 64;
		
		MapCanvas mapCanvas = new MapCanvas();
		mapCanvas.setPreferredSize(new Dimension(800, 600));
		mapCanvas.setLocation(200,200);
		mapCanvas.loadTileset("TestTileSet.png");
		mapCanvas.loadMap(ta.getTiles());
		mapCanvas.addCharacter(pc);
		
		/**************************** TEST **************************************/
		final BufferedImage impImage;
		try {
			File file = new File("Imp001.png");
			impImage = ImageIO.read(file);
			MonsterCharacter imp = new MonsterCharacter(8, 3, impImage);
			mapCanvas.addElement(imp);
		} catch (IOException ioe) {
			System.out.println("BOR could not load the image.");
			ioe.printStackTrace();
		}
		/**************************** TEST **************************************/
		
		window.getContentPane().add(mapCanvas);
		window.setLocation(500, 50);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
