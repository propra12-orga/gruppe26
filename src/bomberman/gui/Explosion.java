package bomberman.gui;

import java.util.ArrayList;
import java.util.List;

import bomberman.game.ExplosionCloudCalculator;

/**
 * Draws explosions.
 */
public class Explosion {

	/**
	 * Contains ExplosionCloudCalculator concerning a position that is currently
	 * bomfed.
	 */
	private final List<ExplosionCloudCalculator> bombs;

	/**
	 * Constructs the Explosion object.
	 */
	public Explosion() {
		bombs = new ArrayList<ExplosionCloudCalculator>();
	}

	/**
	 * Adds the specified location in pixels to the explosions to be drawn.
	 * 
	 * @param xPar
	 *            x-coordinate of center of explosion in pixels.
	 * @param yPar
	 *            y-coordinate of center of explosion in pixels.
	 */
	public void dropBomb(final double xPar, final double yPar) {
		ExplosionCloudCalculator bomb = new ExplosionCloudCalculator(xPar, yPar);
		bombs.add(bomb);
	}

	/**
	 * Draws the explosion clouds.
	 */
	public void drawBombs() {

		ExplosionCloudCalculator bomb;

		for (int i = 0; i < bombs.size(); i++) {

			bomb = bombs.get(i);

			for (int k = 0; k < 5; k++) {

				if (bomb.getMove() < 200) {

					StdDraw.picture(bomb.getB1X(k), bomb.getB1Y(k),
							"graphics/Bomb1.png");
					StdDraw.picture(bomb.getB2X(k), bomb.getB2Y(k),
							"graphics/Bomb2.png");
					StdDraw.picture(bomb.getB3X(k), bomb.getB3Y(k),
							"graphics/Bomb3.png");
				} else {
					StdDraw.picture(bomb.getB1X(k), bomb.getB1Y(k),
							"graphics/Bomb1.png", 40 * bomb.getSmaller(),
							34 * bomb.getSmaller());
					StdDraw.picture(bomb.getB2X(k), bomb.getB2Y(k),
							"graphics/Bomb2.png", 35 * bomb.getSmaller(),
							33 * bomb.getSmaller());
					StdDraw.picture(bomb.getB3X(k), bomb.getB3Y(k),
							"graphics/Bomb3.png", 17 * bomb.getSmaller(),
							14 * bomb.getSmaller());

					bomb.setSmaller();
				}

				bomb.b1move();
				bomb.b2move();
				bomb.b3move();

			}

			if (bomb.getSmaller() < 0.1) {
				bombs.remove(i);

			}

		}

	}

}
