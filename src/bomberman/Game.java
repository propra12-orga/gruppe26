package bomberman;

import messing.around.StdDraw;
import bomberman.game.Board;
import bomberman.game.character.BomberHuman;

public class Game {

	private final int TILESIZE = 50;
	private final Board board;
	private final BomberHuman bman;
	private final int height;
	private final int width;

	public Game(final int height, final int width) {
		board = new Board(height, width);
		bman = new BomberHuman(true, 0, 0);
		this.height = board.getHeight();
		this.width = board.getWidth();
	}

	public void start() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);

		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);

		// DEBUG mode
		bman.boostSpeed(10);

		while (true) {
			doSomethingWithInput();
			drawSomething();
		}
	}

	private void doSomethingWithInput() {

		if (StdDraw.hasNextKeyTyped()) {
			final char c = StdDraw.nextKeyTyped();

			switch (c) {
			case 'w':
				if (canMoveThere('w', bman))
					bman.moveVertically(1);
				break;
			case 's':
				if (canMoveThere('s', bman))
					bman.moveVertically(-1);
				break;
			case 'a':
				if (canMoveThere('a', bman))
					bman.moveHorizontally(-1);
				break;
			case 'd':
				if (canMoveThere('d', bman))
					bman.moveHorizontally(1);
				break;
			}
		}
	}

	private void drawSomething() {
		StdDraw.clear();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board.getField()[j][i] != 0)
					StdDraw.filledSquare(i * 50 + 25, j * 50 + 25, 25);
				else {
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.filledSquare(i * 50 + 25, j * 50 + 25, 25);
					StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
		StdDraw.circle(bman.getPosX(), bman.getPosY(), 5);
		StdDraw.show();
	}

	public boolean canMoveThere(final char direction, final BomberHuman b) {
		final char[] enabledDirections = { 'w', 'a', 's', 'd' };
		if (!arrayContainsChar(enabledDirections, direction))
			return false;

		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrayPosX = getArrayPos(posX);
		final int arrayPosY = getArrayPos(posY);
		final int speed = b.getSpeed();

		// FIXME: okay, that's dirty here. refactor me.
		switch (direction) {
		case 'w':
			final int newArrayPosY = getArrayPos(posY + speed);
			if (newArrayPosY < height)
				if (board.getField()[newArrayPosY][arrayPosX] == 0)
					return true;
			break;
		case 's':
			final int newArrayPosY2 = getArrayPos(posY - speed);
			if (newArrayPosY2 >= 0)
				if (board.getField()[newArrayPosY2][arrayPosX] == 0)
					return true;
			break;
		case 'a':
			final int newArrayPosX = getArrayPos(posX - speed);
			if (newArrayPosX >= 0)
				if (board.getField()[arrayPosY][newArrayPosX] == 0)
					return true;
			break;
		case 'd':
			final int newArrayPosX2 = getArrayPos(posX + speed);
			if (newArrayPosX2 < width)
				if (board.getField()[arrayPosY][newArrayPosX2] == 0)
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

	public int getArrayPos(final int pos) {
		if (pos < 0)
			return -1;
		return pos / TILESIZE;
	}
}
