package bomberman.game.character;

public class BomberHuman {
	private final boolean human;
	private int posX;
	private int posY;
	private int speed;

	public BomberHuman(final boolean human, final int posX, final int posY,
			final int speed) {
		if (posX < 0 || posY < 0 || speed < 0)
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomberhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void boostSpeed(final int offset) {
		// TODO: maybe we will use this as inverted controls powerdown.
		// if (speed + offset <= 0)
		// throw new IllegalArgumentException("negative speed is not allowed");
		speed += offset;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void moveHorizontally(final int offset) {
		posX += offset * speed;
	}

	public void moveVertically(final int offset) {
		posY += offset * speed;
	}

	public boolean isHuman() {
		return human;
	}
}
