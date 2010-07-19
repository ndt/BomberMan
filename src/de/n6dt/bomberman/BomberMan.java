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
import de.n6dt.bomberman.tiles.ITile;
import de.n6dt.bomberman.tiles.WallTile;
import de.n6dt.bomberman.tiles.FreeTile;
import processing.core.PApplet;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class BomberMan extends PApplet {

	private static final long serialVersionUID = 1L;

	public static final int FRAME_RATE = 60;

	public static final int TILE_SIZE = 30;

	public static final String[] level1 = {
		"FFFFFFFFFFFFFFFFFFFFF",
		"FFFWWWWWWWWWWWWWWWFFF",
		"FFFFFFFFFFFFFFFFFFFFF",
		"FFFFFFFFFFFFWWWWWFFFF",
		"FFFFFFFFFFFFFFFFFFFFF",
		"FFWWWWWWWWWWWWWWWFFFF",
		"FFFFWFFFFFFFFFFFFFFFF",
		"FFFFWFFFFFWFFFFFWWFFF",
		"FFFFWWWWWWWFFFFFFWFFF",
		"FFFFFFFFFFFFFFFFFFFFF",
		"FFFFFFFFFFFFFFFFFFFFF"};

	public static final int TILES_HEIGHT = level1.length;
	public static final int TILES_WIDTH = level1[0].length();

	public static boolean stop = false;
	public static HashMap<Position, ITile> tiles;
	public static ArrayList<Item> items;
	public static ArrayList<Player> players;

	public BomberMan() {
		super();
		tiles = new HashMap<Position, ITile>();
		items = new ArrayList<Item>();

		BomberMan.createLevel1();

		players = new ArrayList<Player>();
		players.add(new Player(new Position(0, 0), "Spieler 1", 0xFF0050FF));
		players.add(new Player(new Position(8, 9), "Spieler 2", 0xFF00FF00));
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
		drawBackground();
		drawItems();
		drawPlayers();
	}

	private void drawBackground() {
		ITile tile;

		pushMatrix();
		translate(-TILE_SIZE, 0);
		for (int i = 0; i < level1.length; i++) { // rows
			pushMatrix();
			for (int j = 0; j < level1[0].length(); j++) { // columns
				translate(TILE_SIZE, 0);
				switch (level1[i].charAt(j)) {
				case 'F':
					tile = new FreeTile();
					break;

				case 'W':
					tile = new WallTile();
					break;

				default:
					tile = new FreeTile();
					break;
				}
				tile.draw(this);
				tiles.put(new Position(j,i), tile);
			}
			popMatrix();
			translate(0, TILE_SIZE);
		}
		popMatrix();
	}

	private void drawItems() {
		Position pos;

		for (Item item : items) {
			pos = item.getPosition();

			pushMatrix();
			translate(pos.x * TILE_SIZE, pos.y * TILE_SIZE);
			item.draw(this);
			popMatrix();
		}
	}

	private void drawPlayers() {
		Position pos;

		for (Player player : players) {
			pos = player.getPosition();

			pushMatrix();
			translate(pos.x * TILE_SIZE, pos.y * TILE_SIZE);
			player.draw(this);
			popMatrix();
		}
	}

	public static void createLevel1() {
		for (int i = 0; i < BomberMan.TILES_WIDTH; i++) {
			for (int j = 0; j < BomberMan.TILES_HEIGHT; j++) {
				Position pos = new Position(i, j);
				if ((i + 1) % 4 == 0 || (j + 1) % 4 == 0) {
					items.add(new Block(pos));
				}
			}
		}
	}

	public static void checkPlayers(ArrayList<Player> players) {
		for (Player player : players) {
			for (Item item : items) {
				if (item.getPosition() == player.getPosition()) {
					if (item instanceof Explosion) {
						stop = true;
					}
				}
			}
		}
	}
}
