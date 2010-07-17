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

/**
 * @author nicolas nieswandt <nicolas.nieswandt@googlemail.com>
 * 
 */
public class BomberMan extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int WINDOW_WIDTH = 300;
	public final static int WINDOW_HEIGHT = 300;
	public final static int TILE_SIZE = 30;
	public final static int FRAME_RATE = 60;
	public final static int EXPLODE_R = 3;

	boolean stop = false;

	public static BomberMan p;
	
	Board board;
	Player dau1;
	Player dau2;

	public BomberMan() {
		super();
		board = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);
		dau1 = new Player(board, new Point(0, 0), "Spieler 1");
		dau2 = new Player(board, new Point(8, 8), "Spieler 2");
		p = this;
	}
	
	public static BomberMan getP() {
		return p;
	}

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		smooth();
	}

	public void keyPressed() {
		switch (key) {

		case '4':
			dau1.moveLeft();
			break;

		case '6':
			dau1.moveRight();
			break;

		case '8':
			dau1.moveUp();
			break;

		case '5':
			dau1.moveDown();
			break;

		case '0':
			dau1.setBomb();
			break;

		case 'a':
			dau2.moveLeft();
			break;

		case 'd':
			dau2.moveRight();
			break;

		case 'w':
			dau2.moveUp();
			break;

		case 's':
			dau2.moveDown();
			break;

		case ' ':
			dau2.setBomb();
			break;

		default:
			break;
		}
	}

	public void draw() {
		if (!stop) {
			dau1.checkBombAndPlayer();
			dau2.checkBombAndPlayer();

			background(255);
			board.display();
		} else {
			background(0);
		}
	}
}
