package com.zigabyte.polygondefense.level;

import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Tile {

	public Vector2i pos;
	public Node node;

	private Level level;

	public Tile(Level level, Vector2i pos) {
		this.level = level;
		this.pos = pos;

		node = new Node(level, new Vector2f(getCenter()));
	}

	public Vector2f getCenter() {
		return new Vector2f(pos.x * level.TILE_WIDTH + level.TILE_WIDTH / 2,
				pos.y * level.TILE_HEIGHT + level.TILE_HEIGHT / 2);
	}

}
