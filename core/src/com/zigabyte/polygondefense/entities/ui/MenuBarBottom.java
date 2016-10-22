package com.zigabyte.polygondefense.entities.ui;

import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.graphics.SpriteLoader;
import com.zigabyte.polygondefense.level.Level;

public class MenuBarBottom extends UIElement {
	private Polygon background;

	// Size variables
	private int dx = 50;
	private int w = 900;
	private int h = 70;

	public MenuBarBottom(Level level) {
		super(level);

		float v[] = { 0, 0, w, 0, w - dx, h, dx, h };

		background = new Polygon(v);
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
		//render.drawPolygon(background, new Color(0.2f, 0.2f, 0.2f, 1f), xo, 0);

		render.drawTexture(SpriteLoader.getTest(), xo, 0, w, h);
		
		render.drawText();
	}
}
