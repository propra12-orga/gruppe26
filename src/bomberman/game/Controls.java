package bomberman.game;

import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.StdDraw;

/**
 * Controls
 * 
 * Obviously does anything regards controlling Bomferman
 * 
 * Collisions, movement, pausing the game
 */
public class Controls {

	// Wie immer Variabeln:
	// spielfeldarray
	// zellengroesse
	// hoehe des Spielfelds
	// breite des Spielfelds
	/**
	 * Gameboard-array(int)
	 */
	private final int[][] field;
	/**
	 * Size of Tiles (int)
	 */
	private final int TILESIZE;
	/**
	 * height of field (int)
	 */
	private final int height;
	/**
	 * width of field (int)
	 */
	private final int width;
	// Ausserdem: Bombeneinstellungen.
	/**
	 * Ticks until Bomb goes bomf (int)
	 */
	private final int BOMBTICKS = Settings.TIMERCONSTANT;
	/**
	 * Anzahl an Ticks, bis eine neue Bombe gesetzt werden darf.
	 */
	private int bombthresh = 60;
	/**
	 * Anzahl der ticks, die seit dem Setzen der Letzten Bombe vergangen sind.
	 */
	private int ticksSinceLastBomb = 0;

	/**
	 * Gets the field with height*width and tilesize
	 * 
	 * @param board
	 *            - gameboard
	 * @param TILESIZE
	 *            - size of boardtiles
	 */
	public Controls(final Board board, final int TILESIZE) {
		this.field = board.getField();
		this.TILESIZE = TILESIZE;
		this.height = board.getHeight();
		this.width = board.getWidth();
	}

	/**
	 * Checks if Bomferman, "b", can move in direction, "direction"
	 * 
	 * @param direction
	 *            - (w a s d)
	 * @param b
	 *            - Bomb
	 * @return true or false
	 */
	public boolean canMoveThere(final char direction, final BomberHuman b) {
		final char[] enabledDirections = { 'w', 'a', 's', 'd' };
		if (!arrayContainsChar(enabledDirections, direction))
			throw new IllegalArgumentException(
					"this method does not support this key input");
		// Variablen: PosX und Y sowie der Speed vom Bomferman.
		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int eps = b.getSpeed();
		// Kollisionssystem:
		// Checkt anhand von 4 Punkten von Bomferman, ob sich dieser bewegen
		// kann
		// 4 Punkte...

		final int arrayPosXP = getArrayPos(posX + Settings.collisionRightOffset);
		final int arrayPosYP = getArrayPos(posY + Settings.collisionTopOffset);
		final int arrayPosXN = getArrayPos(posX + Settings.collisionLeftOffset);
		final int arrayPosYN = getArrayPos(posY
				+ Settings.collisionBottomOffset);
		// Kollisionssystem: Aufgrund von haesslichen Bugs noch eine kleine
		// Korrektur
		// Verhindert, dass unser Bomferman in Ecken stecken bleibt
		final int arrayPosXP_EPS = getArrayPos(posX
				+ Settings.collisionRightOffset + eps);
		final int arrayPosYP_EPS = getArrayPos(posY
				+ Settings.collisionTopOffset + eps);
		final int arrayPosXN_EPS = getArrayPos(posX
				+ Settings.collisionLeftOffset - eps);
		final int arrayPosYN_EPS = getArrayPos(posY
				+ Settings.collisionBottomOffset - eps);
		// Wenn Bomferman ausserhalb der Grenzen ist: false zurueckgeben
		if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width
				|| arrayPosYP >= height)
			return false;
		// Kollsionssystem Kern:
		// Je nach Richtung wird hier ein anderer Punkt abgefragt.
		// W: interessant sind die oberen beiden Punkte
		// S: die unteren Punkte zaehlen.
		// A: Punkt oben links und unten links werden abgefragt.
		// D: Punkt oben rechts und unten rechts werden abgefragt.

