package com.zigabyte.polygondefense.entities;

import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class EntityMovable extends Entity{

	public EntityMovable(Level level) {
		super(level);
	}

	public EntityMovable(Level level, Vector2f pos) {
		super(level, pos);
	}

	protected abstract void move(float deltaTime);
}
