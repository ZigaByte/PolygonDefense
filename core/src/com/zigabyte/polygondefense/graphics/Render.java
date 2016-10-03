package com.zigabyte.polygondefense.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Render {

	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;

	public ShapeRenderer shapeRenderer;
	public SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	public Render() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera(WIDTH, HEIGHT);
	}

	public void drawPolygon(Polygon p) {
		drawPolygon(p, Color.WHITE, 0, 0, 0);
	}

	public void drawPolygon(Polygon p, float x, float y) {
		drawPolygon(p, Color.WHITE, x, y, 0);
	}

	public void drawPolygon(Polygon p, Color c, float x, float y) {
		drawPolygon(p, c, x, y, 0);
	}

	/**
	 * @param x
	 *            the x offset. X will be the center of the polygon
	 * @param y
	 *            the y offset. Y will be the center of the polygon
	 * 
	 * @param rotation
	 *            rotation of the polygon in radians
	 * 
	 */
	public void drawPolygon(Polygon p, Color color, float x, float y, float rotation) {
		rotation = rotation * 180 / 3.14f; // Convert to degrees

		shapeRenderer.setColor(color);
		//shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.5f);

		// Draw the individual vertices triangles with vertices

		shapeRenderer.translate(x, y, 0);
		shapeRenderer.rotate(0, 0, 1, rotation);

		float[] v = p.VERTICES;
		for (int i = 0; i < p.VERTICES_COUNT - 2; i++) {
			int t = 2 * (i + 1);
			shapeRenderer.triangle(v[0], v[1], v[t], v[t + 1], v[t + 2], v[t + 3]);
		}

		shapeRenderer.rotate(0, 0, 1, -rotation);
		shapeRenderer.translate(-x, -y, 0);

	}

	public void drawLine(int x0, int y0, int x1, int y1) {
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.line(x0, y0, x1, y1);
	}

	public void begin() {
		shapeRenderer.begin(ShapeType.Filled);
		spriteBatch.begin();

		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		// Draw the background
		float r = 220 / 255f;
		Gdx.gl.glClearColor(r, r, r, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		// Set the origin of the render in the bottom left corner
		shapeRenderer.translate(-WIDTH / 2, -HEIGHT / 2, 0);
	}

	public void end() {
		// Reset the position of the render origin
		shapeRenderer.translate(WIDTH / 2, HEIGHT / 2, 0);
		
		shapeRenderer.end();
		spriteBatch.end();
	
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	public void dispose() {
		shapeRenderer.dispose();
		spriteBatch.disableBlending();
	}

}