		switch (direction) {
		case 'w':
			// Ist einer der Punkte entweder ausserhalb des Feldes...
			if (arrayPosXN < 0 || arrayPosXP >= width
					|| arrayPosYP_EPS >= height) {
				break;
			}
			// ODER wuerde beim bewegen in einer Wand enden: false
			// zurueckgeben...
			if ((field[arrayPosYP_EPS][arrayPosXN] == 0)
					&& (field[arrayPosYP_EPS][arrayPosXP] == 0))
				return true;
			break;
		case 's':
			if (arrayPosXN < 0 || arrayPosYN_EPS < 0 || arrayPosXP >= width) {
				break;
			}
			if ((field[arrayPosYN_EPS][arrayPosXN] == 0)
					&& (field[arrayPosYN_EPS][arrayPosXP] == 0))
				return true;
			break;
		case 'a':
			if (arrayPosXN_EPS < 0 || arrayPosYN < 0 || arrayPosYP >= height) {
				break;
			}
			if ((field[arrayPosYP][arrayPosXN_EPS] == 0)
					&& (field[arrayPosYN][arrayPosXN_EPS] == 0))
				return true;
			break;
		case 'd':
			if (arrayPosYN < 0 || arrayPosXP_EPS >= width
					|| arrayPosYP >= height) {
				break;
			}
			if ((field[arrayPosYP][arrayPosXP_EPS] == 0)
					&& (field[arrayPosYN][arrayPosXP_EPS] == 0))
				return true;
			break;
		}

		return false;
	}

	private boolean arrayContainsChar(final char[] arr, final char c) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c)
				return true;
		}
		return false;
	}

	/**
	 * Converts the position of Bomferman into array-coordinates...
	 * 
	 * @param pos
	 *            - position to be converted
	 * @return (int) converted position
	 */
	public int getArrayPos(final int pos) {
		if (pos < 0)
			return -1;
		return pos / TILESIZE;
	}

	/**
	 * Listens to keyboard-inputs and lets Bomfermans act accordingly
	 * 
	 * @param bman
	 *            - Bomferman to be controlled
	 * @param bombs
	 *            - Bombs
	 */
	public void doSomethingWithInput(final BomberHuman bman,
			final List<Bomb> bombs) {

		if (StdDraw.typedKeys[Settings.P1_UP]) {
			if (canMoveThere('w', bman)) {
				bman.moveUp();
			}
		}
		if (StdDraw.typedKeys[Settings.P1_DOWN]) {
			if (canMoveThere('s', bman)) {
				bman.moveDown();
			}
		}
		if (StdDraw.typedKeys[Settings.P1_LEFT]) {
			if (canMoveThere('a', bman)) {
				bman.moveLeft();
			}
		}
		if (StdDraw.typedKeys[Settings.P1_RIGHT]) {
			if (canMoveThere('d', bman)) {
				bman.moveRight();
			}
		}

		bman.addMove();

		if (StdDraw.typedKeys[Settings.P1_BOMB]) {
			if (ticksSinceLastBomb < 0) {
				dropBomb(bman, bombs);
				bman.addBombStatus(true);
				ticksSinceLastBomb = bombthresh;
			} else {
				bman.addBombStatus(false);
			}
		}

		if (StdDraw.typedKeys[Settings.P1_PAUSE]) {
			StdDraw.typedKeys[Settings.P1_PAUSE] = false;
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.text(TILESIZE * width / 2, TILESIZE * height / 2,
					"GAME PAUSED");
			StdDraw.show();
			StdDraw.setPenColor(StdDraw.BLACK);
			while (!StdDraw.typedKeys[Settings.P1_PAUSE]) {
			}
			StdDraw.typedKeys[Settings.P1_PAUSE] = false;
		}

		ticksSinceLastBomb--;
	}

	/**
	 * Create or Add Bombs to Field
	 * 
	 * @param bman
	 *            - Owner of the Bombs
	 * @param bombs
	 *            - Bombs
	 */
	private void dropBomb(final BomberHuman bman, final List<Bomb> bombs) {
		final int posX = bman.getPosX();
		final int posY = bman.getPosY();
		final Bomb b = new Bomb(posX, posY, BOMBTICKS);
		bombs.add(b);
	}

	public void bombRatioUp() {
		bombthresh -= 5;
	}
}
