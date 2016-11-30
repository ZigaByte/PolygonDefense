package com.zigabyte.polygondefense.entities.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.EntityMovable;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Projectile extends EntityMovable {

	protected Mob target;

	protected float speed; // Per second
	public float damage;

	public Projectile(Level level, Vector2f pos, Mob target, float damage) {
		super(level, pos);
		this.target = target;
		this.damage = damage;

		this.speed = 200;
	}

	/**
	 * Gets called when the projectile hits the enemy
	 * */
	private void hit() {
		level.removeEntity(this);

		target.hit(this);
	}

	/**
	 * Move the projectile towards the target mob and check if it collides.	
	 * */
	protected void move(float deltaTime) {
		Vector2f relative = target.pos.sub(this.pos);
		float distance = relative.length();

		Vector2f direction = relative.normal();
		pos = pos.add(direction.mul(speed * deltaTime));

		// Calculate the rotation of the mob
		rotation = direction.getAngle();
		if (direction.x < 0)
			rotation += 3.14f;
		rotation += 3.14f / 2;

		// Check collision
		if (distance < 50) {
			hit();
		}
	}

	@Override
	public void update() {
		move(Gdx.graphics.getDeltaTime());

		if (target.dead)
			level.removeEntity(this);
	}

	@Override
	public void render(Render render) {
		render.drawCirlce(Color.BLACK, pos.x, pos.y, 5);
	}

}
