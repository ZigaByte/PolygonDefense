package com.zigabyte.polygondefense.entities.tower;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class Tower extends Entity {

	protected Polygon polygon;
	protected Color color;

	protected float rotation;

	public Tower(Level level, Vector2f pos) {
		super(level, pos);

		color = new Color(1, 1, 1, 1);
	}

	@Override
	public void update() {
		rotation += 0.03f;
	}

	@Override
	public void render(Render render) {
		render.drawPolygon(polygon, new Color(0, 0, 0, 0.5f), pos.x + 9, pos.y + 5, rotation);
		render.drawPolygon(polygon, color, pos.x, pos.y, rotation);
	}

}
