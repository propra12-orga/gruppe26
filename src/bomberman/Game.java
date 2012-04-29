package bomberman;

import java.util.ArrayList;
import java.util.List;

import messing.around.StdDraw;
import bomberman.game.Board;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.Gui;

public class Game {

	private final int TILESIZE = 50;
	private final Board board;
	private final BomberHuman bman;
	private final int height;
	private final int width;
	private final List<Bomb> bombs = new ArrayList<Bomb>();
	private final Gui gui;

	public Game(final int height, final int width) {
		board = new Board(height, width);
		bman = new BomberHuman(true, 0, 0);
		this.height = board.getHeight();
		this.width = board.getWidth();
		this.gui = new Gui(board.getField(), TILESIZE, bombs, bman);
	}

	public void start() {
		gui.initialize();
		// DEBUG mode
		bman.boostSpeed(10);

		while (true) {
			doSomethingWithInput(bman);
			manageBombs();
			gui.drawWalls();
			gui.drawBomber();
			gui.drawBombs();
			gui.finish();
		}
	}

	private void manageBombs() {
		final List<Integer> exploded = new ArrayList<Integer>();

		int count = 0;

		for (Bomb b : bombs) {
			b.tick();
			final boolean exists = b.isStillThere();

			if (!exists) {
				exploded.add(count);
			}
			count++;
		}

		for (Integer integer : exploded) {
			bombs.remove((int) integer);
		}
	}

	private void doSomethingWithInput(final BomberHuman bman) {

		if (StdDraw.hasNextKeyTyped()) {
			final char c = StdDraw.nextKeyTyped();

			switch (c) {
			case 'w':
				if (canMoveThere('w', bman))
					bman.moveUp();
				else
					bman.moveVertically(TILESIZE - 1
							- (bman.getPosY() % TILESIZE));
				break;
			case 's':
				if (canMoveThere('s', bman))
					bman.moveDown();
				else
					bman.moveVertically(-(bman.getPosY() % TILESIZE));
				break;
			case 'a':
				if (canMoveThere('a', bman))
					bman.moveLeft();
				else
					bman.moveHorizontally(-(bman.getPosX() % TILESIZE));
				break;
			case 'd':
				if (canMoveThere('d', bman))
					bman.moveRight();
				else
					bman.moveHorizontally(TILESIZE - 1
							- (bman.getPosX() % TILESIZE));
				break;
			case 'e':
				dropBomb(bman);
			}
		}
	}

	private void dropBomb(BomberHuman bman) {
		final int posX = bman.getPosX();
		final int posY = bman.getPosY();
		final Bomb b = new Bomb(posX, posY, 300);
		bombs.add(b);
	}

	public boolean canMoveThere(final char direction, final BomberHuman b) {
		final char[] enabledDirections = { 'w', 'a', 's', 'd' };
		if (!arrayContainsChar(enabledDirections, direction))
			throw new IllegalArgumentException(
					"this method does not support this key input");

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
