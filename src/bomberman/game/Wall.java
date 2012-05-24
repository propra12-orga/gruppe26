package bomberman.game;

public class Wall {
	final private int X;
	final private int Y;
	
	public Wall(final int X, final int Y) {
		this.X = X;
		this.Y = Y;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
	
}
