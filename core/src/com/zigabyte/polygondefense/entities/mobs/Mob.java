package com.zigabyte.polygondefense.entities.mobs;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Mob extends Entity {

	protected Polygon p;

	protected Node target;
	protected int nodeCount = 0;

	public Mob(Level level, Vector2f pos) {
		super(level, pos);
		p = new Polygon(3, 20);
	}

	private void move() {
		Vector2f direction = target.pos.sub(this.pos).normal();
		pos = pos.add(direction.mul(5));
	}

	@Override
	public void update() {
		if (target == null) {
			target = level.getNode(0);
		}

		if (target.getDistance(pos) < 10) {
			nodeCount++;
			target = level.getNode(nodeCount);

			// TODO, THIS WILL CRASH , array out of bounds
		}

		move();
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(p, new Color(0, 0, 0, 0.5f), pos.x + 3, pos.y + 2, 0);
		render.drawPolygon(p, new Color(1, 1, 1, 1f), pos.x, pos.y);
	}

}
