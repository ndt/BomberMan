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
import processing.core.PApplet;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Player {

	Point _position;
	String _name;
	int  _color;
	Board _board;
	Bomb _bomb;

	Player(Board board, Point initialPosition, String name, int color) {
		_board = board;
		_position = initialPosition;
		_name = name;
		_color = color;

		_bomb = new Bomb(_board);
		_board.tiles[_position.x][_position.y].setType(_name);
		if (_position.x + 1 < _board._tilesX)
			_board.tiles[_position.x + 1][_position.y].setType("free");
		if (_position.x - 1 >= 0)
			_board.tiles[_position.x - 1][_position.y].setType("free");
		if (_position.y + 1 < _board._tilesY)
			_board.tiles[_position.x][_position.y + 1].setType("free");
		if (_position.y - 1 >= 0)
			_board.tiles[_position.x][_position.y - 1].setType("free");
	}
	
	public void draw(int x, int y) {
		BomberMan p = BomberMan.getP();
		p.fill(_color);
		p.ellipse(x + BomberMan.TILE_SIZE / 2, y + BomberMan.TILE_SIZE / 2,
				BomberMan.TILE_SIZE * 3 / 4, BomberMan.TILE_SIZE * 3 / 4);
	}

	void moveLeft() {
		if (_position.x - 1 >= 0
				&& _board.tiles[_position.x - 1][_position.y].usable()) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.x--;
			checkKilled(_position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	void moveRight() {
		if (_position.x + 1 < _board._tilesX
				&& _board.tiles[_position.x + 1][_position.y].usable()) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.x++;
			checkKilled(_position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	void moveUp() {
		if (_position.y - 1 >= 0
				&& _board.tiles[_position.x][_position.y - 1].usable()) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.y--;
			checkKilled(_position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	void moveDown() {
		if (_position.y + 1 < _board._tilesY
				&& _board.tiles[_position.x][_position.y + 1].usable()) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.y++;
			checkKilled(_position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	void setBomb() {
		if (!_bomb.isWorking()) {
			_bomb.setBomb(_position.x, _position.y, BomberMan.EXPLODE_R, 3);
		}
	}

	void checkBombAndPlayer() {
		if (_board.tiles[_position.x][_position.y].getType() == "explode")
			killed();
		if (_bomb.isWorking())
			_bomb.checkExplode();
	}

	void checkKilled(int x, int y) {
		if (_board.tiles[x][y].getType() == "explode")
			killed();
	}

	void killed() {
		PApplet.println(_name + " killed!");
		BomberMan.stop = true;
	}

}
