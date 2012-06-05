package bomberman.game.character;

import java.util.List;

import bomberman.game.Network;
import bomberman.game.objects.Bomb;

public class BomberHuman {
	private final boolean human;
	private int posX;
	private int posY;
	private int speed = 1;
	private Network nw;
	private String move = "mv 25 75";
	private String bomb = "bomb 0";

	public BomberHuman(final boolean human, final int posX, final int posY) {
		if (posX < 0 || posY < 0)
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomberhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
	}

	public BomberHuman(final int posX, final int posY, final Network nw,
			final boolean server) {
		this.posX = posX;
		this.posY = posY;
		this.nw = nw;
		this.human = true;
	}

	public void addMove() {
		move = "mv " + posX + " " + posY;
	}

	public void addBombStatus(final boolean bombDrop) {
		if (bombDrop) {
			bomb = "bomb 1";
		} else {
			bomb = "bomb 0";
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void getNetworkMovement(final List<Bomb> bombs) {
		if (nw == null)
			return;
		nw.read(this, bombs);
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

	public void moveRight() {
		posX += speed;
	}

	public void moveLeft() {
		posX -= speed;
	}

	public void moveUp() {
		posY += speed;
	}

	public void moveDown() {
		posY -= speed;
	}

	public void moveHorizontally(final int offset) {
		posX += offset;
	}

	public void moveVertically(final int offset) {
		posY += offset;
	}

	public boolean isHuman() {
		return human;
	}

	public String getMove() {
		return move;
	}

	public String getBomb() {
		return bomb;
	}

	public void setPosX(final int posX) {
		this.posX = posX;
	}

	public void setPosY(final int posY) {
		this.posY = posY;
	}

}
