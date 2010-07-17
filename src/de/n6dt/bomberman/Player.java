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

	final static float DIAMETER = Board.TILE_SIZE * 3 / 4;

	Position _position;
	String _name;
	int _color;
	Board _board;
	Bomb _bomb;

	/**
	 * @param board
	 * @param initialPosition
	 * @param name
	 * @param color
	 */
	public Player(Board board, Position initialPosition, String name, int color) {

		_board = board;
		_position = initialPosition;
		_name = name;
		_color = color;

		_bomb = new Bomb(_board);
	}

	/**
	 * 
	 */
	public void draw() {
		BomberMan p = BomberMan.getP();

		p.fill(_color);
		p.ellipse((float) (_position.x + 0.5) * Board.TILE_SIZE, (float) (_position.y + 0.5) * Board.TILE_SIZE, DIAMETER, DIAMETER);
	}

	/**
	 * 
	 */
	void moveLeft() {
		if (_board.canMoveLeft(_position)) {
			_position.x--;
			_board.checkKilled(this, _position);
		}
	}

	/**
	 * 
	 */
	void moveRight() {
		if (_board.canMoveRight(_position)) {
			_position.x++;
			_board.checkKilled(this, _position);
		}
	}

	/**
	 * 
	 */
	void moveUp() {
		if (_board.canMoveUp(_position)) {
			_position.y--;
			_board.checkKilled(this, _position);
		}
	}

	/**
	 * 
	 */
	void moveDown() {
		if (_board.canMoveDown(_position)) {
			_position.y++;
			_board.checkKilled(this, _position);
		}
	}

	/**
	 * 
	 */
	void setBomb() {
		if (!_bomb.isTicking()) {
			_bomb.setBomb(_position, BomberMan.EXPLODE_R);
		}
	}

	/**
	 * 
	 */
	void killed() {
		PApplet.println(_name + " killed!");
		BomberMan.stop = true;
	}

	public Point getPosition() {
		return _position;
	}

}
