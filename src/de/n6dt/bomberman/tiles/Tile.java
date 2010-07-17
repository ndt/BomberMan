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

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 *
 */
public class Tile implements ITile {
	String _type;
	boolean _usedByPlayer;
	boolean _bombed;
	boolean _exploding;
	int _frameCounter;
	protected Point _position;

	public Tile(Point pos) {
		this(pos, "");
	}
	public Tile(Point pos, String t) {
		_position = pos;
		_type = t;
		_bombed = false;
		_exploding = false;
		_usedByPlayer = false;
	}

	public void draw() {
		BomberMan p = BomberMan.getP();

		p.fill(255);
		p.rect(_position.x * Board.TILE_SIZE, _position.y * Board.TILE_SIZE, Board.TILE_SIZE, Board.TILE_SIZE);

		if (_type == "explode") {
			if (!_exploding) {
				_exploding = true;
				_frameCounter = 0;
			}
			_frameCounter++;
			if (_frameCounter / BomberMan.FRAME_RATE == 1) {
				_type = "free";
				_exploding = false;
			}
		}
	}

	public String getType() {
		return _type;
	}

	public void setType(String t) {
		_type = t;
	}

	public void setBomb() {
		_bombed = true;
	}

	public void delBomb() {
		_bombed = false;
	}

	public void setPlayer() {
		_usedByPlayer = true;
	}

	public void delPlayer() {
		_usedByPlayer = false;
	}

	public boolean usable() {
		if (_type != "wall" && !_usedByPlayer)
			return true;
		else
			return false;
	}
	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return _position;
	}

}
