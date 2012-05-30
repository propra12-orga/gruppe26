package bomberman.game;

import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.StdDraw;

/**
 * @author Jan
 *
 */
public class Controls {

	private final int[][] field;
	private final int TILESIZE;
	private final int height;
	private final int width;

	// private final int COLLISIONBOX = 10;
	private final int COLLISIONBOX = 0;
	private final int BOMBTICKS = 300;
	private final int BOMBTHRESH = 100;
	private int ticksSinceLastBomb = 0;

	public Controls(final Board board, final int TILESIZE) {
		this.field = board.getField();
		this.TILESIZE = TILESIZE;
		this.height = board.getHeight();
		this.width = board.getWidth();
	}

	public boolean canMoveThere(final char direction, final BomberHuman b) {
		final char[] enabledDirections = { 'w', 'a', 's', 'd' };
		if (!arrayContainsChar(enabledDirections, direction))
			throw new IllegalArgumentException(
					"this method does not support this key input");

		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrayPosXP = getArrayPos(posX+10);
		final int arrayPosYP = getArrayPos(posY+5);
		final int arrayPosXN = getArrayPos(posX-10);
		final int arrayPosYN = getArrayPos(posY-5);
		if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width || arrayPosYP >= height)
			return false;
		
		final int speed = b.getSpeed();
		System.out.println(posX + " " + posY);
		// FIXME: okay, that's dirty here. refactor me.
		switch (direction) {
		case 'w':
			if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width || arrayPosYP >= height) break;
			if((field[arrayPosYP][arrayPosXN]==0)&&(field[arrayPosYP][arrayPosXP]==0)){
				return true;
			}
			break;
		case 's':
			if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width || arrayPosYP >= height) break;
			if ((field[arrayPosYN][arrayPosXN]==0)&&(field[arrayPosYN][arrayPosXP]==0)){
				return true;
			}
			break;
		case 'a':
			if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width || arrayPosYP >= height) break;
			if((field[arrayPosYP][arrayPosXN]==0)&&(field[arrayPosYN][arrayPosXN]==0)){
				return true;
			}
			break;
		case 'd':
			if (arrayPosXN < 0 || arrayPosYN < 0 || arrayPosXP >= width || arrayPosYP >= height) break;
			if((field[arrayPosYP][arrayPosXP]==0)&&(field[arrayPosYN][arrayPosXP]==0)){
				return true;
			}
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

	public int getArrayPos(final int pos) {
		if (pos < 0)
			return -1;
		return pos / TILESIZE;
	}

	public void doSomethingWithInput(final BomberHuman bman,
			final List<Bomb> bombs) {

		// if (StdDraw.hasNextKeyTyped()) {
		// final char c = StdDraw.nextKeyTyped();

		// switch (c) {
		if (StdDraw.typedKeys[Settings.P1_UP]) {
			if (canMoveThere('w', bman))
				bman.moveUp();
//			else
//				bman.moveVertically(TILESIZE - 1 - COLLISIONBOX
//						- (bman.getPosY() % TILESIZE));
		}
		if (StdDraw.typedKeys[Settings.P1_DOWN]) {
			if (canMoveThere('s', bman))
				bman.moveDown();
//			else
//				bman.moveVertically(COLLISIONBOX - (bman.getPosY() % TILESIZE));
		}
		if (StdDraw.typedKeys[Settings.P1_LEFT]) {
			if (canMoveThere('a', bman))
				bman.moveLeft();
//			else
//				bman.moveHorizontally(COLLISIONBOX
//						- (bman.getPosX() % TILESIZE));
		}
		if (StdDraw.typedKeys[Settings.P1_RIGHT]) {
			if (canMoveThere('d', bman))
				bman.moveRight();
//			else
//				bman.moveHorizontally(TILESIZE - 1 - COLLISIONBOX
//						- (bman.getPosX() % TILESIZE));
		}
		// if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == 'e')
		if (StdDraw.typedKeys[Settings.P1_BOMB])
			if (ticksSinceLastBomb < 0) {
				dropBomb(bman, bombs);
				ticksSinceLastBomb = BOMBTHRESH;
			}

		ticksSinceLastBomb--;
	}

	private void dropBomb(final BomberHuman bman, final List<Bomb> bombs) {
		final int posX = bman.getPosX();
		final int posY = bman.getPosY();
		final Bomb b = new Bomb(posX, posY, BOMBTICKS);
		bombs.add(b);
	}
}
