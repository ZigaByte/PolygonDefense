package com.zigabyte.polygondefense.entities.ui;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;

public class MenuBarBottom extends UIElement{
	private Polygon background;
	private Polygon useless;

	public MenuBarBottom(Level level) {
		super(level);

		float v[] = { 1600, 0, 1600, 150, 1250, 150, 1200, 100, 0, 100, 0, 0 };

		float v2[] = { -100, 0, 1170, 0, 1200, 30, -100, 30 };

		background = new Polygon(v);
		useless = new Polygon(v2);
	}

	public boolean processInput() {
		return false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {
		render.drawPolygon(background, new Color(0.2f, 0.2f, 0.2f, 0.8f), 0, 0);
		render.drawPolygon(useless, new Color(0.5f, 0.5f, 0.5f, 0.8f), 20, 115);
	}
}
