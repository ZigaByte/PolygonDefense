package com.zigabyte.polygondefense.entities.tower;

import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class TowerHexagon extends Tower {

	public TowerHexagon(Level level, Vector2f pos) {
		super(level, pos);
		polygon = new Polygon(6, 20);

	}

}
