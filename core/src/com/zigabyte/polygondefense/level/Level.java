package com.zigabyte.polygondefense.level;

import java.util.ArrayList;

import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.Node;
import com.zigabyte.polygondefense.entities.mobs.Mob;
import com.zigabyte.polygondefense.entities.tower.Tower;
import com.zigabyte.polygondefense.entities.ui.Button;
import com.zigabyte.polygondefense.entities.ui.MenuBarBottom;
import com.zigabyte.polygondefense.entities.ui.UIElement;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.input.Click;
import com.zigabyte.polygondefense.input.Input;
import com.zigabyte.polygondefense.math.Vector2f;
import com.zigabyte.polygondefense.math.Vector2i;

public class Level {

	public final int TILE_WIDTH;
	public final int TILE_HEIGHT;
	public final int TILES_X;
	public final int TILES_Y;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<UIElement> ui = new ArrayList<UIElement>();
	private ArrayList<Node> nodes = new ArrayList<Node>();

	private ArrayList<Tile> tiles = new ArrayList<Tile>();

	public Level() {
		addEntity(new Tower(this, new Vector2f(200, 200)));
		addEntity(new Mob(this, new Vector2f(-100, 100)));

		ui.add(new MenuBarBottom(this));
		ui.add(new Button(this));

		/*
		 * nodes.add(new Tile(new Vector2i(5, 5))); nodes.add(new Tile(new Vector2i(6, 6))); nodes.add(new Tile(new Vector2i(2, 8))); nodes.add(new
		 * Tile(new Vector2i(7, 3)));
		 */

		TILES_X = 16;
		TILES_Y = 9;

		TILE_WIDTH = Render.WIDTH / TILES_X;
		TILE_HEIGHT = Render.HEIGHT / TILES_Y;

		for (int x = 0; x < TILES_X; x++) {
			for (int y = 0; y < TILES_Y; y++) {
				tiles.add(new Tile(this, new Vector2i(x, y)));
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

	private void processInput() {
		if (Input.ready()) {
			Vector2f input = Input.inputs.get(0).getPos();
			Input.inputs.remove(0);

			// Send input to ui first
			for (int i = 0; i < ui.size(); i++) {
				if (ui.get(i).processInput(input)) {
					break;
				}
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

	/**
	 * Render the level.
	 */
	public void render(Render render) {

		for (int i = 0; i < 16; i++) {
			if (i < 9)
				render.drawLine(0, 100 * i, 1600, 100 * i);
			render.drawLine(100 * i, 0, 100 * i, 900);
		}

		renderEntities(render);
		renderNodes(render);
		renderUI(render);

		// render.drawPolygon(new Polygon(7, 50));
		// render.drawPolygon(new Polygon(3, 100), Color.BLUE, +600, 300, 45);
		// render.drawPolygon(new Polygon(4, 150), Color.ORANGE, +200, -100, 72);
		// render.drawPolygon(new Polygon(5, 90), Color.RED, -500, -300);
		// render.drawPolygon(new Polygon(9, 70), Color.GREEN, -300, 250);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public Tile getTile(int x, int y) {
		/*
		 * for (Tile t : tiles) if (t.pos.x == x && t.pos.y == y) { return t; }
		 */

		return tiles.get(x * TILES_Y + y);
	}

	public Tile getTile(Vector2i v) {
		return getTile(v.x, v.y);
	}

	public Node getNode(int i) {
		return nodes.get(i);
	}
}
