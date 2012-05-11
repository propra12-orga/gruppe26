package bomberman.gui;

import messing.around.StdDraw;
import bomberman.game.Game;

public class MenuGui {

	public static void main(String args[]) {
		// Game in progress?
		boolean game_stopped = false;
		// I know, I'm lazy but I can not remember Numbers, just Varnames.
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		final double text_x_newgame = 500;
		final double text_y_newgame = 400;
		final double text_x_exit = 500;
		final double text_y_exit = 300;
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.show();
		while (game_stopped == true) {
			if ((StdDraw.mousePressed() == true)
					&& ((StdDraw.mouseX() == text_x_newgame) && (StdDraw
							.mouseY() == text_y_newgame))) {
				final Game g = new Game(null, null, null, null);
				g.start();
			}
			if ((StdDraw.mousePressed() == true)
					&& ((StdDraw.mouseX() == text_x_exit) && (StdDraw.mouseY() == text_y_exit)))
				game_stopped = true;
		}
	}
}
