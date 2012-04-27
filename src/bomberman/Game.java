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
		this.height = height;
		this.width = width;
	}

	public void start() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);

		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);

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
				bman.moveVertically(1);
				break;
			case 's':
				bman.moveVertically(-1);
				break;
			case 'a':
				bman.moveHorizontally(-1);
				break;
			case 'd':
				bman.moveHorizontally(1);
				break;
			}
		}
	}

	private void drawSomething() {
		// StdDraw.clear();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board.getField()[j][i] != 0)
					StdDraw.filledSquare(i * 50 + 25, j * 50 + 25, 25);
			}
		}
		StdDraw.circle(bman.getPosX(), bman.getPosY(), 5);
	}
}
