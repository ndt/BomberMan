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
	float   _timer;
	float   _timeStart;
	float   _timerEnd;
	boolean _explode;
	boolean _operating;
	int     _positionX;
	int     _positionY;
	int     _explodeRadius;
	Board   _board;

	Bomb(Board board) {
		_board = board;
		_operating = false;
	}

	void setBomb(int x, int y, int z, float t) {
		_positionX = x;
		_positionY = y;
		_explodeRadius = z;
		_timerEnd = t;
		_board.tiles[_positionX][_positionY].setBomb();
		_operating = true;
		BomberMan p = BomberMan.getP();
		_timeStart = (float) p.frameCount / BomberMan.FRAME_RATE;
	}

	void checkExplode() {
		BomberMan p = BomberMan.getP();
		_timer = (float) p.frameCount / BomberMan.FRAME_RATE - _timeStart;

		if (_timer >= _timerEnd) {
			explode();
		}
	}

	void explodeRight() {
		if (_positionX+1 >= _board._tilesX) return;
		if (_board.tiles[_positionX+1][_positionY].getType() == "blocked") return;

		for (int x = _positionX; x <= _positionX + _explodeRadius; x++) {
			if (x < _board._tilesX) {     
				if (_board.tiles[x][_positionY].getType() == "wall") {
					_board.tiles[x][_positionY].setType("explode");  
					break;
				} else {
					_board.tiles[x][_positionY].setType("explode");
				}
			}
		}
	}

	void explodeLeft() {
		if (_positionX-1 < 0) return;
		if (_board.tiles[_positionX-1][_positionY].getType() == "blocked") return;

		for (int x = _positionX; x >= _positionX - _explodeRadius; x--) {   
			if (x >= 0) {
				if (_board.tiles[x][_positionY].getType() == "wall") {
					_board.tiles[x][_positionY].setType("explode");  
					break;
				} else {
					_board.tiles[x][_positionY].setType("explode");
				}
			}
		}
	}

	void explodeDown() {
		if (_positionY+1 >= _board._tilesY) return;
		if (_board.tiles[_positionX][_positionY+1].getType() == "blocked") return;

		for (int y = _positionY; y <= _positionY + _explodeRadius; y++) {   
			if (y < _board._tilesY) {
				if (_board.tiles[_positionX][y].getType() == "wall") {
					_board.tiles[_positionX][y].setType("explode");  
					break;
				} else {
					_board.tiles[_positionX][y].setType("explode");
				}
			}
		}
	}  

	void explodeUp() {
		if (_positionY-1 < 0) return;
		if (_board.tiles[_positionX][_positionY-1].getType() == "blocked") return;

		for (int y = _positionY; y >= _positionY - _explodeRadius; y--) {   
			if (y >= 0) {
				if (_board.tiles[_positionX][y].getType() == "wall") {
					_board.tiles[_positionX][y].setType("explode");  
					break;
				} else {
					_board.tiles[_positionX][y].setType("explode");
				}
			}
		}
	}

	void explode() {
		explodeRight();
		explodeLeft();
		explodeDown();
		explodeUp();

		_board.tiles[_positionX][_positionY].delBomb();
		_operating = false;
		_timer = 0;
	}

	boolean isWorking() {
		return _operating;
	}

}
