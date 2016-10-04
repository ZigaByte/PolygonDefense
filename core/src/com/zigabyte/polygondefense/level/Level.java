package com.zigabyte.polygondefense.level;

import java.util.ArrayList;

import com.zigabyte.polygondefense.Game;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.entities.ui.MenuBarBottom;
import com.zigabyte.polygondefense.entities.ui.UIElement;
import com.zigabyte.polygondefense.entities.ui.top.ButtonHexagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonPentagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonSquare;
import com.zigabyte.polygondefense.entities.ui.top.ButtonTriangle;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller;
import com.zigabyte.polygondefense.input.Input;
import com.zigabyte.polygondefense.level.Tile.State;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Level {

	private Game game;
	public Controller controller;

	public final int TILE_WIDTH;
	public final int TILE_HEIGHT;
	public final int TILES_X;
	public final int TILES_Y;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<UIElement> ui = new ArrayList<UIElement>();
	private ArrayList<Node> nodes = new ArrayList<Node>();

	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Level(Game game) {
		this.game = game;

		controller = new Controller(this);

		// addEntity(new Tower(this, new Vector2f(200, 200)));
		addEntity(new Mob(this, new Vector2f(-100, 100)));

		ui.add(new MenuBarBottom(this));
		ui.add(new ButtonTriangle(this));
		ui.add(new ButtonSquare(this));
		ui.add(new ButtonPentagon(this));
		ui.add(new ButtonHexagon(this));

		/*
		 * nodes.add(new Tile(new Vector2i(5, 5))); nodes.add(new Tile(new Vector2i(6, 6))); nodes.add(new Tile(new Vector2i(2, 8))); nodes.add(new
		 * Tile(new Vector2i(7, 3)));
		 */

		TILES_X = (int) (16 * 1.5f);
		TILES_Y = (int) (9 * 1.5f);

		TILE_WIDTH = Render.WIDTH / TILES_X;
		TILE_HEIGHT = Render.HEIGHT / TILES_Y;

		for (int x = 0; x < TILES_X; x++) {
			for (int y = 0; y < TILES_Y; y++) {
				tiles.add(new Tile(this, new Vector2i(x, y)));

				if (y == 0 || y == TILES_Y - 1) {
					getTile(x, y).state = State.BLOCKED;
				}
			}
		}

		nodes.add(getTile(5, 5).node);
		nodes.add(getTile(4, 4).node);
		nodes.add(getTile(8, 2).node);
		nodes.add(getTile(12, 5).node);
		nodes.add(getTile(2, 2).node);
		nodes.add(getTile(5, 5).node);
		nodes.add(new Node(this, new Vector2f(1000000, 5)));
	}

	private void updateEntities() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}

	/**
	 * Pass the input to different parts of the game and see if they accept it
	 */
	private void processInput() {
		if (Input.ready()) {
			Vector2f input = Input.inputs.get(0).getPos();
			Input.inputs.remove(0);

			// Send input to ui first
			for (int i = 0; i < ui.size(); i++) {
				if (ui.get(i).processInput(input)) {
					return;
				}
			}

			// Controller second
			if (controller.processInput(input)) {
				return;
			}
		}
	}

	/**
	 * Update all the entities and process input
	 */
	public void update() {
		processInput();

		updateEntities();
	}

	private void renderEntities(Render render) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(render);
		}
	}

	private void renderNodes(Render render) {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).render(render);
		}
	}

	private void renderUI(Render render) {
		for (int i = 0; i < ui.size(); i++) {
			ui.get(i).render(render);
		}
	}

	private void renderTiles(Render render) {
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).render(render);
		}
	}

	/**
	 * Render the level.
	 */
	public void render(Render render) {

		for (int i = 0; i < TILES_X; i++) {
			if (i < TILES_Y)
				render.drawLine(0, TILE_HEIGHT * i, 1600, TILE_HEIGHT * i);
			render.drawLine(TILE_WIDTH * i, 0, TILE_WIDTH * i, 900);
		}

		renderTiles(render);
		renderEntities(render);
		renderNodes(render);
		renderUI(render);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * Gets tile from world coordinates
	 */
	public Tile getTile(float x, float y) {
		return getTile((int) (x / TILE_WIDTH), (int) (y / TILE_HEIGHT));
	}

	public Tile getTile(Vector2f v) {
		return getTile(v.x, v.y);
	}

	/**
	 * Gets tile by tile index
	 */
	public Tile getTile(int x, int y) {
		return tiles.get(x * TILES_Y + y);
	}

	public Tile getTile(Vector2i v) {
		return getTile(v.x, v.y);
	}

	public Node getNode(int i) {
		return nodes.get(i);
	}
}
