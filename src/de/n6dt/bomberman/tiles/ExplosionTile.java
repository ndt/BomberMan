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

import processing.core.PApplet;
import de.n6dt.bomberman.Board;
import de.n6dt.bomberman.Position;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 *
 */
public class ExplosionTile extends Tile implements ITile {
	
	public ExplosionTile(Position pos) {
		super(pos);
	}

	/**
	 * @see de.n6dt.bomberman.tiles.ITile#draw(int, int)
	 */
	@Override
	public void draw(PApplet p) {
		p.fill(0xFFFF0000);
		p.rect(_position.x * Board.TILE_SIZE + 5, _position.y * Board.TILE_SIZE + 5, Board.TILE_SIZE - 10, Board.TILE_SIZE - 10);
	}

}
