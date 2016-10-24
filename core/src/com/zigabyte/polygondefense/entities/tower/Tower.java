package com.zigabyte.polygondefense.entities.tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.entities.projectile.Projectile;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class Tower extends Entity {

	protected final int SIZE = 40;

	protected Polygon polygon;
	protected Color color;

	protected Mob target;

	// Variables that define the traits of the tower
	private float damage = 15;
	private float attackSpeed = 1; // Time between shots.
	private float range = 500;
	private boolean attackAll = false;

	// Variables regarding the state of the tower
	protected boolean active = false;
	private float shootTimer = 0;

	public Tower(Level level, Vector2f pos) {
		super(level, pos);

		color = new Color(1, 1, 1, 1);
	}

	private void findTarget() {
		ArrayList<Mob> mobs = level.getMobsInRange(pos, range);
		// For now TODO
		if (!mobs.isEmpty()) {
			// Sort the array by distance
			Collections.sort(mobs, new Comparator<Mob>() {
				public int compare(Mob m1, Mob m2) {
					float d1 = m1.pos.distanceSquared(pos);
					float d2 = m2.pos.distanceSquared(pos);
					return Double.compare(d1, d2);
				};
			});

			target = mobs.get(0);
			active = true;
		} else {
			active = false;
		}
	}

	private void updateTarget() {
		if (target == null) {
			findTarget();
		}
		if (target != null)
			if (target.dead) {
				findTarget();
			}
	}

	private void updatePosition() {
		if (active) {
			Vector2f direction = pos.sub(target.pos).normal();
			rotation = direction.getAngle();

			if (pos.x > target.pos.x) {
				rotation += 3.14f;
			}
		}
	}

	private void shootAt(Mob m) {
		level.addEntity(new Projectile(level, this.pos, target, damage));
	}

	private void updateShooting() {
		if (shootTimer >= 0) {
			shootTimer -= Gdx.graphics.getDeltaTime();
		}

		if (active) {
			if (shootTimer < 0) {
				// Reset the timer and shoot at the target
				shootTimer = attackSpeed;

				if (attackAll) {
					ArrayList<Mob> mobsInRange = level.getMobsInRange(pos, range);
					for (Mob b : mobsInRange) {
						shootAt(b);
					}
				} else {
					shootAt(target);
				}
			}
		}
	}

	@Override
	public void update() {
		updateTarget();

		updatePosition();

		updateShooting();
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(polygon, new Color(0, 0, 0, 0.5f), pos.x + 9, pos.y + 5, rotation);
		render.drawPolygon(polygon, color, pos.x, pos.y, rotation);

		if (active) {
			render.shapeRenderer.setColor(0, 0, 0, 1);
			render.shapeRenderer.line(pos.x, pos.y, target.pos.x, target.pos.y);
		}
	}

}
