package com.fetch.bor.gui;

import java.awt.Graphics2D;

public class UI {

	Sprite baseUI;
	
	public UI() {
		baseUI = SpriteStore.get().getSprite("BORUI.png");
	}
	
	public void draw(Graphics2D g) {
		baseUI.draw(g, 0, 0);
	}
}
