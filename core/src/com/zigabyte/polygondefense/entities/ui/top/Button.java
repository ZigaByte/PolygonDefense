package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.ui.UIElement;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller.State;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Rectangle;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class Button extends UIElement {
	protected Rectangle rect;
	protected Polygon background;
	protected Color color;

	protected int offset = 0;

	public Button(Level level) {
		super(level);

		int width = 288;
		int height = 100;
		float v[] = { 0, 0, width, 0, width, height, 0, height };
		background = new Polygon(v);
		color = Color.BLACK;

		rect = new Rectangle(new Vector2f(100, 900 -height), new Vector2f(100 + width, 900));
	}

	public abstract void pressed();

	@Override
	public boolean processInput(Vector2f input) {
		boolean process = rect.isInside(input);

		if (process) {
			level.controller.state = State.ACTIVE;
			pressed();
		}

		return process;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {
		render.drawPolygon(background, color, 100 + offset, 800);
	}

}
