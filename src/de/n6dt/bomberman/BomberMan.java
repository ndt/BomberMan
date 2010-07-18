/**
 * This script belongs to BomberMan.
 *
 * It is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This script is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHAN-
 * TABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with the script.
 * If not, see http://www.gnu.org/licenses/lgpl.html
 *
 */
package de.n6dt.bomberman;

import java.util.ArrayList;
import java.util.HashMap;

import de.n6dt.bomberman.items.Explosion;
import de.n6dt.bomberman.items.Item;
import de.n6dt.bomberman.items.Block;
import de.n6dt.bomberman.tiles.WallTile;
import de.n6dt.bomberman.tiles.FreeTile;
import de.n6dt.bomberman.tiles.Tile;
import processing.core.PApplet;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class BomberMan extends PApplet {

	private static final long serialVersionUID = 1L;

	public final static int FRAME_RATE = 60;

	public static boolean stop = false;

	ArrayList<Player> players;

	public final static int TILE_SIZE = 30;
	public final static int TILES_HEIGHT = 11;
	public final static int TILES_WIDTH = 21;

	public static final String[] level1 = {
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNBBBBBBBBBBBBBBBNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN",
		"NNNNNNNNNNNNNNNNNNNNN"
	};

	public static HashMap<Position,Item> items;
	public static HashMap<Position,Tile> tiles;

	public BomberMan() {
		super();
		tiles = new HashMap<Position, Tile>();
		items = new HashMap<Position, Item>();
		
		BomberMan.createLevel1();
		
		players = new ArrayList<Player>();
		players.add(new Player(new Position(0, 0), "Spieler 1", 0xFF0050FF));
		players.add(new Player(new Position(8, 8), "Spieler 2", 0xFF00FF00));
	}

	public void setup() {
		size(BomberMan.TILES_WIDTH * BomberMan.TILE_SIZE + 1, BomberMan.TILES_HEIGHT * BomberMan.TILE_SIZE + 1);
		smooth();
		frameRate(FRAME_RATE);
	}

	public void keyPressed() {
		switch (key) {

		case '4':
			players.get(0).moveLeft();
			break;

		case '6':
			players.get(0).moveRight();
			break;

		case '8':
			players.get(0).moveUp();
			break;

		case '5':
			players.get(0).moveDown();
			break;

		case '0':
			players.get(0).dropBomb();
			break;

		case 'a':
			players.get(1).moveLeft();
			break;

		case 'd':
			players.get(1).moveRight();
			break;

		case 'w':
			players.get(1).moveUp();
			break;

		case 's':
			players.get(1).moveDown();
			break;

		case ' ':
			players.get(1).dropBomb();
			break;

		default:
			break;
		}
	}

	public void draw() {
		if (stop) {
			background(0);
			return;
		}

		BomberMan.checkPlayers(players);

		background(255);
		for (Tile tile: tiles.values()) {
			tile.draw(this);
		}
		for (Item object: items.values()) {
			object.draw(this);
		}
		for (Player player : players) {
			player.draw(this);
		}
	}

	public static void createLevel1() {
		for (int i = 0; i < BomberMan.TILES_WIDTH; i++) {
			for (int j = 0; j < BomberMan.TILES_HEIGHT; j++) {
				Position pos = new Position(i, j);
	
				if ((i + 1) % 4 == 0 && (j + 1) % 4 == 0) {
					tiles.put(pos, new WallTile(pos));
				} else if ((i + 1) % 4 == 0 || (j + 1) % 4 == 0) {
					items.put(pos, new Block(pos));
				} else {
					tiles.put(pos, new FreeTile(pos));
				}
			}
		}
	}

	public static void checkPlayers(ArrayList<Player> players) {
		for (Player player : players) {
			for (Tile tile : tiles.values()) {
				if ((tile instanceof Explosion)
						&& tile.getPosition() == player.getPosition()) {
					stop = true;
				}
			}
		}
	}

	public static boolean canMoveDown(Position _position) {
		return (_position.y + 1 < BomberMan.TILES_HEIGHT)
				&& (tiles.get(_position.down(1)).usable());
	}

	public static boolean canMoveUp(Position _position) {
		return (_position.y - 1 >= 0)
				&& (tiles.get(_position.up(1)).usable());
	}

	public static boolean canMoveRight(Position _position) {
		return (_position.x + 1 < BomberMan.TILES_WIDTH)
				&& (tiles.get(_position.right(1)).usable());
	}

	public static boolean canMoveLeft(Position _position) {
		return (_position.x - 1 >= 0)
				&& (tiles.get(_position.left(1)).usable());
	}
	
	public static void addItem(Position pos, Item item) {
		items.put(pos, item);
	}
}
