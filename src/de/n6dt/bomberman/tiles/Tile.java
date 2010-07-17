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

import de.n6dt.bomberman.BomberMan;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 *
 */
public class Tile {
	String _type;
	boolean _usedByPlayer;
	boolean _bombed;
	boolean _exploding;
	int _frameCounter;

	public Tile(String t) {
		_type = t;
		_bombed = false;
		_exploding = false;
		_usedByPlayer = false;
	}

	public void display(int x, int y) {
		BomberMan p = BomberMan.getP();

		p.fill(255);
		p.rect(x, y, BomberMan.TILE_SIZE, BomberMan.TILE_SIZE);

		if (_type == "wall") {
			p.fill(129);
			p.rect(x + 3, y + 3, BomberMan.TILE_SIZE - 6,
					BomberMan.TILE_SIZE - 6);
		}

		if (_type == "Spieler 1") {
			p.fill(0x0050FF);
			p.ellipse(x + BomberMan.TILE_SIZE / 2, y + BomberMan.TILE_SIZE / 2,
					BomberMan.TILE_SIZE * 3 / 4, BomberMan.TILE_SIZE * 3 / 4);
		}

		if (_type == "Spieler 2") {
			p.fill(0x00FF00);
			p.ellipse(x + BomberMan.TILE_SIZE / 2, y + BomberMan.TILE_SIZE / 2,
					BomberMan.TILE_SIZE * 3 / 4, BomberMan.TILE_SIZE * 3 / 4);
		}

		if (_type == "explode") {
			if (!_exploding) {
				_exploding = true;
				_frameCounter = 0;
			}
			_frameCounter++;
			if (_frameCounter / BomberMan.FRAME_RATE == 1) {
				_type = "free";
				_exploding = false;
			} else {
				p.fill(0xFF0000);
				p.rect(x + 5, y + 5, BomberMan.TILE_SIZE - 10,
						BomberMan.TILE_SIZE - 10);
			}

		}

		if (_bombed) {
			p.fill(0);
			p.ellipse(x + BomberMan.TILE_SIZE / 2, y + BomberMan.TILE_SIZE / 2,
					BomberMan.TILE_SIZE * 1 / 2, BomberMan.TILE_SIZE * 1 / 2);
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

}
