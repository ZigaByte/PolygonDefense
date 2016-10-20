package com.zigabyte.polygondefense.entities.mobs;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.projectile.Projectile;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Mob extends Entity {

	protected Polygon p;

	protected Node target;
	protected int nodeCount = 0;

	public boolean dead = false;

	public Mob(Level level, Vector2f pos) {
		super(level, pos);
		p = new Polygon(3, 20);
	}

	public void hit(Projectile p) {
		dead = true;
		level.removeEntity(this);
	}

	private void move() {
		Vector2f direction = target.pos.sub(this.pos).normal();
		pos = pos.add(direction.mul(2));

		// Calculate the rotation of the mob
		rotation = direction.getAngle();
		if (direction.x < 0)
			rotation += 3.14f;
	}

	@Override
	public void update() {
		if (target == null) {
			target = level.getNode(0);
		}

		if (target.getDistance(pos) < 5) {
			nodeCount++;
			//target = level.getNode(nodeCount);
			Random r = new Random();
			target = level.getNode(r.nextInt(5));
			// TODO, THIS WILL CRASH , array out of bounds
		}

		move();
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(p, new Color(0, 0, 0, 0.5f), pos.x + 3, pos.y + 2, rotation);
		render.drawPolygon(p, new Color(1, 1, 1, 1f), pos.x, pos.y, rotation);
	}

}
