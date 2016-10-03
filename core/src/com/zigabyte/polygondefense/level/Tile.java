package com.zigabyte.polygondefense.level;

import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Tile extends Entity {

	public enum State {
		BLOCKED, FREE, TAKEN;
	}

	public Node node;

	private Level level;

	public State state;

	public Tile(Level level, Vector2i pos) {
		super(level, pos.toVector2f());

		this.level = level;

		state = State.FREE;

		node = new Node(level, new Vector2f(getCenter()));
	}

	public Vector2f getCenter() {
		return new Vector2f(pos.x * level.TILE_WIDTH + level.TILE_WIDTH / 2,
				pos.y * level.TILE_HEIGHT + level.TILE_HEIGHT / 2);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Render render) {

		// Draw the inactive if the controller is trying to place something
		if (level.controller.state == Controller.State.ACTIVE) {
			Vector2f center = getCenter();
			int width = level.TILE_WIDTH;
			int height = level.TILE_HEIGHT;

			// Set the color of the tile depending if the user can place on top or not
			if (state == State.FREE) {
				render.shapeRenderer.setColor(0, 1, 0, 0.2f);
			} else if (state == State.TAKEN || state == State.BLOCKED) {
				render.shapeRenderer.setColor(1, 0, 0, 0.2f);
			}

			render.shapeRenderer.rect(center.x - width / 2, center.y - height / 2, width, height);
		}
	}

}
