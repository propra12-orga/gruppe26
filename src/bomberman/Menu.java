package bomberman;

import java.io.IOException;
import java.net.UnknownHostException;

import bomberman.game.Board;
import bomberman.game.Controls;
import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.Game;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;
import bomberman.gui.MenuGui;

public class Menu {

	/**
	 * Starts the game.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public void startGame() throws UnknownHostException, IOException {

		while (true) {
			final MenuGui mg = new MenuGui();
			Boolean b = Boolean.FALSE;
			while (b == Boolean.FALSE) {
				b = mg.gameStarted();
			}
			if (b == null) {
				System.exit(0);
			}

			final Game g = setupGame();
			g.start();
		}

	}

	/**
	 * Setup Game: initializes a new board with (currently) fixed parameters
	 * also sets up game-logic (i.e Explosion Calculator, Controls and GUI)
	 * 
	 * @return game Object
	 */
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
