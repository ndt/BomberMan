package de.n6dt.bomberman.items;

import de.n6dt.bomberman.Position;
import processing.core.PApplet;

public abstract class Item {

	protected Position _position;

	public Item(Position pos) {
		_position = pos;
	}

	abstract public void draw(PApplet p);

	public Position getPosition() {
		return _position;
	}

}