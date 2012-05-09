package bomberman;

import bomberman.game.Board;
import bomberman.game.Controls;
import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.Game;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;

public class Menu {
	public void startGame() {
		final Game g = setupGame();
		g.start();
		System.out.println("Game over.");
	}

	public Game setupGame() {
		final int TILESIZE = 50;

		final Board b = new Board(15, 30);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(
				b.getField(), TILESIZE);
		final GameGui gui = new GameGui(b.getField(), TILESIZE, eac);
		final Exit exit = new Exit(21, 10, TILESIZE);
		final Controls controls = new Controls(b, TILESIZE);

		final Game g = new Game(controls, exit, eac, gui);

		return g;
	}
}
