package com.zigabyte.polygondefense.entities.ui.top;

import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class ButtonSquare extends Button {

	public ButtonSquare(Level level, Vector2f pos, Vector2f size) {
		super(level, pos, size);
		activateController = true;
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.SQUARE;
	}

}
