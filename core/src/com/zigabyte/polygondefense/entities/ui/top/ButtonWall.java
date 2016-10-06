package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;

public class ButtonWall extends Button {

	public ButtonWall(Level level) {
		super(level);

		color = new Color(0.25f, 0.25f, 0.25f, 0.5f);

		offset = 320 * 4;
		rect.v0.x += offset;
		rect.v1.x += offset;
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.WALL;
	}

}
