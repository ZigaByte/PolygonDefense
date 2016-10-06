package com.zigabyte.polygondefense.input;

import com.zigabyte.polygondefense.entities.tower.Tower;
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

	private void addWall(Tile tile) {
		if (tile == null)
			return;

		if (tile.state == Tile.State.FREE) {
			tile.state = Tile.State.WALL;
		}
	}

	private void addTower(Tile tile, Tower towerTriangle) {
		if (tile == null)
			return;

		if (tile.state == Tile.State.WALL) {
			level.addEntity(towerTriangle);
			tile.state = Tile.State.TAKEN;
		}
	}

	public boolean processInput(Vector2f input) {
		if (state == State.ACTIVE) {
			Tile tile = level.getTile(input);

			this.state = State.IDLE;

			if (tile == null) {
				// You clicked past the grid!
				return true;
			}
			switch (mode) {
			case TRIANGLE:
				addTower(tile, new TowerTriangle(level, tile.getCenter()));
				break;
			case SQUARE:
				addTower(tile, new TowerSquare(level, tile.getCenter()));
				break;
			case PENTAGON:
				addTower(tile, new TowerPentagon(level, tile.getCenter()));
				break;
			case HEXAGON:
				addTower(tile, new TowerHexagon(level, tile.getCenter()));
				break;
			case WALL:
				addWall(tile);
				break;
			}

			// Once something was placed, set the controller state to idle

			return true;
		}
		return false;
	}

}
