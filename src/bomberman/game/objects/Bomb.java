package bomberman.game.objects;

public class Bomb {
	/*
	 * TODO: do we want to finalize them? thinking of pushing/kicking bombs...
	 */
	final private int posX;
	final private int posY;
	final private int maxTimer;
	private int timer;
	private boolean exists = true;

	private int explosionBorderLeft;
	private int explosionBorderRight;
	private int explosionBorderTop;
	private int explosionBorderBottom;

	/**
	 * @return [int] bottom border of explosion
	 */
	public int getExplosionBorderBottom() {
		return explosionBorderBottom;
	}

	/**
	 * @return [int] left border of explosion
	 */
	public int getExplosionBorderLeft() {
		return explosionBorderLeft;
	}

	/**
	 * @return [int] right border of explosion
	 */
	public int getExplosionBorderRight() {
		return explosionBorderRight;
	}

	/**
	 * @return [int] top border of explosion
	 */
	public int getExplosionBorderTop() {
		return explosionBorderTop;
	}

	/**
	 * Constructor for Bombobject.
	 * 
	 * @param posX
	 *            - xposition of bomb
	 * @param posY
	 *            - yposition of bomb
	 * @param timer
	 *            - timer of bomb
	 */
	public Bomb(final int posX, final int posY, final int timer) {
		if (timer <= 0 || posX < 0 || posY < 0)
			throw new IllegalArgumentException("setting detonator failed");
		this.posX = posX;
		this.posY = posY;
		this.timer = timer;
		this.maxTimer = timer;
	}

	/**
	 * save the borders of explosions into variables
	 * 
	 * @param left
	 *            - left explosion border
	 * @param right
	 *            - right explosion border
	 * @param top
	 *            - top explosion border
	 * @param bottom
	 *            - bottom explosion border
	 */
	public void saveExplosionBorders(final int left, final int right,
			final int top, final int bottom) {
		explosionBorderLeft = left;
		explosionBorderRight = right;
		explosionBorderTop = top;
		explosionBorderBottom = bottom;
	}

	/**
	 * @return [int] maximum size of timer
	 */
	public int getMaxTimer() {
		return maxTimer;
	}

	/**
	 * @return [int] postion of bomb
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return [int] position of Bomb
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @return [int] timer of the bomb
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * go bomf. let the bomb explode!
	 */
	public void goBomf() {
		timer = (timer > 20) ? 20 : timer;
	}

	/**
	 * counts down the timer of bombs, if it reaches 0, explode!
	 */
	public void tick() {
		timer--;
		if (timer <= 0)
			explode();
	}

	/**
	 * let the bomb explode
	 */
	public void explode() {
		exists = false;
	}

	/**
	 * @return true or false
	 */
	public boolean isStillThere() {
		return exists;
	}

	/**
	 * @return true or false
	 */
	public boolean isCurrentlyExploding() {
		return (timer > -50 && timer <= 0);
	}
}
