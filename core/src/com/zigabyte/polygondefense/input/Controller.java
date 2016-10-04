package com.zigabyte.polygondefense.input;

import com.zigabyte.polygondefense.entities.tower.TowerHexagon;
import com.zigabyte.polygondefense.entities.tower.TowerPentagon;
import com.zigabyte.polygondefense.entities.tower.TowerSquare;
import com.zigabyte.polygondefense.entities.tower.TowerTriangle;
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
	public Mode mode;

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

				switch (mode) {
				case TRIANGLE:
					level.addEntity(new TowerTriangle(level, tile.getCenter()));
					break;
				case SQUARE:
					level.addEntity(new TowerSquare(level, tile.getCenter()));
					break;
				case PENTAGON:
					level.addEntity(new TowerPentagon(level, tile.getCenter()));
					break;
				case HEXAGON:
					level.addEntity(new TowerHexagon(level, tile.getCenter()));
					break;
				default:
					break;
				}
				tile.state = Tile.State.BLOCKED;

				this.state = State.IDLE;
			}

			return true;
		}
		return false;
	}
}
