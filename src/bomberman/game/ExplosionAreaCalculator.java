package bomberman.game;

import java.util.ArrayList;
import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public class ExplosionAreaCalculator {

	final private int[][] field;
	final private int height;
	final private int width;
	final private int TILESIZE;
	final private int RADIUS = 5;

	public ExplosionAreaCalculator(final int[][] field, final int TILESIZE) {
		this.field = field;
		this.width = field[0].length;
		this.height = field.length;
		this.TILESIZE = TILESIZE;
	}

	public boolean isInExplosionArea(Bomb b, BomberHuman bman) {
		final int posX = bman.getPosX();
		final int posY = bman.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		return isInExplosionArea(b, arrPosX, arrPosY);
	}

	public boolean isInExplosionArea(Bomb b, Bomb c) {
		final int posX = c.getPosX();
		final int posY = c.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		return isInExplosionArea(b, arrPosX, arrPosY);
	}

	public boolean isInExplosionArea(Bomb b, final int X, final int Y) {

		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrPosX = getArrayPos(posX);
		final int arrPosY = getArrayPos(posY);

		if (X != arrPosX && Y != arrPosY)
			return false;

		if (arrPosX + RADIUS < X || arrPosY + RADIUS < Y
				|| arrPosX - RADIUS > X || arrPosY - RADIUS > Y)
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

	public int getLeftBoundsOfExplosion(final int posX, final int posY) {
		int i = posX;
		while (field[posY][i] == 0 && i > 0 && i + RADIUS > posX)
			i--;
		return i;
	}

	public int getRightBoundsOfExplosion(final int posX, final int posY) {
		int i = posX;
		while (field[posY][i] == 0 && i < width - 1 && i < posX + RADIUS)
			i++;
		return i;
	}

	public int getUpperBoundsOfExplosion(final int posX, final int posY) {
		int i = posY;
		while (field[i][posX] == 0 && i < height - 1 && i < posY + RADIUS)
			i++;
		return i;
	}

	public int getLowerBoundsOfExplosion(final int posX, final int posY) {
		int i = posY;
		while (field[i][posX] == 0 && i > 0 && i + RADIUS > posY)
			i--;
		return i;
	}

	public void affectedWalls(final Bomb b) {
		final List<Wall> walls = new ArrayList<Wall>();
		final int bombX = getArrayPos(b.getPosX());
		final int bombY = getArrayPos(b.getPosY());

		final int leftBoundsOfExplosion = getLeftBoundsOfExplosion(bombX, bombY);
		if (leftBoundsOfExplosion >= 0
				&& bombX - leftBoundsOfExplosion <= RADIUS)
			walls.add(new Wall(leftBoundsOfExplosion, bombY));

		final int rightBoundsOfExplosion = getRightBoundsOfExplosion(bombX,
				bombY);
		if (rightBoundsOfExplosion < width
				&& rightBoundsOfExplosion - bombX <= RADIUS)
			walls.add(new Wall(rightBoundsOfExplosion, bombY));

		final int upperBoundsOfExplosion = getUpperBoundsOfExplosion(bombX,
				bombY);
		if (upperBoundsOfExplosion < height
				&& upperBoundsOfExplosion - bombY <= RADIUS)
			walls.add(new Wall(bombX, upperBoundsOfExplosion));

		final int lowerBoundsOfExplosion = getLowerBoundsOfExplosion(bombX,
				bombY);
		if (lowerBoundsOfExplosion >= 0
				&& bombX - lowerBoundsOfExplosion <= RADIUS)
			walls.add(new Wall(bombX, lowerBoundsOfExplosion));

		for (Wall wall : walls)
			if (field[wall.getY()][wall.getX()] == 2)
				field[wall.getY()][wall.getX()] = 0;

	}

	// TODO: copied from Controls.java; maybe we could refactor again?
	public int getArrayPos(final int pos) {
		if (pos < 0)
			return -1;
		return pos / TILESIZE;
	}
}
