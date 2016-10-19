package com.zigabyte.polygondefense.level;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Tile extends Entity {

	/**
	 * FREE - no wall - enemies can move through.
	 * WALL - a tower can be placed on top, no entities movement.
	 * TAKEN - a tile with a wall and a tower. No entities movement allowed.
	 * BLOCKED - a wall on which a tower cannot be placed.
	 * */
	public enum State {
		BLOCKED, FREE, WALL, TAKEN;
	}

	public Node node;

	private Level level;

	public State state;

	public Vector2i posI;
	public int cost;

	public Tile(Level level, Vector2i pos) {
		super(level, pos.toVector2f());
		this.posI = pos;

		this.level = level;

		state = State.FREE;

		node = new Node(level, new Vector2f(getCenter()));

		cost = 500;
	}

	public Vector2f getCenter() {
		float x = level.X_PADDING_LEFT + (pos.x * level.TILE_WIDTH + level.TILE_WIDTH / 2);
		float y = level.Y_PADDING_BOTTOM + (pos.y * level.TILE_HEIGHT + level.TILE_HEIGHT / 2);
		return new Vector2f(x, y);
	}

	@Override
	public void update() {

	}

	public void renderRect(Render render) {
		Vector2f center = getCenter();
		float width = level.TILE_WIDTH;
		float height = level.TILE_HEIGHT;

		render.shapeRenderer.rect(center.x - width / 2, center.y - height / 2, width, height);
	}

	@Override
	public void render(Render render) {

		// Render the wall
		if (state == State.WALL || state == State.TAKEN) {
			render.shapeRenderer.setColor(0, 0, 0, 0.2f);
			renderRect(render);
		} else if (state == State.BLOCKED) {
			render.shapeRenderer.setColor(0, 0, 0, 0.75f);
			renderRect(render);
		}

		// Draw the inactive if the controller is trying to place something
		if (level.controller.state == Controller.State.ACTIVE) {

			render.shapeRenderer.setColor(1, 0, 0, 0.2f);
			/*if (state == State.TAKEN || state == State.BLOCKED) {
			}else*/
			if (state == State.WALL || state == State.FREE) {

				render.shapeRenderer.setColor(1, 0, 0, 0.2f);

				switch (level.controller.mode) {
				case WALL:
					if (state == State.FREE) {
						render.shapeRenderer.setColor(0, 1, 0, 0.2f);
					}
					break;
				default:
					if (state == State.WALL)
						render.shapeRenderer.setColor(0, 1, 0, 0.2f);
					break;
				}
			}
			renderRect(render);
			//render.shapeRenderer.rect(center.x - width / 2, center.y - height / 2, width, height);
		}
		// Debug only below - path finding
		/*else {
			render.shapeRenderer.setColor(new Color((cost * 14)));
			renderRect(render);
		}*/
	}

	public int getXI() {
		return posI.x;
	}

	public int getYI() {
		return posI.y;
	}
}
