package com.zigabyte.polygondefense.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Level;
import com.zigabyte.polygondefense.math.Vector2f;

public abstract class Entity {
	
	protected static Random random = new Random();

	protected Level level;

	public Vector2f pos;
	protected float rotation;

	public Entity(Level level) {
		this(level, new Vector2f(0, 0));
	}

	public Entity(Level level, Vector2f pos) {
		this.level = level;
		this.pos = pos;
	}

	public abstract void update();

	public abstract void render(Render render);

}
