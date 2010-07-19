package de.n6dt.bomberman.tiles;

import processing.core.PApplet;

public interface ITile {

	abstract public boolean isWalkable();

	abstract public void draw(PApplet p);

}