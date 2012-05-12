package bomberman.gui;

import messing.around.StdDraw;
import bomberman.game.Game;

public class MenuGui {

	/*
	 * TEST CLASS FOR TESTING PURPOSE ONLY - REFACTOR CODE TO NON-STATIC
	 */

	// I know, I'm lazy but I can not remember Numbers, just Varnames.
	final static double exit_size_X = 50;
	final static double exit_size_Y = 20;
	final static double newgame_size_X = 70;
	final static double newgame_size_Y = 20;

	final static double text_x_newgame = 500;
	final static double text_y_newgame = 400;
	final static double text_x_exit = 500;
	final static double text_y_exit = 300;

	public static void main(String args[]) {
		// Game in progress?
		boolean game_stopped = false;

		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);

		while (game_stopped == false) {
			StdDraw.clear();
			// Maus ueber Button "New Game?"
			if (isMouseOverNewGame()) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
				StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(text_x_exit, text_y_exit, "Exit");
			} else {
				StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
				StdDraw.text(text_x_exit, text_y_exit, "Exit");
			}
			StdDraw.show();
			if ((isMouseOverNewGame() == true)
					&& (StdDraw.mousePressed() == true)) {
				// let's go dummy for now...
				final Game g = new Game(null, null, null, null);
				g.start();

			} else if (isMouseOverExit()) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
				StdDraw.text(text_x_exit, text_y_exit, "Exit");
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
			} else {
				StdDraw.text(text_x_exit, text_y_exit, "Exit");
				StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
			}
			if (isMouseOverExit() && StdDraw.mousePressed()) {
				// %TODO%: close Window on Exit.
			}
		}
	}

	public static boolean isMouseOverNewGame() {
		return (StdDraw.mouseX() <= text_x_newgame + newgame_size_X)
				&& (StdDraw.mouseX() >= text_x_newgame - newgame_size_X)
				&& (StdDraw.mouseY() <= text_y_newgame + newgame_size_Y)
				&& (StdDraw.mouseY() >= text_y_newgame - newgame_size_Y);
	}

	public static boolean isMouseOverExit() {
		return (StdDraw.mouseX() <= text_x_exit + exit_size_X)
				&& (StdDraw.mouseX() >= text_x_exit - exit_size_X)
				&& (StdDraw.mouseY() <= text_y_exit + exit_size_Y)
				&& (StdDraw.mouseY() >= text_y_exit - exit_size_Y);
	}
}
