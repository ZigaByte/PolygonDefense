package com.zigabyte.polygondefense.input;

import com.zigabyte.polygondefense.entities.tower.Tower;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.level.Tile;
import com.zigabyte.polygondefense.math.Vector2f;

public class Controller {
	public enum State {
		IDLE, ACTIVE;
	}

	public enum Mode {
		WALL, TRIANGLE, SQUARE, PENTAGON, HEXAGON;
	}

	public State state;

	private Level level;

	public Controller(Level level) {
		this.level = level;
		state = State.IDLE;

	}

	public boolean processInput(Vector2f input) {
		if (state == State.ACTIVE) {
			Tile tile = level.getTile(input);

			// Spawn a new tower if the tile is free
			if (tile.state == Tile.State.FREE) {
				level.addEntity(new Tower(level, tile.getCenter()));
				tile.state = Tile.State.BLOCKED;

				this.state = State.IDLE;
			}

			return true;
		}
		return false;
	}
}
