package bomberman.game;

/**
 * Calculates the position of explosion grahics.
 * 
 */
public class ExplosionCloudCalculator {

	// b1, b2 und b3 sind verschiedene Rauchbilder. b1 ist das unterste b3 das
	// Oberste
	// die Bilder werden uebereinander gelegt und sollen so eine Explosion
	// simulieren

	/**
	 * Contains x-coordinates of the first explosions graphics layer.
	 */
	private final double[] b1X = new double[5];
	/**
	 * Contains y-coordinates of the first explosions graphics layer.
	 */
	private final double[] b1Y = new double[5];

	/**
	 * Contains x-coordinates of the second explosions graphics layer.
	 */
	private final double[] b2X = new double[5];
	/**
	 * Contains y-coordinates of the second explosions graphics layer.
	 */
	private final double[] b2Y = new double[5];

	/**
	 * Contains x-coordinates of the third explosions graphics layer.
	 */
	private final double[] b3X = new double[5];
	/**
	 * Contains y-coordinates of the third explosions graphics layer.
	 */
	private final double[] b3Y = new double[5];

	/**
	 * Reciprocal value of movement speed of first cloud layer.
	 */
	private double move = 1;
	/**
	 * Reciprocal value of movement speed of second cloud layer.
	 */
	private double move2 = 1;
	/**
	 * Reciprocal value of movement speed of third cloud layer.
	 */
	private double move3 = 1;

	/**
	 * Resize factor.
	 */
	private double smaller = 1;

	/**
	 * Constructs the ExplosionCloudCalculator object and initializes the
	 * coordinates of the graphics.
	 * 
	 * @param xPar
	 *            x-coordinate of the center of the tile in bombradius
	 * @param yPar
	 *            y-coordinate of the center of the tile in bombradius
	 */
	public ExplosionCloudCalculator(final double xPar, final double yPar) {

		for (int i = 0; i < 5; i++) {

			this.b1X[i] = xPar;
			this.b2X[i] = xPar;
			this.b3X[i] = xPar;

			this.b1Y[i] = yPar;
			this.b2Y[i] = yPar;
			this.b3Y[i] = yPar;
		}
	}

	/**
	 * Returns specified element of B1X array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B1X array
	 */
	public double getB1X(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b1X[iPar];
	}

	/**
	 * Returns specified element of B2X array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B2X array
	 */
	public double getB2X(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b2X[iPar];
	}

	/**
	 * Returns specified element of B3X array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B3X array
	 */
	public double getB3X(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b3X[iPar];
	}

	/**
	 * Returns specified element of B1Y array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B1Y array
	 */
	public double getB1Y(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b1Y[iPar];
	}

	/**
	 * Returns specified element of B2Y array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B2Y array
	 */
	public double getB2Y(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b2Y[iPar];
	}

	/**
	 * Returns specified element of B3Y array, or the first or last one if out
	 * of bounds.
	 * 
	 * @param iPar
	 *            position in array
	 * @return value in B3Y array
	 */
	public double getB3Y(int iPar) {

		if (iPar < 0) {
			iPar = 0;
		}
		if (iPar > 4) {
			iPar = 4;
		}
		return b3Y[iPar];
	}

	/**
	 * Moves every graphic of the first layer.
	 */
	public void b1move() {

		for (int i = 1; i < 3; i++) {

			double x = b1X[i];
			b1X[i] = x + (1 / (-move)) * 4;
		}

		for (int i = 3; i < 5; i++) {

			double x = b1X[i];
			b1X[i] = x + (1 / (move)) * 4;
		}

		double y = b1Y[1];
		b1Y[1] = y + (1 / (move) * 3) * (-1);

		y = b1Y[2];
		b1Y[2] = y + (1 / (move) * 3) * (1);

		y = b1Y[3];
		b1Y[3] = y + (1 / (move) * 3) * (1);

		y = b1Y[4];
		b1Y[4] = y + (1 / (move) * 3) * (-1);

		move += 3;

	}

	/**
	 * Moves every graphic of the second layer.
	 */
	public void b2move() {

		for (int i = 1; i < 3; i++) {

			double x = b2X[i];
			b2X[i] = x + (1 / (-move2)) * 4;
		}

		for (int i = 3; i < 5; i++) {

			double x = b2X[i];
			b2X[i] = x + ((1 / (move2)) * 6);
		}

		double y = b2Y[1];
		b2Y[1] = y + (1 / (move2) * 7) * (-1);

		y = b2Y[0];
		y = b1Y[0] = y + (1 / move2) * -8;

		y = b1Y[2];
		b2Y[2] = y + (1 / (move2) * 7) * (1);

		y = b2Y[3];
		b2Y[3] = y + (1 / (move2) * 7) * (1);

		y = b2Y[4];
		b2Y[4] = y + (1 / (move2) * 7) * (-1);

		move2 += 3;
	}

	/**
	 * Moves every graphic of the third layer.
	 */
	public void b3move() {

		double x = b3X[0];
		b3X[0] = x + (1 / (move3)) * 2;

		x = b3X[1];
		b3X[1] = x - (1 / (move3)) * 1.6;

		x = b3X[2];
		b3X[2] = x + (1 / (move3) * 1) * 2.1;

		x = b3X[3];
		b3X[3] = x - (1 / (move3)) * 2;

		x = b3X[4];
		b3X[4] = x - (1 / (move3)) * 2;

		double y = b3Y[0];
		b3Y[0] = y - (1 / (move3)) * 2.1;

		y = b3Y[1];
		b3Y[1] = y - (1 / (move3)) * 2;

		y = b3Y[2];
		b3Y[2] = y + (1 / (move3)) * 2;

		y = b3Y[3];
		b3Y[3] = y - (1 / (move3)) * 2;

		y = b3Y[4];
		b3Y[4] = y + (1 / (move3)) * 1.8;

		move3 += 0.3;

	}

	/**
	 * Get movement speed of clouds.
	 * 
	 * @return reciprocal value of movement speed.
	 */
	public double getMove() {
		return move;
	}

	/**
	 * Returns current resize factor.
	 * 
	 * @return current resize factor.
	 */
	public double getSmaller() {
		return smaller;
	}

	/**
	 * Resizes the graphics by a constant factor.
	 */
	public void setSmaller() {
		double x = smaller;
		smaller = x * 0.985;
	}

}
