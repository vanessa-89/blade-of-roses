package com.fetch.bor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.util.calendar.BaseCalendar.Date;

import com.fetch.bor.Procedural.ProceduralGeneration;
import com.fetch.bor.bor.BORCharacter;
import com.fetch.bor.bor.Map;
import com.fetch.bor.bor.MonsterCharacter;
import com.fetch.bor.bor.PlayerCharacter;
import com.fetch.bor.bor.Tile;

public class Game extends Canvas {

	private static final int DIR_NORTH = 0;
	private static final int DIR_EAST = 1;
	private static final int DIR_SOUTH = 2;
	private static final int DIR_WEST = 3;
	
	/* Game loop constants */
	private static final int MAIN_MENU = 0;
	private static final int IN_GAME = 1;
	private static final int SCORE = 2;
	
	/* The strategy that allows us to use accelerated page flipping */
	private BufferStrategy strategy;
	/* The state of the game */
	private boolean gameRunning = true;
	/* The list of all the characters that exist in our game */
	private ArrayList<BORCharacter> characters = new ArrayList<BORCharacter>();
	
	
	private boolean waitingForKeyPress = true;
	public PlayerCharacter pc;
	
	public boolean leftPressed = false;
	public boolean rightPressed = false;
	public boolean spacePressed = false;
	public boolean upPressed = false;
	public boolean downPressed = false;
	private long lastMove = 0;
	private long turnInterval = 250;
	
	private ProceduralGeneration PG;
	public Map map;
	private UI ui;
	private int gameState;

	
	public Game() {
		
		// create window
		JFrame window = new JFrame("Blade of Roses");
		
		// get and set content area
		JPanel contentPanel = (JPanel) window.getContentPane();
		contentPanel.setLayout(null);
		
		setSize(800, 600);
		contentPanel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		try {
			window.setIconImage(ImageIO.read(new File("Images/ICON_ROSE.gif")));
		} catch (IOException e1) {
			// Do nothing, no icon file
		}
		window.setSize(805, 625);
		window.setResizable(false);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up logging
		try {
			Calendar cal = Calendar.getInstance();
			System.setOut(new PrintStream("BORLog" + cal.get(Calendar.DATE) + "_" + cal.get(Calendar.MONTH) + ".txt"));
			System.out.println("Blade of Roses - Debug Log\n\n" + cal.getTime().toString() + "\n\n");
		} catch (FileNotFoundException e) {
			// Do nothing as logging has failed
		}
		
		
		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		addKeyListener(new KeyInputHandler());
		
		// Map Generation
		PG = new ProceduralGeneration(100,100);
		PG.Generate();
		PG.findBounds();
		
		// Map
		initMap();
		
		// UI
		initUI();
		
		// Characters
		initCharacters();
		
		requestFocus();
		
		gameState = MAIN_MENU;
	}

	/**
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		Game g = new Game();
		
		g.gameLoop();
	}
	
	private void initUI() {
		ui = new UI();
	}

	private void initMap() {
		map = new Map("Images/TestTileSet.png", 6, 5, PG.convertOut());
	}
	
	private void initCharacters() {
		// create the player
		pc = new PlayerCharacter(this, "Player1Sheet.png", 5, 4);
		characters.add(pc);
		
		BORCharacter imp = new MonsterCharacter(this, "Imp001.png", 5, 6);
		characters.add(imp);

	}
	
	void mainGameLoop() {
		while (true) {
			switch(gameState) {
				case MAIN_MENU:
					mainMenu();
					break;
				case IN_GAME:
					gameLoop();
					break;
				case SCORE:
//					highScore();
					break;
				default:
					break;
					
			}
		}
	}
	
	public void mainMenu() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		
		g.dispose();
		strategy.show();
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
			//if (!waitingForKeyPress) {
				for (int i = 0; i < characters.size(); i++) {
					BORCharacter character = characters.get(i);

					character.move(delta);
				}
			//}

			// Draw Map
			map.draw(g);
			// cycle round drawing all the entities we have in the game
			for (int i = 0; i < characters.size(); i++) {
				BORCharacter character = characters.get(i);

				character.draw(g);
			}
			// Draw UI
			ui.draw(g);


			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();
			
			if ((leftPressed) && (!rightPressed)) {
				tryToMove(DIR_WEST);
			} else if ((rightPressed) && (!leftPressed)) {
				tryToMove(DIR_EAST);
			}
			if ((upPressed) && (!downPressed)) {
				tryToMove(DIR_NORTH);
			} else if ((downPressed) && (!upPressed)) {
				tryToMove(DIR_SOUTH);
			}

			// finally pause for a bit. Note: this should run us at about
			// 60 fps
			try { Thread.sleep(30); } catch (Exception e) {}
			
		}
	}
	
	public void tryToMove(int direction) {
		// check that we have waiting long enough to fire
		if (System.currentTimeMillis() - lastMove < turnInterval) {
			return;
		}
		
		// if we waited long enough, create the shot entity, and record the time.
		lastMove = System.currentTimeMillis();
		switch (direction) {
		case DIR_NORTH:
			map.moveNorth();
			pc.moveNorth();
			break;
		case DIR_EAST:
			map.moveEast();
			pc.moveEast();
			break;
		case DIR_SOUTH:
			map.moveSouth();
			pc.moveSouth();
			break;
		case DIR_WEST:
			map.moveWest();
			pc.moveWest();
			break;
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
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
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
