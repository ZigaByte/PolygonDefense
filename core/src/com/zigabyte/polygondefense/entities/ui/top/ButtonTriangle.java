package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;

public class ButtonTriangle extends Button {

	public ButtonTriangle(Level level) {
		super(level);

		color = new Color(0.75f, 0.25f, 0.25f, 0.5f);
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.TRIANGLE;
	}

}
