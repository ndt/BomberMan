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

import java.awt.Point;
import de.n6dt.bomberman.tiles.BlockTile;
import de.n6dt.bomberman.tiles.NullTile;
import de.n6dt.bomberman.tiles.Tile;
import de.n6dt.bomberman.tiles.WallTile;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Board {
	
	int _width;
	int _height;
	int _stdX;
	int _stdY;
	int _tilesX;
	int _tilesY;

	Tile[][] tiles;

	public Board(int a, int b) {
		_width = a;
		_height = b;

		_tilesX = a / BomberMan.TILE_SIZE;
		if (_tilesX % 2 == 0) {
			_tilesX--;
			_stdX = (BomberMan.WINDOW_WIDTH - _tilesX * BomberMan.TILE_SIZE) / 2;
		}

		_tilesY = b / BomberMan.TILE_SIZE;
		if (_tilesY % 2 == 0) {
			_tilesY--;
			_stdY = (BomberMan.WINDOW_HEIGHT - _tilesY * BomberMan.TILE_SIZE) / 2;
		}

		tiles = new Tile[_tilesX][_tilesY];
		for (int i = 0; i < _tilesX; i++) {
			for (int j = 0; j < _tilesY; j++) {
				if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0) {
					tiles[i][j] = new BlockTile();
				} else if ((i + 1) % 2 == 0 || (j + 1) % 2 == 0) {
					tiles[i][j] = new WallTile();
				} else {
					tiles[i][j] = new NullTile();
				}
			}
		}
	}

	void display() {
		for (int i = 0; i < _tilesX; i++) {
			for (int j = 0; j < _tilesY; j++) {
				tiles[i][j].display(_stdX + i * BomberMan.TILE_SIZE, _stdY + j
						* BomberMan.TILE_SIZE);
			}
		}
	}

	String getTileContent(int x, int y) {
		return tiles[x][y].getType();
	}

	void setTileContent(int x, int y, String t) {
		tiles[x][y].setType(t);
	}

	/**
	 * @param player TODO
	 * 
	 */
	void checkBombAndPlayer(Player player) {
		if (tiles[player._position.x][player._position.y].getType() == "explode")
			player.killed();
		if (player._bomb.isTicking())
			player._bomb.checkExplode();
	}

	/**
	 * @param player TODO
	 * @param x
	 * @param y
	 */
	void checkKilled(Player player, int x, int y) {
		if (tiles[x][y].getType() == "explode")
			player.killed();
	}

	public boolean canMoveLeft(Point _position) {
		return (_position.x - 1 >= 0) && (tiles[_position.x - 1][_position.y].usable());
	}

	public boolean canMoveRight(Point _position) {
		return (_position.x + 1 < _tilesX) && (tiles[_position.x + 1][_position.y].usable());
	}

	public boolean canMoveUp(Point _position) {
		return (_position.y - 1 >= 0) && (tiles[_position.x][_position.y - 1].usable());
	}

	public boolean canMoveDown(Point _position) {
		return (_position.y + 1 < _tilesY) && (tiles[_position.x][_position.y + 1].usable());
	}
}