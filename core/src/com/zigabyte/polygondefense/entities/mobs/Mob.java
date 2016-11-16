package com.zigabyte.polygondefense.entities.mobs;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.projectile.Projectile;
import com.zigabyte.polygondefense.entities.tower.Tower;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Mob extends Entity {

	protected Polygon p;
	protected Color color;

	protected Node node;

	protected final float MAX_HEALTH;
	protected float health;
	protected float movementSpeed = 8;

	public boolean dead = false;

	public Mob(Level level, Vector2f pos) {
		super(level, pos);
		p = new Polygon(3, 20);

		MAX_HEALTH = 50;
		health = MAX_HEALTH;

		color = new Color();
		updateColor();

		node = level.start.node;
	}

	/**
	 * Updates the color of the mob. Green is full hp, red is low hp
	 * */
	private void updateColor() {
		float ratio = health / MAX_HEALTH;
		color.set(ratio, 1 - ratio, 0, 1);
	}

	public void hit(Projectile p) {
		health -= p.damage;

		if (health < 0) {
			dead = true;
			level.removeEntity(this);

			// TODO Add money and spawn a text particle

		} else {
			updateColor();
		}
	}

	private void move(float deltaTime) {
		Vector2f direction = node.pos.sub(this.pos).normal();
		pos = pos.add(direction.mul(movementSpeed));

		// Calculate the rotation of the mob
		rotation = direction.getAngle();
		if (direction.x < 10)
			rotation += 3.14f;
	}

	@Override
	public void update(float deltaTime) {
		if (node == null) {
			node = level.getNode(0);
		}

		// If the mob is close enough to the current target node
		if (node.getDistance(pos) < 5) {
			if (!node.finalNode) {
				// Get the next node
				node = node.getNext();
			} else {
				// You are through the maze!
				node = new Node(level, pos.add(200, 0), true);
				
			}
		}

		move(deltaTime);
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(p, new Color(0, 0, 0, 0.5f), pos.x + 3, pos.y + 2, rotation);
		render.drawPolygon(p, color, pos.x, pos.y, rotation);
	}

	public float distance(Tower tower) {
		return tower.pos.distance(this.pos);
	}

}
