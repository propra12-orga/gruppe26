package bomberman.game;

import java.util.ArrayList;
import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.Gui;

public class Game {

	private final int EXPLOSION_RADIUS = 5; // in tiles at the moment
	private final int TILESIZE = 50;

	private final Board board;
	private final BomberHuman bman;
	private final List<Bomb> bombs = new ArrayList<Bomb>();
	private final Gui gui;
	private final Controls controls;
	private final ExplosionAreaCalculator eac;

	public Game(final int height, final int width) {
		board = new Board(height, width);
		bman = new BomberHuman(true, 0, 0);
		this.eac = new ExplosionAreaCalculator(board.getField(),
				EXPLOSION_RADIUS, TILESIZE);
		this.gui = new Gui(board.getField(), TILESIZE, bombs, bman, eac);
		this.controls = new Controls(board, TILESIZE, bombs);
	}

	public void start() {
		gui.initialize();
		// DEBUG mode
		bman.boostSpeed(10);

		loop();
	}

	private void loop() {
		while (true) {
			controls.doSomethingWithInput(bman);
			manageBombs();

			gui.draw();
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
			final Bomb b = bombs.get(integer);

			if (!b.isCurrentlyExploding())
				bombs.remove((int) integer);
		}
	}

}
