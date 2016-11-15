package com.zigabyte.polygondefense.entities;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class Node extends Entity {

	private Node next;
	public final boolean finalNode;

	public Node(Level level, Vector2f pos) {
		this(level, pos, false);
	}

	public Node(Level level, Vector2f pos, boolean finalNode) {
		super(level, pos);
		this.finalNode = finalNode;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Render render) {
		render.shapeRenderer.setColor(Color.RED);
		render.beginRenderer(render.shapeRenderer);
		render.shapeRenderer.circle(pos.x, pos.y, 3);
	}

	/**
	 * Return distance to the node from a certain vector
	 */
	public float getDistance(Vector2f v) {
		return pos.sub(v).length();
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getNext() {
		return next;
	}

}
