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

import de.n6dt.bomberman.objects.Player;
import de.n6dt.bomberman.tiles.BlockTile;
import de.n6dt.bomberman.tiles.ExplosionTile;
import de.n6dt.bomberman.tiles.FreeTile;
import de.n6dt.bomberman.tiles.ITile;
import de.n6dt.bomberman.tiles.WallTile;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Board {
	
	public final static int WINDOW_HEIGHT = 300;
	public final static int WINDOW_WIDTH = 300;
	public final static int TILE_SIZE = 30;
	public final static int TILES_HEIGHT = 11;
	public final static int TILES_WIDTH = 21;

	public HashMap<Position,ITile> tiles;

	public Board() {
		Position pos;

		tiles = new HashMap<Position, ITile>();
		
		for (int i = 0; i < TILES_WIDTH; i++) {
			for (int j = 0; j < TILES_HEIGHT; j++) {
				pos = new Position(i,j);

				if ((i + 1) % 4 == 0 && (j + 1) % 4 == 0) {
					tiles.put(pos, new BlockTile(pos));
				} else if ((i + 1) % 4 == 0 || (j + 1) % 4 == 0) {
					tiles.put(pos, new WallTile(pos));
				} else {
					tiles.put(pos, new FreeTile(pos));
				}
			}
		}
		
	}

	void draw() {
		for (ITile tile: tiles.values()) {
			tile.draw();
		}
	}

	public boolean canMoveLeft(Position _position) {
		return (_position.x - 1 >= 0) && (tiles.get(_position.left(1)).usable());
	}

	public boolean canMoveRight(Position _position) {
		return (_position.x + 1 < TILES_WIDTH) && (tiles.get(_position.right(1)).usable());
	}

	public boolean canMoveUp(Position _position) {
		return (_position.y - 1 >= 0) && (tiles.get(_position.up(1)).usable());
	}

	public boolean canMoveDown(Position _position) {
		return (_position.y + 1 < TILES_HEIGHT) && (tiles.get(_position.down(1)).usable());
	}

	public void checkPlayers(ArrayList<Player> players) {
		for (Player player : players) {
		for (ITile tile : tiles.values()) {
			if ((tile instanceof ExplosionTile) && tile.getPosition() == player.getPosition()) {
				BomberMan.stop = true;
			}
		}
	}
	}
}