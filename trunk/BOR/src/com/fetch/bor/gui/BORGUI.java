package com.fetch.bor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import com.fetch.bor.Procedural.ProceduralGeneration;
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
		// Musicz
//		int totalFramesRead = 0;
//		File fileIn = new File("Song013.mid");
//		try {
//		  AudioInputStream audioInputStream = 
//		    AudioSystem.getAudioInputStream(fileIn);
//		  int bytesPerFrame = 
//		    audioInputStream.getFormat().getFrameSize();
//		    if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
//		    // some audio formats may have unspecified frame size
//		    // in that case we may read any amount of bytes
//		    bytesPerFrame = 1;
//		  } 
//		  // Set an arbitrary buffer size of 1024 frames.
//		  int numBytes = 1024 * bytesPerFrame; 
//		  byte[] audioBytes = new byte[numBytes];
//		  try {
//		    int numBytesRead = 0;
//		    int numFramesRead = 0;
//		    // Try to read numBytes bytes from the file.
//		    while ((numBytesRead = 
//		      audioInputStream.read(audioBytes)) != -1) {
//		      // Calculate the number of frames actually read.
//		      numFramesRead = numBytesRead / bytesPerFrame;
//		      totalFramesRead += numFramesRead;
//		      // Here, do something useful with the audio data that's 
//		      // now in the audioBytes array...
//		      
//		    }
//		    Clip clip = AudioSystem.getClip();
//		    clip.loop(2);
//		    clip.open(audioInputStream.getFormat(), audioBytes, 0, totalFramesRead);
//		    clip.start();
//		  } catch (Exception ex) { 
//		    ex.printStackTrace();
//		  }
//		} catch (Exception e) {
//		  e.printStackTrace();
//		}

		
		
		// UI
		ProceduralGeneration PG = new ProceduralGeneration(100,100);
		PG.Generate();
		PG.findBounds();
		
		try {
			File iconFile = new File("ICON_ROSE.gif");
			icon = ImageIO.read(iconFile);
			File spriteFile = new File("Player1.png");
			sprite = ImageIO.read(spriteFile);
			pc = new PlayerCharacter((PG.yStart-PG.boundsX1), (PG.yStart-PG.boundsY1), sprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JFrame window = new JFrame("Blade of Roses");
		window.setIconImage(icon);
		
		
		
		TestArea ta = new TestArea(14);
//		int size = 14 * 64;
		
		MapCanvas mapCanvas = new MapCanvas();
		mapCanvas.setPreferredSize(new Dimension(800, 600));
		mapCanvas.setLocation(200,200);
		mapCanvas.loadTileset("TestTileSet.png");
//		mapCanvas.loadMap(ta.getTiles());
		mapCanvas.loadMap(PG.convertOut());
		mapCanvas.addCharacter(pc);
		mapCanvas.setBackground(Color.BLACK);
		
		/**************************** TEST **************************************/
		final BufferedImage impImage;
		try {
			File file = new File("Imp001.png");
			impImage = ImageIO.read(file);
			Random r = new Random();
			MonsterCharacter imp = new MonsterCharacter(r.nextInt(12) + 1, r.nextInt(12) + 1, impImage);
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
