package bomberman.game.objects;

/**
 * Contains everything regarding Bombs Constructor, Methods for controlling
 * explosions Each Bomb has its x- and y-position, a timer, its 4
 * explosion-borders and a variable indicating whether the bomb actually exists
 * 
 */
public class Bomb {

	/**
	 * x-Position of Bomb
	 */
	final private int posX;
	/**
	 * y-Position of Bomb
	 */
	final private int posY;
	/**
	 * maximum timer that has to go down until bomb goes bomf
	 */
	final private int maxTimer;
	/**
	 * current timer of Bomb (ticks until Bomb goes bomf)
	 */
	private int timer;
	/**
	 * is Bomb existant? (i.e not gone bomf)
	 */
	private boolean exists = true;

	/**
	 * left explosion border
	 */
	private int explosionBorderLeft;
	/**
	 * right explosion border
	 */
	private int explosionBorderRight;
	/**
	 * top explosion border
	 */
	private int explosionBorderTop;
	/**
	 * bottom explosion border
	 */
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
	private void explode() {
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
