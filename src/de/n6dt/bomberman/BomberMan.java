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
package de.n6dt.bomberman;

import processing.core.PApplet;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class BomberMan extends PApplet {

	private static final long serialVersionUID = 1L;

	public final static int WINDOW_WIDTH = 300;
	public final static int WINDOW_HEIGHT = 300;
	public final static int TILE_SIZE = 30;
	public final static int FRAME_RATE = 60;
	public final static int EXPLODE_R = 3;

	public static boolean stop = false;

	public static BomberMan p;
	
	ArrayList<Player> players;
	
	Board board;

	public BomberMan() {
		super();
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		players = new ArrayList<Player>();
		players.add( new Player(board, new Point(0, 0), "Spieler 1", 0x0050FF) );
		players.add( new Player(board, new Point(8, 8), "Spieler 2", 0x00FF00) );
		p = this;
	}
	
	public static BomberMan getP() {
		return p;
	}

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		smooth();
		frameRate(FRAME_RATE);
	}

	public void keyPressed() {
		switch (key) {

		case '4':
			players.get(0).moveLeft();
			break;

		case '6':
			players.get(0).moveRight();
			break;

		case '8':
			players.get(0).moveUp();
			break;

		case '5':
			players.get(0).moveDown();
			break;

		case '0':
			players.get(0).setBomb();
			break;

		case 'a':
			players.get(1).moveLeft();
			break;

		case 'd':
			players.get(1).moveRight();
			break;

		case 'w':
			players.get(1).moveUp();
			break;

		case 's':
			players.get(1).moveDown();
			break;

		case ' ':
			players.get(1).setBomb();
			break;

		default:
			break;
		}
	}

	public void draw() {
		
		for (Player player : players) {
			player.draw(this);
		}	
		
		if (!stop) {
			players.get(0)._board.checkBombAndPlayer(players.get(0));
			players.get(1)._board.checkBombAndPlayer(players.get(1));

			background(255);
			board.display();
		} else {
			background(0);
		}
	}
}
