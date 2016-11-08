package com.zigabyte.polygondefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.PauseableThread;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.graphics.SpriteLoader;
import com.zigabyte.polygondefense.input.Input;
import com.zigabyte.polygondefense.level.Level;

public class Game extends ApplicationAdapter implements Runnable {

	private Level level;

	private Render render;

	private PauseableThread thread;
	private boolean running;
	private int ups = 0;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input());

		render = new Render();
		SpriteLoader.loadSprites();

		level = new Level(this);

		// Start the constant updates
		thread = new PauseableThread(this);
		running = true;
		thread.start();
	}

	public void update() {
		level.update();
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int u = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			while (delta >= 1) {
				delta--;
				u++;
				update();
			}
			if (System.currentTimeMillis() - timer > 1000) {
				ups = u;
				u = 0;
				timer += 1000;
			}
		}
	}

	@Override
	public void render() {
		// Dirty temporary reset feature
		if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.O)) {
			level = new Level(this);
		}

		render.begin();

		level.render(render);

		render.end();
	}

	@Override
	public void dispose() {
		render.dispose();

		thread.stopThread();
	}
}
