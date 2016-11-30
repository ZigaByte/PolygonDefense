package com.zigabyte.polygondefense.entities.mobs;

import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Mob2 extends Mob {

	public Mob2(Level level, Vector2f pos) {
		super(level, pos);
		p = new Polygon(4, 20);

		MAX_HEALTH = 100;
		health = MAX_HEALTH;
	}

	protected void move(float deltaTime) {
		Vector2f direction = node.pos.sub(this.pos).normal();
		pos = pos.add(direction.mul(movementSpeed * deltaTime));

		rotation += 1.5f * deltaTime;
	}
}
