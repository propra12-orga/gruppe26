package bomberman;

import messing.around.StdDraw;
import bomberman.game.Board;
import bomberman.game.character.BomberHuman;

public class Game {

	final Board field;
	final BomberHuman bman;

	public Game() {
		field = new Board(15, 15);
		bman = new BomberHuman(true, 0, 0, 1);
	}

	public void start() {
		StdDraw.setXscale(0, 500);
		StdDraw.setYscale(0, 500);

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
		StdDraw.circle(bman.getPosX(), bman.getPosY(), 2);
	}
}
