package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class ButtonWall extends Button {

	public ButtonWall(Level level, Vector2f pos, Vector2f size) {
		super(level, pos, size);
		color = new Color(0.25f, 0.25f, 0.25f, 0.5f);
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.WALL;
	}

}
