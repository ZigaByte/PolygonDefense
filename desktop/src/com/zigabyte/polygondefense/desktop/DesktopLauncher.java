package com.zigabyte.polygondefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zigabyte.polygondefense.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 4;
		config.width = 1000;
		config.height = (int)(config.width / (16f/9f));
		//config.fullscreen = true;
		new LwjglApplication(new Game(), config);
	}
}
