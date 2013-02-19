package com.fetch.bor.gui;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteStore {
	/** The single instance of this class */
	private static SpriteStore single = new SpriteStore();
	
	/**
	 * Get the single instance of this class 
	 * 
	 * @return The single instance of this class
	 */
	public static SpriteStore get() {
		return single;
	}
	
	/** The cached sprite map, from reference to sprite instance */
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	/**
	 * Retrieve a sprite from the store
	 * 
	 * @param ref The reference to the image to use for the sprite
	 * @return A sprite instance containing an accelerate image of the request reference
	 */
	public Sprite getSprite(String ref) {
		// if we've already got the sprite in the cache
		// then just return the existing version
		if (sprites.get(ref) != null) {
			return (Sprite) sprites.get(ref);
		}
		
		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;
		
		try {
			File file = new File("sprites/" + ref);
			
			// use ImageIO to read the image in
			sourceImage = ImageIO.read(file);
		} catch (IOException e) {
			fail("Failed to load: " + ref);
		}
		
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
		
		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		
		// create a sprite, add it the cache then return it
		Sprite sprite = new Sprite(image);
		sprites.put(ref, sprite);
		
		return sprite;
	}
	
	public Sprite getSprite(String ref, int width, int height, int x, int y) {
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(width, height, Transparency.BITMASK);
		
		// draw our source image into the accelerated image
		image.getGraphics().drawImage(getSprite(ref).getImage(), 0, 0, width, height, x, y, x + width, y + height, null);
		
		// create a sprite, add it the cache then return it
		Sprite sprite = new Sprite(image);
		
		return sprite;
	}
	
	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message The message to display on failure
	 */
	private void fail(String message) {
		// wasn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}
}
