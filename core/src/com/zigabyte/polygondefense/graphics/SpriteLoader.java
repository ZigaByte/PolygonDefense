package com.zigabyte.polygondefense.graphics;

import com.badlogic.gdx.graphics.Texture;

public class SpriteLoader {

	public static final String FOLDER = "textures/";

	private static Texture test;

	public static void loadSprites() {
		test = new Texture(FOLDER + "menu_bottom.png");
	}

	public static Texture getTest() {
		return test;
	}
}
