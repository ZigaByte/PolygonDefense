package com.zigabyte.polygondefense.entities.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Dock extends UIElement {

	protected ArrayList<UIElement> parts = new ArrayList<UIElement>();
	protected Polygon background;

	public Dock(Level level) {
		super(level);
	}

	@Override
	public boolean processInput(Vector2f input) {
		boolean processed = false;
		for (UIElement e : parts) {
			processed = e.processInput(input);
			if (processed)
				return true;
		}
		return false;
	}

	@Override
	public void update() {
		for (UIElement e : parts)
			e.update();
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(background, new Color(0x252525ff), 0, 0);
		for (UIElement e : parts)
			e.render(render);
	}

}
