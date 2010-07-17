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

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Bomb {

	final int DURATION = 3;

	Timer _timer;
	boolean _explode;
	boolean _operating;
	Position _position;
	int _explodeRadius;
	Board _board;

	Bomb(Board board) {
		_board = board;
		_operating = false;
	}

	void setBomb(Position position, int radius) {
		_position = position;
		_explodeRadius = radius;
		_timer = new Timer(DURATION);
		_board.tiles.get(_position).setBomb();
		_operating = true;
	}

	void checkExplode() {
		if (_timer.finished()) {
			explode();
		}
	}

	void explodeRight() {
		if (_position.x + 1 >= Board.TILES_WIDTH) return;
		if (_board.tiles.get(_position.right()).getType() == "blocked") return;

		for (int x = 1; x <= _explodeRadius; x++) {
			if (x < Board.TILES_WIDTH) {     
				if (_board.tiles.get(_position.right(x)).getType() == "wall") {
					_board.tiles.get(_position.right(x)).setType("explode");  
					break;
				} else {
					_board.tiles.get(_position.right(x)).setType("explode");
				}
			}
		}
	}

	void explodeLeft() {
		if (_position.x - 1 < 0) return;
		if (_board.tiles.get(_position.left()).getType() == "blocked") return;

		for (int x = 1; x >= _explodeRadius; x--) {
			if (x >= 0) {
				if (_board.tiles.get(_position.right(x)).getType() == "wall") {
					_board.tiles.get(_position.right(x)).setType("explode");
					break;
				} else {
					_board.tiles.get(_position.right(x)).setType("explode");
				}
			}
		}
	}

	void explodeDown() {
		if (_position.y + 1 >= Board.TILES_HEIGHT) return;
		if (_board.tiles.get(_position.down()).getType() == "blocked") return;

		for (int y = _position.y; y <= _position.y + _explodeRadius; y++) {
			if (y < Board.TILES_HEIGHT) {
				if (_board.tiles.get(_position.down()).getType() == "wall") {
					_board.tiles.get(_position.down()).setType("explode");
					break;
				} else {
					_board.tiles.get(_position.down()).setType("explode");
				}
			}
		}
	}

	void explodeUp() {
		if (_position.y - 1 < 0) return;
		if (_board.tiles.get(_position.up()).getType() == "blocked") return;

		for (int y = _position.y; y >= _position.y - _explodeRadius; y--) {
			if (y >= 0) {
				if (_board.tiles.get(_position.up()).getType() == "wall") {
					_board.tiles.get(_position.up()).setType("explode");
					break;
				} else {
					_board.tiles.get(_position.up()).setType("explode");
				}
			}
		}
	}

	void explode() {
		explodeRight();
		explodeLeft();
		explodeDown();
		explodeUp();

		_board.tiles.get(_position).delBomb();
		_operating = false;
	}

	public boolean isTicking() {
		return _operating;
	}

	public void draw() {
		BomberMan p = BomberMan.getP();
		
		p.fill(0);
		p.ellipse(_position.x + Board.TILE_SIZE / 2, _position.y + Board.TILE_SIZE / 2, Board.TILE_SIZE / 2, Board.TILE_SIZE / 2);
	}

}
