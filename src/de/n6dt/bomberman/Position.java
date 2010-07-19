package de.n6dt.bomberman;

import java.awt.Point;

public class Position extends Point {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827189324782935585L;

	public Position(int x, int y) {
		super(x,y);
	}

	public Position down() {
		return down(1);
	}

	public Position down(int i) {
		return new Position(x, Math.min(y+i, BomberMan.TILES_HEIGHT-1));
	}

	public Position up() {
		return up(1);
	}

	public Position up(int i) {
		return new Position(x, Math.max(y-i, 0));
	}

	public Position right() {
		return right(1);
	}

	public Position right(int i) {
		return new Position(Math.min(x+i, BomberMan.TILES_WIDTH-1), y);
	}

	public Position left() {
		return left(1);
	}

	public Position left(int i) {
		return new Position(Math.max(x-i, 0), y);
	}

}
