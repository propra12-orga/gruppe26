package bomberman;

import bomberman.game.Board;
import bomberman.game.Controls;
import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.Game;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;
import bomberman.gui.MenuGui;

public class Menu {

	public void startGame() {

		while (true) {
			final MenuGui mg = new MenuGui();
			Boolean b = Boolean.FALSE;
			while (b == Boolean.FALSE) {
				b = mg.gameStarted();
			}
			if (b == null)
				System.exit(0);

			final Game g = setupGame();
			g.start();
		}

	}

	public Game setupGame() {
		final int TILESIZE = 50;

		final Board b = new Board(10, 10);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(
				b.getField(), TILESIZE);
		final GameGui gui = new GameGui(b.getField(), TILESIZE, eac);
		final Exit exit = new Exit(21, 10, TILESIZE);
		final Controls controls = new Controls(b, TILESIZE);

		final Game g = new Game(controls, exit, eac, gui);

		return g;
	}
}
