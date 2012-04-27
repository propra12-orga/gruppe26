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

	public void setSpeed(final int offset) {
		if (speed + offset < 0)
			throw new IllegalArgumentException("negative speed is not allowed");
		speed += offset;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void moveHorizontally(final int offset) {
		posX += offset;
	}

	public void moveVertically(final int offset) {
		posY += offset;
	}
}
