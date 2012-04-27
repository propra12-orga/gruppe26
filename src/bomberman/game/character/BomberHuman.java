package bomberman.game.character;

public class BomberHuman {
	private final boolean human;
	private int posX;
	private int posY;
	private int speed = 4;

	public BomberHuman(final boolean human, final int posX, final int posY) {
		if (posX < 0 || posY < 0)
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomberhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
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

	// TODO: only sign is relevant, don't want to use boolean though.
	public void moveHorizontally(final int direction) {
		posX = (direction > 0) ? posX + speed : posX - speed;
	}

	public void moveVertically(final int direction) {
		posY = (direction > 0) ? posY + speed : posY - speed;
	}

	public boolean isHuman() {
		return human;
	}
}
