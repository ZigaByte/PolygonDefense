package com.zigabyte.polygondefense.entities.tower;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class Tower extends Entity {

	protected Polygon polygon;
	protected Color color;

	protected Mob target;

	protected float rotation;
	protected boolean active = false;

	public Tower(Level level, Vector2f pos) {
		super(level, pos);

		color = new Color(1, 1, 1, 1);
	}

	private void findTarget() {

		ArrayList<Mob> mobs = level.getMobs();
		// For now TODO
		if (!mobs.isEmpty()) {
			target = mobs.get(0);

			active = true;
		} else {
			active = false;
		}
	}

	private void updateTarget() {
		if (target == null) {
			findTarget();
		} else if (target.dead) {
			findTarget();
		}
	}

	@Override
	public void update() {
		rotation += 0.03f;

		updateTarget();

		if (active) {
			Vector2f direction = pos.sub(target.pos).normal();
			rotation = direction.getAngle();

			if (pos.x > target.pos.x) {
				rotation += 3.14f;
			}

		}
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
