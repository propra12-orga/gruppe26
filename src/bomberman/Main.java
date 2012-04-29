package bomberman;

import bomberman.game.Game;

public class Main {

	public static void main(String[] args) {
		final Game g = new Game(15, 30);
		g.start();
		System.out.println("Game over.");
	}
}
