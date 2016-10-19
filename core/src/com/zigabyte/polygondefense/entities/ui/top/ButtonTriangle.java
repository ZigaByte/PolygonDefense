package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller.Mode;
import com.zigabyte.polygondefense.level.Level;

public class ButtonTriangle extends Button {

	Polygon p;

	public ButtonTriangle(Level level) {
		super(level);

		color = new Color(0.75f, 0.25f, 0.25f, 0.5f);

		p = new Polygon(3, 30);
	}

	@Override
	public void pressed() {
		level.controller.mode = Mode.TRIANGLE;
	}

	@Override
	public void render(Render render) {
		super.render(render);
		render.drawPolygon(p, Color.BLACK, pos.x + 150, pos.y + 850);
	}

}
