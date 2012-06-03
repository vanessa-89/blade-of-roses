package com.fetch.bor.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class UICanvas extends Canvas {

	private BufferedImage ui;
	
	public UICanvas() {
		try {
			File file = new File("BORUI.png");
			ui = ImageIO.read(file);
		} catch (IOException ioe) {
			System.out.println("BOR could not load the image.");
			ioe.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(ui, 0, 0, 800, 600, 0, 0, 800, 600, this);
	}
}
