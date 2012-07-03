package bomberman.game;

/**
 * Calculates Cloudeffect for explosion
 * 
 */
public class ExplosionCloudCalculator {

	// b1, b2 und b3 sind verschiedene Rauchbilder. b1 ist das unterste b3 das
	// Oberste
	// die Bilder werden uebereinander gelegt und sollen so eine Explosion
	// simulieren

	public double[] b1X = new double[5];
	public double[] b1Y = new double[5];

	public double[] b2X = new double[5];
	public double[] b2Y = new double[5];

	public double[] b3X = new double[5];
	public double[] b3Y = new double[5];

	public double move = 1;
	public double move2 = 1;
	public double move3 = 1;

	public double smaller = 1;

	/**
	 * @param xPar
	 * @param yPar
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
	 * @param iPar
	 * @return
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
	 * @param iPar
	 * @return
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
	 * @param iPar
	 * @return
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
	 * @param iPar
	 * @return
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
	 * @param iPar
	 * @return
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
	 * @param iPar
	 * @return
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
	 * 
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
	 * 
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
	 * 
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
	 * @return
	 */
	public double getMove() {

		return move;
	}

	/**
	 * @return
	 */
	public double getSmaller() {

		return smaller;
	}

	/**
	 * 
	 */
	public void setSmaller() {

		double x = smaller;
		smaller = x * 0.985;

	}

}
