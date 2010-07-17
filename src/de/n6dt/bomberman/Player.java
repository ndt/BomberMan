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

	Point  _position;
	String _name;
	int    _color;
	Board  _board;
	Bomb   _bomb;

	/**
	 * @param board
	 * @param initialPosition
	 * @param name
	 * @param color
	 */
	public Player(Board board, Point initialPosition, String name, int color) {
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

	/**
	 * @param x
	 * @param y
	 */
	public void draw(PApplet p) {
		p.fill(_color);
		p.ellipse(_position.x + BomberMan.TILE_SIZE / 2, _position.y + BomberMan.TILE_SIZE / 2, BomberMan.TILE_SIZE * 3 / 4, BomberMan.TILE_SIZE * 3 / 4);
	}

	/**
	 * 
	 */
	void moveLeft() {
		if (_board.canMoveLeft(_position)) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.x--;
			_board.checkKilled(this, _position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	/**
	 * 
	 */
	void moveRight() {
		if (_board.canMoveRight(_position)) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.x++;
			_board.checkKilled(this, _position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	/**
	 * 
	 */
	void moveUp() {
		if (_board.canMoveUp(_position)) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.y--;
			_board.checkKilled(this, _position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	/**
	 * 
	 */
	void moveDown() {
		if (_board.canMoveDown(_position)) {
			_board.tiles[_position.x][_position.y].setType("free");
			_board.tiles[_position.x][_position.y].delPlayer();
			_position.y++;
			_board.checkKilled(this, _position.x, _position.y);
			_board.tiles[_position.x][_position.y].setType(_name);
			_board.tiles[_position.x][_position.y].setPlayer();
		}
	}

	/**
	 * 
	 */
	void setBomb() {
		if (!_bomb.isTicking()) {
			_bomb.setBomb(_position.x, _position.y, BomberMan.EXPLODE_R);
		}
	}

	/**
	 * 
	 */
	void killed() {
		PApplet.println(_name + " killed!");
		BomberMan.stop = true;
	}

}
