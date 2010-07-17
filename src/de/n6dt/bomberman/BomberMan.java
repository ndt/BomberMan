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

import de.n6dt.bomberman.items.Item;
import de.n6dt.bomberman.tiles.ITile;
import processing.core.PApplet;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class BomberMan extends PApplet {

	private static final long serialVersionUID = 1L;

	public final static int FRAME_RATE = 60;

	public static boolean stop = false;

	public static BomberMan p;

	ArrayList<Player> players;

	public static HashMap<Position,Item> items;
	public static HashMap<Position,ITile> tiles;
	public static Board board;

	public BomberMan() {
		super();
		tiles = new HashMap<Position, ITile>();
		items = new HashMap<Position, Item>();
		
		Board.createLevel1();
		
		players = new ArrayList<Player>();
		players.add(new Player(new Position(0, 0), "Spieler 1", 0xFF0050FF));
		players.add(new Player(new Position(8, 8), "Spieler 2", 0xFF00FF00));
		p = this;
	}

	public static BomberMan getP() {
		return p;
	}

	public void setup() {
		size(Board.TILES_WIDTH * Board.TILE_SIZE + 1, Board.TILES_HEIGHT * Board.TILE_SIZE + 1);
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

		Board.checkPlayers(players);

		background(255);
		for (ITile tile: tiles.values()) {
			tile.draw(this);
		}
		for (Item object: items.values()) {
			object.draw(this);
		}
		for (Player player : players) {
			player.draw(this);
		}
	}
}
