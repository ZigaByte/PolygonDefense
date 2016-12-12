package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

	protected Vector2f size;

	protected boolean hidden = false;
	protected boolean activateController = false;

	public Button(Level level, Vector2f pos, Vector2f size) {
		super(level);
		this.pos = pos;
		this.size = size;

		float width = size.x;
		float height = size.y;
		float v[] = { 0, 0, width, 0, width, height, 0, height };
		background = new Polygon(v);
		color = new Color(0x111111ff);

		rect = new Rectangle(new Vector2f(pos.x, pos.y), new Vector2f(pos.x + width, pos.y + height));
	}

	public abstract void pressed();

	@Override
	public boolean processInput(Vector2f input) {
		if (hidden)
			return false;

		boolean process = rect.isInside(input);

		if (process) {
			pressed();

			if (activateController)
				level.controller.state = State.ACTIVE;
		}
		return process;
	}

	@Override
	public void update() {
		// HOTKEYS
		if (this instanceof ButtonTriangle)
			if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
				level.controller.state = State.ACTIVE;
				pressed();
			}
		if (this instanceof ButtonSquare)
			if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
				level.controller.state = State.ACTIVE;
				pressed();
			}
		if (this instanceof ButtonPentagon)
			if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
				level.controller.state = State.ACTIVE;
				pressed();
			}
		if (this instanceof ButtonHexagon)
			if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
				level.controller.state = State.ACTIVE;
				pressed();
			}
		if (this instanceof ButtonWall)
			if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
				pressed();
				level.controller.state = State.ACTIVE;
			}
	}

	@Override
	public void render(Render render) {
		if (hidden)
			return;
		render.drawPolygon(background, color, pos.x, 800);
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
