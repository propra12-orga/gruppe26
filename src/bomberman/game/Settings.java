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

	final public static int TIMERCONSTANT = 300;
	final public static int TILESIZE = 50;

	public final static int collisionRightOffset = 10;
	public final static int collisionLeftOffset = -10;
	public final static int collisionTopOffset = 5;
	public final static int collisionBottomOffset = -5;

	final public static String saveGamePath = "saveGame.sav";
}
