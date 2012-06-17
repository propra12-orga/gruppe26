package bomberman.game.character;

import java.util.List;

import bomberman.game.Network;
import bomberman.game.objects.Bomb;

/**
 * Human Player Bomferman
 * 
 */
public class BomberHuman {
	private final boolean human;
	private int posX;
	private int posY;
	private int speed = 1;
	private Network nw;
	private String move = "mv 25 75";
	private String bomb = "bomb 0";

	/**
	 * @param human
	 * @param posX
	 * @param posY
	 */
	public BomberHuman(final boolean human, final int posX, final int posY) {
		if (posX < 0 || posY < 0)
			throw new IllegalArgumentException(
					"got negative arguments while constructing Bomberhuman");
		this.human = human;
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param nw
	 * @param server
	 */
	public BomberHuman(final int posX, final int posY, final Network nw,
			final boolean server) {
		this.posX = posX;
		this.posY = posY;
		this.nw = nw;
		this.human = true;
	}

	/**
	 * 
	 */
	public void addMove() {
		move = "mv " + posX + " " + posY;
	}

	/**
	 * @param bombDrop
	 */
	public void addBombStatus(final boolean bombDrop) {
		if (bombDrop) {
			bomb = "bomb 1";
		} else {
			bomb = "bomb 0";
		}
	}

	/**
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param bombs
	 */
	public void getNetworkMovement(final List<Bomb> bombs) {
		if (nw == null)
			return;
		nw.read(this, bombs);
	}

	/**
	 * @param offset
	 */
	public void boostSpeed(final int offset) {
		// TODO: maybe we will use this as inverted controls powerdown.
		// if (speed + offset <= 0)
		// throw new IllegalArgumentException("negative speed is not allowed");
		speed += offset;
	}

	/**
	 * @return
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 */
	public void moveRight() {
		posX += speed;
	}

	/**
	 * 
	 */
	public void moveLeft() {
		posX -= speed;
	}

	/**
	 * 
	 */
	public void moveUp() {
		posY += speed;
	}

	/**
	 * 
	 */
	public void moveDown() {
		posY -= speed;
	}

	/**
	 * @param offset
	 */
	public void moveHorizontally(final int offset) {
		posX += offset;
	}

	/**
	 * @param offset
	 */
	public void moveVertically(final int offset) {
		posY += offset;
	}

	/**
	 * @return
	 */
	public boolean isHuman() {
		return human;
	}

	/**
	 * @return
	 */
	public String getMove() {
		return move;
	}

	/**
	 * @return
	 */
	public String getBomb() {
		return bomb;
	}

	/**
	 * @param posX
	 */
	public void setPosX(final int posX) {
		this.posX = posX;
	}

	/**
	 * @param posY
	 */
	public void setPosY(final int posY) {
		this.posY = posY;
	}

}
