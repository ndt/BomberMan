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
import de.n6dt.bomberman.BomberMan;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class WallTile implements ITile {

	public void draw(PApplet p) {
		p.fill(0);
		p.rect(0, 0, BomberMan.TILE_SIZE, BomberMan.TILE_SIZE);
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

}
