package bomberman.gui;


public class MenuGui {

	/*
	 * TEST CLASS FOR TESTING PURPOSE ONLY - REFACTOR CODE TO NON-STATIC
	 */

	// I know, I'm lazy but I can not remember Numbers, just Varnames.
	final private double exit_size_X = 50;
	final private double exit_size_Y = 20;
	final private double newgame_size_X = 70;
	final private double newgame_size_Y = 20;

	final private double text_x_newgame = 500;
	final private double text_y_newgame = 400;
	final private double text_x_exit = 500;
	final private double text_y_exit = 300;

	public MenuGui() {
		StdDraw.setCanvasSize(512, 512); // magic standard constants
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
	}

	// we'll use the wrapper, because I want to exit via the Menu class. The
	// wrapper gives us a third option, i.e. null
	public Boolean gameStarted() {
		StdDraw.resetMousePressedStatus();
		// Game in progress?

		StdDraw.clear();
		// Maus ueber Button "New Game?"
		if (isMouseOverNewGame())
			mouseOverNewGameActions();
		else if (isMouseOverExit())
			mouseOverExitActions();
		else {
			StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
			StdDraw.text(text_x_exit, text_y_exit, "Exit");
		}
		StdDraw.show();
		if (isMouseOverNewGame() && StdDraw.mousePressed()) {
			return true;
		}
		if (isMouseOverExit() && StdDraw.mousePressed())
			return null;

		return false;
	}

	public void mouseOverNewGameActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
	}

	public void mouseOverExitActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
	}

	public boolean isMouseOverNewGame() {
		return (StdDraw.mouseX() <= text_x_newgame + newgame_size_X)
				&& (StdDraw.mouseX() >= text_x_newgame - newgame_size_X)
				&& (StdDraw.mouseY() <= text_y_newgame + newgame_size_Y)
				&& (StdDraw.mouseY() >= text_y_newgame - newgame_size_Y);
	}

	public boolean isMouseOverExit() {
		return (StdDraw.mouseX() <= text_x_exit + exit_size_X)
				&& (StdDraw.mouseX() >= text_x_exit - exit_size_X)
				&& (StdDraw.mouseY() <= text_y_exit + exit_size_Y)
				&& (StdDraw.mouseY() >= text_y_exit - exit_size_Y);
	}
}
