package bomberman;

import bomberman.game.Game;

public class Menu {
	public void startGame() {
		final Game g = new Game(15, 30);
		g.start();
		System.out.println("Game over.");
	}
}
