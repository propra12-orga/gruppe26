package bomberman.game;

/**
 * Game settings
 */
public class Settings {

	/**
	 * ASCII code for up-arrow key
	 */
	public static int P1_UP = 38;
	/**
	 * ASCII code for down-arrow key
	 */
	public static int P1_DOWN = 40;
	/**
	 * ASCII code for left-arrow key
	 */
	public static int P1_LEFT = 37;
	/**
	 * ASCII code for right-arrow key
	 */
	public static int P1_RIGHT = 39;
	/**
	 * ASCII code for spacebar
	 */
	public static int P1_BOMB = 32;
	/**
	 * ASCII code for p key
	 */
	public static int P1_PAUSE = 80;

	/**
	 * Minimum length of a tick in milliseconds.
	 */
	public static final int MINTICKLENGTH = 10;
	/**
	 * Number of ticks a Bomb is ticking.
	 */
	final public static int TIMERCONSTANT = 1600 / MINTICKLENGTH;
	/**
	 * Side length of a single square-tile.
	 */
	final public static int TILESIZE = 50;
	/**
	 * Starting speed of Bomferman.
	 */
	public static final int BMANSPEED = MINTICKLENGTH * 2 / 5;

	/**
	 * Bomferman's collisionbox to his right side.
	 */
	public final static int collisionRightOffset = 10;
	/**
	 * Bomferman's collisionbox to his left side.
	 */
	public final static int collisionLeftOffset = -10;
	/**
	 * Bomferman's collisionbox to his upper side.
	 */
	public final static int collisionTopOffset = 5;
	/**
	 * Bomferman's collisionbox to his lower side.
	 */
	public final static int collisionBottomOffset = -5;

	/**
	 * Path where the savegame is located.
	 */
	final public static String saveGamePath = "saveGame.sav";
	/**
	 * Chance a wall contains a powerup.
	 */
	public static final double POWERUPCHANCE = 0.3;
	/**
	 * Path where the highscore is located.
	 */
	public static final String highScorePath = "highScore.hsc";
	/**
	 * Standard-IP for the network (hit return on empty input).
	 */
	public static String server = "127.0.0.1";

}
