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
import de.n6dt.bomberman.items.Bomb;
import de.n6dt.bomberman.items.Item;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Player {

	final static float DIAMETER = Board.TILE_SIZE * 3 / 4;

	private Position _position;
	private String _name;
	private int _color;


	/**
	 * @param initialPosition
	 * @param name
	 * @param color
	 */
	public Player(Position pos, String name, int color) {
		_position = pos;
		_name = name;
		_color = color;
	}

	/**
	 * 
	 */
	public void draw(PApplet p) {
		p.fill(_color);
		p.ellipse((float) (_position.x + 0.5) * Board.TILE_SIZE, (float) (_position.y + 0.5) * Board.TILE_SIZE, DIAMETER, DIAMETER);
	}

	/**
	 * 
	 */
	public void moveLeft() {
		if (Board.canMoveLeft(_position)) {
			_position.x--;
		}
	}

	/**
	 * 
	 */
	public void moveRight() {
		if (Board.canMoveRight(_position)) {
			_position.x++;
		}
	}

	/**
	 * 
	 */
	public void moveUp() {
		if (Board.canMoveUp(_position)) {
			_position.y--;
		}
	}

	/**
	 * 
	 */
	public void moveDown() {
		if (Board.canMoveDown(_position)) {
			_position.y++;
		}
	}

	/**
	 * 
	 */
	public void dropBomb() {
		BomberMan.items.put(_position, new Bomb(_position));
	}

	public Position getPosition() {
		return _position;
	}

}
