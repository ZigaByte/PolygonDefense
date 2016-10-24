package com.zigabyte.polygondefense.entities.projectile;

import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.graphics.SpriteLoader;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Projectile extends Entity {

	protected Mob target;

	private float speed;
	public float damage;

	public Projectile(Level level, Vector2f pos, Mob target, float damage) {
		super(level, pos);
		this.target = target;
		this.damage = damage;

		this.speed = 5;
	}

	/**
	 * Gets called when the projectile hits the enemy
	 * */
	private void hit() {
		level.removeEntity(this);

		target.hit(this);
	}

	/**
	 * Move the projectile towards the target mob.
	 * */
	private void updatePosition() {
		Vector2f relative = target.pos.sub(this.pos);
		float distance = relative.length();

		Vector2f direction = relative.normal();
		pos = pos.add(direction.mul(speed));

		// Calculate the rotation of the mob
		rotation = direction.getAngle();
		if (direction.x < 0)
			rotation += 3.14f;
		rotation += 3.14f / 2;

		if (distance < 50) {
			hit();
		}
	}

	@Override
	public void update() {
		updatePosition();

		if (target.dead)
			level.removeEntity(this);
	}

	@Override
	public void render(Render render) {
		render.drawTexture(SpriteLoader.getTest(), pos.x - 5, pos.y - 5, 20, 30, rotation);
	}

}
