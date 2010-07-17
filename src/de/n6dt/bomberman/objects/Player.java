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
package de.n6dt.bomberman.objects;

import java.awt.Point;

import de.n6dt.bomberman.Board;
import de.n6dt.bomberman.BomberMan;
import de.n6dt.bomberman.Position;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Player {

	final static float DIAMETER = Board.TILE_SIZE * 3 / 4;

	Position _position;
	String _name;
	int _color;

	/**
	 * @param initialPosition
	 * @param name
	 * @param color
	 */
	public Player(Position initialPosition, String name, int color) {
		_position = initialPosition;
		_name = name;
		_color = color;
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
	public void moveLeft() {
		if (BomberMan.board.canMoveLeft(_position)) {
			_position.x--;
		}
	}

	/**
	 * 
	 */
	public void moveRight() {
		if (BomberMan.board.canMoveRight(_position)) {
			_position.x++;
		}
	}

	/**
	 * 
	 */
	public void moveUp() {
		if (BomberMan.board.canMoveUp(_position)) {
			_position.y--;
		}
	}

	/**
	 * 
	 */
	public void moveDown() {
		if (BomberMan.board.canMoveDown(_position)) {
			_position.y++;
		}
	}

	/**
	 * 
	 */
	public void dropBomb() {
		BomberMan.board.tiles.put(_position, new Bomb(_position));
	}

	public Point getPosition() {
		return _position;
	}

}
