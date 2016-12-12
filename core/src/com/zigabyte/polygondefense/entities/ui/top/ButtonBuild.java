package com.zigabyte.polygondefense.entities.ui.top;

import com.badlogic.gdx.graphics.Color;
import com.zigabyte.polygondefense.entities.ui.DockTop;
import com.zigabyte.polygondefense.graphics.Polygon;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class ButtonBuild extends Button {

	private DockTop dock;

	public ButtonBuild(Level level, DockTop dock, Vector2f pos, Vector2f size) {
		super(level, pos, size);
		this.dock = dock;

		float step = size.y / 2;
		float[] v = { 0, 0, size.x - step, 0, size.x, step, size.x - step, size.y, 0, size.x };
		background = new Polygon(v);
		color = Color.BLACK;
	}

	@Override
	public void pressed() {
		dock.toggle();
		System.out.println("Toggled");
	}

}
