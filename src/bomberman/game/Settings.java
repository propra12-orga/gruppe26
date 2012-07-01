package bomberman.game;

/**
 * Controller-Settings
 */
public class Settings {
	/*
	 * These seriously should be self-explanatory
	 */
	public static int P1_UP = 38;
	public static int P1_DOWN = 40;
	public static int P1_LEFT = 37;
	public static int P1_RIGHT = 39;
	public static int P1_BOMB = 32;
	public static int P1_PAUSE = 80;

	public static final int MINTICKLENGTH = 10;
	final public static int TIMERCONSTANT = 1600 / MINTICKLENGTH;
	final public static int TILESIZE = 50;
	public static final int BMANSPEED = MINTICKLENGTH * 2 / 5;

	public final static int collisionRightOffset = 10;
	public final static int collisionLeftOffset = -10;
	public final static int collisionTopOffset = 5;
	public final static int collisionBottomOffset = -5;

	final public static String saveGamePath = "saveGame.sav";
	public static final double POWERUPCHANCE = 0.3;
	public static final String highScorePath = "highScore.hsc";
	public static String server = "127.0.0.1";

}
