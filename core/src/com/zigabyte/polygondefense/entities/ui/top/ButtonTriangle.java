package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class ButtonTriangle extends Button {

	public ButtonTriangle(Level level, Vector2f pos, Vector2f size) {
		super(level, pos, size);
		activateController = true;
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.TRIANGLE;
	}

	@Override
	public void render(Render render) {
		super.render(render);
	}

}
