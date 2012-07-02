package bomberman.game.objects;

/**
 * Exit
 * 
 * Contains constructor to get an exit at arrayposx, arrayposy
 * 
 */
public class Exit {

	/**
	 * X array-position.
	 */
	private final int posX;
	/**
	 * Y array-position.
	 */
	private final int posY;
	/**
	 * Size of a tile.
	 */
	private final int TILESIZE;

	/**
	 * Constructs an Exit at arrayposx, arrayposy with given TILESIZE
	 * 
	 * @param arrayPosX
	 *            - x-position of exit in the boardarray
	 * @param arrayPosY
	 *            - y-position of exit in the boardarray
	 * @param TILESIZE
	 *            - size of tiles
	 */
	public Exit(final int arrayPosX, final int arrayPosY, final int TILESIZE) {
		this.posX = arrayPosX;
		this.posY = arrayPosY;
		this.TILESIZE = TILESIZE;
	}

	/**
	 * @return - (int) x-position of exit in tiles
	 */
	public int getPosX() {
		return posX * TILESIZE + TILESIZE / 2;
	}

	/**
	 * @return - (int) y-position of exit in tiles
	 */
	public int getPosY() {
		return posY * TILESIZE + TILESIZE / 2;
	}

	/**
	 * @return - (int) x-position of exit in the boardarray
	 */
	public int getArrayPosX() {
		return posX;
	}

	/**
	 * @return - (int) y-position of exit in the boardarray
	 */
	public int getArrayPosY() {
		return posY;
	}

}
