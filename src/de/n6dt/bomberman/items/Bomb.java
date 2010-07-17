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
package de.n6dt.bomberman.items;

import processing.core.PApplet;
import de.n6dt.bomberman.BomberMan;
import de.n6dt.bomberman.Position;
import de.n6dt.bomberman.tiles.ExplosionTile;
import de.n6dt.bomberman.tiles.WallTile;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Bomb extends Item {

	final int DURATION = 3;

	int _explodeRadius;

	public Bomb(Position position) {
		super(position);
	}

	void explode() {
		BomberMan.items.put(_position, new ExplosionTile(_position));
		
		for (int i = 1; i <= _explodeRadius; i++) {
			Position p = _position.right(i);
			if (p.x < BomberMan.TILES_WIDTH) {
				if (BomberMan.tiles.get(p) instanceof WallTile) break;
				BomberMan.items.put(p,new ExplosionTile(p));
			}
		}
		for (int i = 1; i <= _explodeRadius; i++) {
			Position p = _position.left(i);
			if (p.x >= 0) {
				if (BomberMan.tiles.get(p) instanceof WallTile) break;
				BomberMan.items.put(p,new ExplosionTile(p));
			}
		}
		for (int i = 1; i <= _explodeRadius; i++) {
			Position p = _position.down(i);
			if (p.y < BomberMan.TILES_HEIGHT) {
				if (BomberMan.tiles.get(p) instanceof WallTile) break;
				BomberMan.items.put(p,new ExplosionTile(p));
			}
		}
		for (int i = 1; i <= _explodeRadius; i++) {
			Position p = _position.up(i);
			if (p.y >= 0) {
				if (BomberMan.tiles.get(p) instanceof WallTile) break;
				BomberMan.items.put(p,new ExplosionTile(p));
			}
		}
	}

	public void draw(PApplet p) {
		p.fill(0);
		p.ellipse((float) (_position.x + 0.5) * BomberMan.TILE_SIZE, (float) (_position.y + 0.5) * BomberMan.TILE_SIZE, (float) BomberMan.TILE_SIZE / 2, (float) BomberMan.TILE_SIZE / 2);
	}

}
