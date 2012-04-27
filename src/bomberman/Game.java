package bomberman;

import messing.around.StdDraw;
import bomberman.game.Board;
import bomberman.game.character.BomberHuman;

public class Game {

	private final int TILESIZE = 50;
	private final Board field;
	private final BomberHuman bman;

	public Game() {
		field = new Board(15, 30);
		bman = new BomberHuman(true, 0, 0);
	}

	public void start() {
		final int width = field.getField()[0].length;
		final int height = field.getField().length;
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
		StdDraw.clear();
		StdDraw.circle(bman.getPosX(), bman.getPosY(), 5);
	}
}
