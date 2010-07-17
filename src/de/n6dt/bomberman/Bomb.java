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

import processing.core.PApplet;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 *
 */
public class Bomb {
	
	final int DURATION = 3;
	
	Timer   _timer;
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

	void setBomb(int x, int y, int radius) {
		BomberMan p = BomberMan.getP();
		_positionX = x;
		_positionY = y;
		_explodeRadius = radius;
		_timer = new Timer(p.frameCount, DURATION);
		_board.tiles[_positionX][_positionY].setBomb();
		_operating = true;
		_timer.setTimeStart((float) p.frameCount / BomberMan.FRAME_RATE);
	}

	void checkExplode() {
		BomberMan p = BomberMan.getP();
		_timer.setTimer((float) p.frameCount / BomberMan.FRAME_RATE - _timer.getTimeStart());

		if (_timer.getTimer() >= _timer.getDuration()) {
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
		_timer.setTimer(0);
	}

	public boolean isTicking() {
		return _operating;
	}
	
	public void draw(PApplet p, int x, int y) {
		_timer.tick();
	}

}
