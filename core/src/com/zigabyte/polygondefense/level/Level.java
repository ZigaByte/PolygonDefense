package com.zigabyte.polygondefense.level;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.utils.Queue;
import com.zigabyte.polygondefense.Game;
import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.entities.tower.TowerTriangle;
import com.zigabyte.polygondefense.entities.ui.MenuBarBottom;
import com.zigabyte.polygondefense.entities.ui.UIElement;
import com.zigabyte.polygondefense.entities.ui.top.ButtonHexagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonPentagon;
import com.zigabyte.polygondefense.entities.ui.top.ButtonSquare;
import com.zigabyte.polygondefense.entities.ui.top.ButtonTriangle;
import com.zigabyte.polygondefense.entities.ui.top.ButtonWall;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Controller;
import com.zigabyte.polygondefense.input.Input;
import com.zigabyte.polygondefense.level.Tile.State;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Level {

	private Game game;
	public Controller controller;

	public float TILE_WIDTH;
	public float TILE_HEIGHT;
	public int TILES_X;
	public int TILES_Y;

	public float LEVEL_WIDTH;
	public float LEVEL_HEIGHT;

	public final int X_PADDING_LEFT = 0;
	public final int X_PADDING_RIGHT = 0;
	public final int Y_PADDING_BOTTOM = 0;
	public final int Y_PADDING_TOP = 0;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<UIElement> ui = new ArrayList<UIElement>();
	private ArrayList<Node> nodes = new ArrayList<Node>();

	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	public Tile start;
	public Tile exit;

	public Level(Game game) {
		this.game = game;

		controller = new Controller(this);

		// addEntity(new Tower(this, new Vector2f(200, 200)));
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			addEntity(new Mob(this, new Vector2f(100 + r.nextInt(500), 100 + r.nextInt(500))));
		}

		addEntity(new TowerTriangle(this, new Vector2f(350, 650)));

		ui.add(new MenuBarBottom(this));
		ui.add(new ButtonTriangle(this));
		ui.add(new ButtonSquare(this));
		ui.add(new ButtonPentagon(this));
		ui.add(new ButtonHexagon(this));
		ui.add(new ButtonWall(this));

		/*
		 * nodes.add(new Tile(new Vector2i(5, 5))); nodes.add(new Tile(new
		 * Vector2i(6, 6))); nodes.add(new Tile(new Vector2i(2, 8)));
		 * nodes.add(new Tile(new Vector2i(7, 3)));
		 */

		LevelLoader loader = new LevelLoader();
		loader.loadLevel(1, this);

		nodes.add(getTile(5, 5).node);
		nodes.add(getTile(4, 4).node);
		nodes.add(getTile(8, 2).node);
		nodes.add(getTile(12, 5).node);
		nodes.add(getTile(2, 2).node);
		nodes.add(getTile(5, 5).node);
		nodes.add(new Node(this, new Vector2f(1000000, 5)));
		

		start = getTile(0, 7);
		exit = getTile(15, 1);
		nodes.add(start.node);
		nodes.add(exit.node);

		calculateCosts();
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

		// DEBUG LINES
		for (int i = 0; i < TILES_X; i++) {
			if (i < TILES_Y)
				render.drawLine(X_PADDING_LEFT, TILE_HEIGHT * i + Y_PADDING_BOTTOM, LEVEL_WIDTH + X_PADDING_LEFT, TILE_HEIGHT * i + Y_PADDING_BOTTOM);
			render.drawLine(TILE_WIDTH * i + X_PADDING_LEFT, Y_PADDING_BOTTOM, TILE_WIDTH * i + X_PADDING_LEFT, Y_PADDING_BOTTOM + LEVEL_HEIGHT);
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
	 * Calculate the costs of tiles
	 * 
	 * @param origin
	 *            - the tile with the lowest cost (should be the spawn point)
	 *            
	 * @return - returns true if the path from start to exit is still unblocked.
	 */
	public boolean calculateCosts() {
		// First reset the cost of all the tiles
		for (Tile t : tiles)
			t.cost = 500;

		Queue<Tile> queue = new Queue<Tile>();
		queue.addFirst(exit);
		int cost = 0;
		exit.cost = 0;

		while (queue.size > 0) {
			Tile current = queue.get(0);
			queue.removeFirst();

			cost = current.cost + 1;

			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {

					if (dx != dy && dx != -dy) {

						int x = current.getXI() + dx;
						int y = current.getYI() + dy;
						if (x >= 0 && y >= 0 && x < TILES_X && y < TILES_Y) {
							Tile t = getTile(x, y);

							if (t.state != Tile.State.FREE)
								continue;

							if (t.cost > cost) {
								t.cost = cost;
								queue.addLast(t);
							}
						}
					}
				}
			}
		}
		// Check if the method was able to calculate the cost for the end tile.
		return start.cost != 500;
	}

	/**
	 * Gets tile from world coordinates
	 */
	public Tile getTile(float x, float y) {
		float xWorld = x - X_PADDING_LEFT;
		float yWorld = y - Y_PADDING_BOTTOM;

		// Make sure the x and y are in bounds of the tile array.
		if (xWorld < 0 || yWorld < 0 || xWorld > LEVEL_WIDTH || yWorld > LEVEL_HEIGHT) {
			return null;
		}

		return getTile((int) (xWorld / TILE_WIDTH), (int) (yWorld / TILE_HEIGHT));
	}

	public Tile getTile(Vector2f v) {
		return getTile(v.x, v.y);
	}

	/**
	 * Gets tile by tile index
	 */
	public Tile getTile(int x, int y) {
		for (Tile t : tiles) {
			if (t.getXI() == x && t.getYI() == y)
				return t;
		}

		return null;
	}

	public Tile getTile(Vector2i v) {
		return getTile(v.x, v.y);
	}

	public Node getNode(int i) {
		return nodes.get(i);
	}

	public ArrayList<Mob> getMobs() {
		ArrayList<Mob> mobs = new ArrayList<Mob>();
		for (Entity e : entities) {
			if (e instanceof Mob)
				mobs.add((Mob) e);
		}
		return mobs;
	}

	public ArrayList<Mob> getMobsInRange(Vector2f point, float range) {
		ArrayList<Mob> mobs = new ArrayList<Mob>();
		for (Entity e : entities) {
			if (e instanceof Mob && e.pos.distanceSquared(point) < range * range)
				mobs.add((Mob) e);
		}
		return mobs;
	}
}
