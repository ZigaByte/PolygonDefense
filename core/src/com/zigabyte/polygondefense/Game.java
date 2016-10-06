package com.zigabyte.polygondefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.graphics.SpriteLoader;
import com.zigabyte.polygondefense.input.Input;
import com.zigabyte.polygondefense.level.Level;

public class Game extends ApplicationAdapter {

	private Level level;

	private Render render;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input());

		render = new Render();
		SpriteLoader.loadSprites();

		level = new Level(this);
	}

	public void update() {
		level.update();

	}

	@Override
	public void render() {
		// Dirty temporary reset feature
		if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.R)) {
			level = new Level(this);
		}

		render.begin();

		update();

		level.render(render);

		render.end();
	}

	@Override
	public void dispose() {
		render.dispose();
	}
}
