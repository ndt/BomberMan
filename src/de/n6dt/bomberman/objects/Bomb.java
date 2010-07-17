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

import de.n6dt.bomberman.Board;
import de.n6dt.bomberman.BomberMan;
import de.n6dt.bomberman.Position;
import de.n6dt.bomberman.Timer;
import de.n6dt.bomberman.tiles.BlockTile;
import de.n6dt.bomberman.tiles.ExplosionTile;
import de.n6dt.bomberman.tiles.Tile;
import de.n6dt.bomberman.tiles.WallTile;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class Bomb extends Tile implements IObject {

	final int DURATION = 3;

	Timer _timer;
	int _explodeRadius;

	public Bomb(Position position) {
		super(position);
		_timer = new Timer(DURATION);
	}

	void explodeRight() {
		if (_position.x + 1 >= Board.TILES_WIDTH) return;
		if (BomberMan.board.tiles.get(_position.right()) instanceof BlockTile) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.right(i).x < Board.TILES_WIDTH) {     
				if (BomberMan.board.tiles.get(_position.right(i)) instanceof WallTile) break;
				BomberMan.board.tiles.put(_position.right(i),new ExplosionTile(_position.right(i)));
			}
		}
	}

	void explodeLeft() {
		if (_position.x - 1 < 0) return;
		if (BomberMan.board.tiles.get(_position.left()) instanceof BlockTile) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.left(i).x >= 0) {
				if (BomberMan.board.tiles.get(_position.left(i)) instanceof WallTile) break;
				BomberMan.board.tiles.put(_position.left(i),new ExplosionTile(_position.left(i)));
			}
		}
	}

	void explodeDown() {
		if (_position.y + 1 >= Board.TILES_HEIGHT) return;
		if (BomberMan.board.tiles.get(_position.down()) instanceof BlockTile) return;

		for (int i = _position.y; i <= _explodeRadius; i++) {
			if (_position.down(i).y < Board.TILES_HEIGHT) {
				if (BomberMan.board.tiles.get(_position.down(i)) instanceof WallTile) break;
				BomberMan.board.tiles.put(_position.down(i),new ExplosionTile(_position.down(i)));
			}
		}
	}

	void explodeUp() {
		if (_position.y - 1 < 0) return;
		if (BomberMan.board.tiles.get(_position.up()) instanceof BlockTile) return;

		for (int i = 1; i <= _explodeRadius; i++) {
			if (_position.up(i).y >= 0) {
				if (BomberMan.board.tiles.get(_position.up(i)) instanceof WallTile) break;
				BomberMan.board.tiles.put(_position.up(i),new ExplosionTile(_position.up(i)));
			}
		}
	}

	void explode() {
		explodeRight();
		explodeLeft();
		explodeDown();
		explodeUp();
	}

	public void draw() {
		BomberMan p = BomberMan.getP();
		
		p.fill(0);
		p.ellipse((float) (_position.x + 0.5) * Board.TILE_SIZE, (float) (_position.y + 0.5) * Board.TILE_SIZE, (float) Board.TILE_SIZE / 2, (float) Board.TILE_SIZE / 2);

		if (_timer.finished()) {
			explode();
		}

	}

}
