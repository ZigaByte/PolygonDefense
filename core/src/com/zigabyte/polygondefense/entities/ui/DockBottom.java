package com.zigabyte.polygondefense.entities.ui;

import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.level.Level;

public class DockBottom extends Dock{

	public DockBottom(Level level) {
		super(level);
		
		float sizeY = 100;
		float[] v = { 0, 0, 1600, 0, 1600, sizeY, 0, sizeY };
		background = new Polygon(v);
	}

}
