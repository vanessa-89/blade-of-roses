package com.fetch.bor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fetch.bor.bor.BORCharacter;
import com.fetch.bor.bor.MonsterCharacter;
import com.fetch.bor.bor.PlayerCharacter;

public class Game extends Canvas {

	/* The strategy that allows us to use accelerated page flipping */
	private BufferStrategy strategy;
	/* The state of the game */
	private boolean gameRunning = true;
	/* The list of all the characters that exist in our game */
	private ArrayList<BORCharacter> characters = new ArrayList<BORCharacter>();
	
	
	private boolean waitingForKeyPress = true;
	private PlayerCharacter pc;
	
	public boolean leftPressed = false;
	public boolean rightPressed = false;
	public boolean spacePressed = false;
	public boolean upPressed = false;
	public boolean downPressed = false;

	
	public Game() {
		
		// create window
		JFrame window = new JFrame("Blade of Roses");
		
		// get and set content area
		JPanel contentPanel = (JPanel) window.getContentPane();
		contentPanel.setPreferredSize(new Dimension(800,600));
		contentPanel.setLayout(null);
		
		setBounds(0, 0, 800, 600);
		contentPanel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		window.setLocation(500, 50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up logging
		try {
			System.setOut(new PrintStream("BORLog.txt"));
			System.out.println("Hello Log");
		} catch (FileNotFoundException e) {
			// Do nothing as logging has failed
		}
		
		
		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		addKeyListener(new KeyInputHandler());
		
		initCharacters();
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game g = new Game();
		
		g.gameLoop();
	}
	
	private void initCharacters() {
		// create the player
		pc = new PlayerCharacter(this, "Player1.png", 2, 2);
		characters.add(pc);
		
		// create a block of aliens (5 rows, by 12 aliens, spaced evenly)
		int alienCount = 0;
		for (int row = 0; row < 5; row++) {
			for (int x = 0; x < 12; x++) {
				BORCharacter alien = new MonsterCharacter(this, "Imp001.png", x, row);
				characters.add(alien);
				alienCount++;
			}
		}

	}
	
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
					
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			
			// cycle round asking each entity to move itself
			if (!waitingForKeyPress) {
				for (int i = 0; i < characters.size(); i++) {
					BORCharacter character = characters.get(i);

					character.move(delta);
				}
			}

			// cycle round drawing all the entities we have in the game
			for (int i = 0; i < characters.size(); i++) {
				BORCharacter character = characters.get(i);

				character.draw(g);
			}


			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();
			
//			if ((leftPressed) && (!rightPressed)) {
//				pc.moveWest();
//			} else if ((rightPressed) && (!leftPressed)) {
//				pc.moveEast();
//			}
//			if ((upPressed) && (!downPressed)) {
//				pc.moveNorth();
//			} else if ((downPressed) && (!upPressed)) {
//				pc.moveSouth();
//			}

			// finally pause for a bit. Note: this should run us at about
			// 60 fps
			try { Thread.sleep(30); } catch (Exception e) {}
			
		}
	}
	

	private class KeyInputHandler extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = true;
			}
			
		} 
			
			
		public void keyReleased(KeyEvent e) {			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
				pc.moveNorth();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
				pc.moveSouth();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
				pc.moveWest();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
				pc.moveEast();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = false;
			}
		}

		public void keyTyped(KeyEvent e) {
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}

}
