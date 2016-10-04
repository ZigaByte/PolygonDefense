package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;

public class ButtonHexagon extends Button {

	public ButtonHexagon(Level level) {
		super(level);
		offset = 960;
		rect.v0.x += offset;
		rect.v1.x += offset;
		color = new Color(0.5f, 0.5f, 0.5f, 0.5f);

	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.HEXAGON;
	}
}
