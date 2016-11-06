package com.zigabyte.polygondefense.level;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.zigabyte.polygondefense.graphics.Render;
import com.zigabyte.polygondefense.level.Tile.State;
import com.zigabyte.polygondefense.math.Vector2i;

public class LevelLoader {
	public LevelLoader() {

	}

	public void loadLevel(int levelNumber, Level level) {
		FileHandle file = Gdx.files.internal("data/level" + levelNumber + ".txt");
		String text = file.readString();

		// Individual lines of the file
		String[] lines = text.split("\n");

		// Read the width and height of the level in tiles
		String[] first = lines[0].split("-");
		int w = Integer.parseInt(first[0].trim());
		int h = Integer.parseInt(first[1].trim());

		lines = Arrays.copyOfRange(lines, 1, lines.length);

		int TILES_X = (int) (w * 1.0f);
		int TILES_Y = (int) (h * 1.0f);

		int LEVEL_WIDTH = Render.WIDTH - (level.X_PADDING_LEFT + level.X_PADDING_RIGHT);
		int LEVEL_HEIGHT = Render.HEIGHT - (level.Y_PADDING_BOTTOM + level.Y_PADDING_TOP);

		int TILE_WIDTH = LEVEL_WIDTH / TILES_X;
		int TILE_HEIGHT = LEVEL_HEIGHT / TILES_Y;

		// Set the values in the level - must be done before making the tiles
		level.TILES_X = TILES_X;
		level.TILES_Y = TILES_Y;
		level.LEVEL_WIDTH = LEVEL_WIDTH;
		level.LEVEL_HEIGHT = LEVEL_HEIGHT;
		level.TILE_WIDTH = TILE_WIDTH;
		level.TILE_HEIGHT = TILE_HEIGHT;

		// Create the tiles and add states
		for (int y = 0; y < TILES_Y; y++) {
			// Get a new line
			String row = lines[y];
			String[] tiles = row.split("-");
			for (int i = 0; i < TILES_X; i++) {
				tiles[i] = tiles[i].replace("{", "");
				tiles[i] = tiles[i].replace("}", "");
			}
			for (int x = 0; x < TILES_X; x++) {
				level.tiles.add(new Tile(level, new Vector2i(x, y)));

				int tileId = Integer.parseInt(tiles[x].trim());
				if (tileId == 4) {
					// System.out.println("Hello " + x + " " + y);
					level.getTile(x, y).state = State.BLOCKED;
				} else if (tileId == 0) {
					level.getTile(x, y).state = State.WALL;
				}
			}
		}
	}
}
