package com.zigabyte.polygondefense.entities.ui;

import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class UIElement extends Entity {

	public UIElement(Level level) {
		super(level);
	}

	public boolean processInput(Vector2f input) {
		return false;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {
	}

}
