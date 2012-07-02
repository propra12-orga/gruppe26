package bomberman.game;

/**
 * Wall just has its Position X and Y
 */
public class Wall {
	/**
	 * X array-position of Wall.
	 */
	final private int X;
	/**
	 * Y array-position of Wall.
	 */
	final private int Y;

	/**
	 * @param X
	 *            - X-Position
	 * @param Y
	 *            - Y-Position
	 */
	public Wall(final int X, final int Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * @return X-Position of Wall
	 */
	public int getX() {
		return X;
	}

	/**
	 * @return Y-Position of Wall
	 */
	public int getY() {
		return Y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wall))
			return false;
		final Wall that = (Wall) obj;
		return (this.X == that.X && this.Y == that.Y);
	}

	@Override
	public int hashCode() {
		return 100 * X + Y;
	}
}
