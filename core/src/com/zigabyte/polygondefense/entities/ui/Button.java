package com.zigabyte.polygondefense.entities.ui;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller.State;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Rectangle;
import com.zigabyte.polygondefense.math.Vector2f;

public class Button extends UIElement {
	private Rectangle rect;
	private Polygon background;

	public Button(Level level) {
		super(level);

		float v[] = { 0, 0, 300, 0, 300, 120, 0, 120 };
		background = new Polygon(v);

		rect = new Rectangle(new Vector2f(100, 780), new Vector2f(100 + 300, 780 + 120));
	}

	@Override
	public boolean processInput(Vector2f input) {
		boolean process = rect.isInside(input);

		if (process) {
			level.controller.state = State.ACTIVE;
		}
		// System.out.println("Input detected > " + input.x + " " + input.y);
		// System.out.println(rect.isInside(input));
		return process;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {
		render.drawPolygon(background, Color.CORAL, 100, 780);
	}

}
