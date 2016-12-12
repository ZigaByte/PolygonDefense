package com.zigabyte.polygondefense.entities.ui;

import com.zigabyte.polygondefense.entities.ui.top.Button;
import com.zigabyte.polygondefense.entities.ui.top.ButtonBuild;
import com.zigabyte.polygondefense.entities.ui.top.ButtonHexagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonPentagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonSquare;
import com.zigabyte.polygondefense.entities.ui.top.ButtonTriangle;
import com.zigabyte.polygondefense.entities.ui.top.ButtonWall;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public class DockTop extends Dock {

	private boolean hidden = false; // The hidden buttons

	public DockTop(Level level) {
		super(level);

		int sizeX = 210, sizeY = 100, i = 0, offset = 150;
		parts.add(new ButtonSquare(level, new Vector2f(offset + i++ * sizeX, 900 - sizeY), new Vector2f(sizeX, sizeY)));
		parts.add(new ButtonTriangle(level, new Vector2f(offset + i++ * sizeX, 900 - sizeY), new Vector2f(sizeX, sizeY)));
		parts.add(new ButtonPentagon(level, new Vector2f(offset + i++ * sizeX, 900 - sizeY), new Vector2f(sizeX, sizeY)));
		parts.add(new ButtonHexagon(level, new Vector2f(offset + i++ * sizeX, 900 - sizeY), new Vector2f(sizeX, sizeY)));
		parts.add(new ButtonWall(level, new Vector2f(offset + i++ * sizeX, 900 - sizeY), new Vector2f(sizeX, sizeY)));

		parts.add(new ButtonBuild(level, this, new Vector2f(0, 900 - sizeY), new Vector2f(offset, sizeY)));

	}

	/**
	 * Toggle the visibility of the buttons in the top dock. This should later animate it all
	 * */
	public void toggle() {
		hidden = !hidden;
		for (UIElement p : parts) {
			if (p instanceof Button && !(p instanceof ButtonBuild)) {
				Button b = (Button) p;
				b.setHidden(hidden);
			}
		}
	}

}
