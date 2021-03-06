package com.zigabyte.polygondefense.entities.mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.EntityMovable;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.projectile.Projectile;
import com.zigabyte.polygondefense.entities.tower.Tower;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Mob extends EntityMovable {

	protected Polygon p;
	protected Color color;

	protected Node node;

	protected float MAX_HEALTH;
	protected float health;
	protected float movementSpeed = 100; // Per second

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
		color.set(1-ratio, ratio, 0, 1);
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

	protected void move(float deltaTime) {
		Vector2f direction = node.pos.sub(this.pos).normal();
		pos = pos.add(direction.mul(movementSpeed * deltaTime));

		// Calculate the rotation of the mob
		rotation = direction.getAngle();
		if (direction.x < 10)
			rotation += 3.14f;
	}

	@Override
	public void update() {
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

		move(Gdx.graphics.getDeltaTime());
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
