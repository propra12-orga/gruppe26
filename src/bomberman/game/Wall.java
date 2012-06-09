package bomberman.game;

/**
 * Wall just has its Position X and Y
 */
public class Wall {
	final private int X;
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
	
}
