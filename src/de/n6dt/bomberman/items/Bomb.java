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
import de.n6dt.bomberman.Board;
import de.n6dt.bomberman.BomberMan;
import de.n6dt.bomberman.Position;
import de.n6dt.bomberman.Timer;
import de.n6dt.bomberman.tiles.ExplosionTile;
import de.n6dt.bomberman.tiles.WallTile;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Bomb extends Item {

	final int DURATION = 3;

	Timer _timer;
	int _explodeRadius;

	public Bomb(Position position) {
		super(position);
		_timer = new Timer(DURATION);
	}

	void explodeRight() {
		if (!Board.canMoveRight(_position)) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.right(i).x < Board.TILES_WIDTH) {     
				if (BomberMan.tiles.get(_position.right(i)) instanceof WallTile) break;
				BomberMan.tiles.put(_position.right(i),new ExplosionTile(_position.right(i)));
			}
		}
	}

	void explodeLeft() {
		if (!Board.canMoveLeft(_position)) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.left(i).x >= 0) {
				if (BomberMan.tiles.get(_position.left(i)) instanceof WallTile) break;
				BomberMan.tiles.put(_position.left(i),new ExplosionTile(_position.left(i)));
			}
		}
	}

	void explodeDown() {
		if (!Board.canMoveDown(_position)) return;

		for (int i = _position.y; i <= _explodeRadius; i++) {
			if (_position.down(i).y < Board.TILES_HEIGHT) {
				if (BomberMan.tiles.get(_position.down(i)) instanceof WallTile) break;
				BomberMan.tiles.put(_position.down(i),new ExplosionTile(_position.down(i)));
			}
		}
	}

	void explodeUp() {
		if (!Board.canMoveUp(_position)) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.up(i).y >= 0) {
				if (BomberMan.tiles.get(_position.up(i)) instanceof WallTile) break;
				BomberMan.tiles.put(_position.up(i),new ExplosionTile(_position.up(i)));
			}
		}
	}

	void explode() {
		explodeRight();
		explodeLeft();
		explodeDown();
		explodeUp();
	}

	public void draw(PApplet p) {
		p.fill(0);
		p.ellipse((float) (_position.x + 0.5) * Board.TILE_SIZE, (float) (_position.y + 0.5) * Board.TILE_SIZE, (float) Board.TILE_SIZE / 2, (float) Board.TILE_SIZE / 2);

		if (_timer.finished()) {
			explode();
		}
	}

}
