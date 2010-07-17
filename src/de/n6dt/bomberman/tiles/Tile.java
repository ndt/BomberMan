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
package de.n6dt.bomberman.tiles;

import java.awt.Point;

import de.n6dt.bomberman.Board;
import de.n6dt.bomberman.BomberMan;
import de.n6dt.bomberman.Position;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 *
 */
public class Tile implements ITile {
	protected Position _position;

	public Tile(Position pos) {
		_position = pos;
	}

	public void draw() {
		BomberMan p = BomberMan.getP();

		p.fill(255);
		p.rect(_position.x * Board.TILE_SIZE, _position.y * Board.TILE_SIZE, Board.TILE_SIZE, Board.TILE_SIZE);
	}

	public boolean usable() {
		return true;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return _position;
	}
}
