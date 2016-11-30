package com.zigabyte.polygondefense.entities.ui;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.graphics.SpriteLoader;
import com.zigabyte.polygondefense.level.Level;

public class MenuBarBottom extends UIElement {

	private int w = 900;
	private int h = 70;

	public MenuBarBottom(Level level) {
		super(level);
	}

	public boolean processInput() {
		return false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {
		int xo = (1600 - w) / 2;
		render.drawTexture(SpriteLoader.getTest(), xo, 0, w, h);
		
		render.drawText("50", Color.WHITE, xo + 200, 55);
	}
}
