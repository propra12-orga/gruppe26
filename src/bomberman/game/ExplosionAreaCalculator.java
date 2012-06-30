package bomberman.game;

import java.util.ArrayList;
import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

/**
 * ExplosionAreCalculator
 * 
 * Controls the explosion: How far do they get, which walls/Bomfermans will be
 * affected?
 * 
 */
public class ExplosionAreaCalculator {

	// Variablen, wie eigentlich immer:
	// Spielfeld-array mit Kollisionen
	// Hoehe und Breite des Feldes
	// Groesse der Spielfeldzellen
	// Radius der Bombe
	final private int[][] field;
	final private int height;
	final private int width;
	final private int TILESIZE;
	private int radius = 2;

	/**
	 * 
	 * 
	 * @param field
	 *            - fieldarray with walls
	 * @param TILESIZE
	 *            - size of the fieldtiles
	 */
	public ExplosionAreaCalculator(final int[][] field, final int TILESIZE) {
		this.field = field;
		this.width = field[0].length;
		this.height = field.length;
		this.TILESIZE = TILESIZE;
	}

	/**
	 * Checks whether Bomferman bman will be hit by bomb b
	 * 
	 * @param b
	 *            bomb-object
	 * @param bman
	 *            bomferman-object
	 * @return true or false
	 */
	public boolean isInExplosionArea(final Bomb b, final BomberHuman bman) {
		final int posX = bman.getPosX();
		final int posY = bman.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		return isInExplosionArea(b, arrPosX, arrPosY);
	}

	/**
	 * Checks whether Bomb c will be hit by bomb b
	 * 
	 * @param b
	 *            - exploding bomb
	 * @param c
	 *            - to be exploded bomb
	 * @return true or false
	 */
	public boolean isInExplosionArea(final Bomb b, final Bomb c) {
		final int posX = c.getPosX();
		final int posY = c.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		return isInExplosionArea(b, arrPosX, arrPosY);
	}

	/**
	 * Checks whether X,Y is in Explosionarea
	 * 
	 * @param b
	 *            - exploding bomb
	 * @param X
	 *            - X-Coordinate to check
	 * @param Y
	 *            - Y-Coordinate to check
	 * @return true or false
	 */
	public boolean isInExplosionArea(final Bomb b, final int X, final int Y) {

		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		if (X != arrPosX && Y != arrPosY)
			return false;

		if (arrPosX + radius < X || arrPosY + radius < Y
				|| arrPosX - radius > X || arrPosY - radius > Y)
			return false;

		for (int i = arrPosX; i <= X; i++)
			if (field[arrPosY][i] != 0)
				return false;

		for (int i = arrPosX; i >= X; i--)
			if (field[arrPosY][i] != 0)
				return false;

		for (int j = arrPosY; j <= Y; j++)
			if (field[j][arrPosX] != 0)
				return false;

		for (int j = arrPosY; j >= Y; j--)
			if (field[j][arrPosX] != 0)
				return false;

		return true;
	}

	/**
	 * Gets left bounds of explosion of bomb at posx posy
	 * 
	 * @param posX
	 *            - xposition of bomb
	 * @param posY
	 *            - ypositon of bomb
	 * @return [int] last tile where the explosion has effect (left of bomb)
	 */
	public int getLeftBoundsOfExplosion(final int posX, final int posY) {
		int i = posX;
		while (field[posY][i] == 0 && i > 0 && i + radius > posX) {
			i--;
		}
		return i;
	}

	/**
	 * Gets right bounds of explosion of bomb at posx posy
	 * 
	 * @param posX
	 *            - xposition of bomb
	 * @param posY
	 *            - ypositon of bomb
	 * @return [int] last tile where the explosion has effect (left of bomb)
	 */
	public int getRightBoundsOfExplosion(final int posX, final int posY) {
		int i = posX;
		while (field[posY][i] == 0 && i < width - 1 && i < posX + radius) {
			i++;
		}
		return i;
	}

	/**
	 * Gets upper bounds of explosion of bomb at posx posy
	 * 
	 * @param posX
	 *            - xposition of bomb
	 * @param posY
	 *            - ypositon of bomb
	 * @return [int] last tile where the explosion has effect (above bomb)
	 */
	public int getUpperBoundsOfExplosion(final int posX, final int posY) {
		int i = posY;
		while (field[i][posX] == 0 && i < height - 1 && i < posY + radius) {
			i++;
		}
		return i;
	}

	/**
	 * Gets lower bounds of explosion of bomb at posx posy
	 * 
	 * @param posX
	 *            - xposition of bomb
	 * @param posY
	 *            - ypositon of bomb
	 * @return [int] last tile where the explosion has effect (under bomb)
	 */
	public int getLowerBoundsOfExplosion(final int posX, final int posY) {
		int i = posY;
		while (field[i][posX] == 0 && i > 0 && i + radius > posY) {
			i--;
		}
		return i;
	}

	/**
	 * Check if Walls in explosionarea are affected and destroys them
	 * 
	 * @param b
	 *            - Bomb
	 */
	public void affectedWalls(final Bomb b) {
		final List<Wall> walls = new ArrayList<Wall>();
		final int bombX = getArrayPos(b.getPosX());
		final int bombY = getArrayPos(b.getPosY());

		final int leftBoundsOfExplosion = getLeftBoundsOfExplosion(bombX, bombY);
		if (leftBoundsOfExplosion >= 0
				&& bombX - leftBoundsOfExplosion <= radius) {
			walls.add(new Wall(leftBoundsOfExplosion, bombY));
		}

		final int rightBoundsOfExplosion = getRightBoundsOfExplosion(bombX,
				bombY);
		if (rightBoundsOfExplosion < width
				&& rightBoundsOfExplosion - bombX <= radius) {
			walls.add(new Wall(rightBoundsOfExplosion, bombY));
		}

		final int upperBoundsOfExplosion = getUpperBoundsOfExplosion(bombX,
				bombY);
		if (upperBoundsOfExplosion < height
				&& upperBoundsOfExplosion - bombY <= radius) {
			walls.add(new Wall(bombX, upperBoundsOfExplosion));
		}

		final int lowerBoundsOfExplosion = getLowerBoundsOfExplosion(bombX,
				bombY);
		if (lowerBoundsOfExplosion >= 0
				&& bombY - lowerBoundsOfExplosion <= radius) {
			walls.add(new Wall(bombX, lowerBoundsOfExplosion));
		}

		for (Wall wall : walls) {
			if (field[wall.getY()][wall.getX()] == 2) {
				field[wall.getY()][wall.getX()] = 0;
			}
		}
	}

	public void bombRangeUp() {
		radius++;
	}

	// TODO: copied from Controls.java; maybe we could refactor again?
	public int getArrayPos(final int pos) {
		if (pos < 0)
			return -1;
		return pos / TILESIZE;
	}
}
