package bomberman.gui;

/**
 * 
 * GUI after winning / loosing a game. User can restart or exit the game by
 * clicking one option.
 * 
 * @author Jan
 */
public class MenuGui {

	/**
	 * Useless Comments my ass... Variables:
	 * 
	 * size of exit text (x and y) size of "Newgame?"-text
	 * 
	 * Positions of the texts
	 * 
	 * I know I'm lazy but I just can renember var-names, no values...
	 */
	final private double exit_size_X = 50;
	final private double exit_size_Y = 20;
	final private double newgame_size_X = 70;
	final private double newgame_size_Y = 20;

	final private double text_x_newgame = 500;
	final private double text_y_newgame = 400;
	final private double text_x_exit = 500;
	final private double text_y_exit = 300;

	final private double controls_size_X = 60;
	final private double controls_size_Y = 20;
	final private double text_x_controls = 500;
	final private double text_y_controls = 200;

	/**
	 * Set up the GUI
	 */
	public MenuGui() {
		StdDraw.setCanvasSize(512, 512); // magic standard constants
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
	}

	// we'll use the wrapper, because I want to exit via the Menu class. The
	// wrapper gives us a third option, i.e. null
	/**
	 * Draws the Menu with texts "New Game?" and "Exit" Starts a new game or
	 * closes the window
	 * 
	 * @return true or false, null
	 */
	public Boolean gameStarted() {
		StdDraw.resetMousePressedStatus();
		// Game in progress?

		StdDraw.clear();
		// Maus ueber Button "New Game?"
		if (isMouseOverNewGame())
			mouseOverNewGameActions();
		else if (isMouseOverExit())
			mouseOverExitActions();
		else if (isMouseOverControls())
			mouseOverControlsActions();
		else {
			StdDraw.text(text_x_controls, text_y_controls, "Controls");
			StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
			StdDraw.text(text_x_exit, text_y_exit, "Exit");
		}
		StdDraw.show();
		if (isMouseOverNewGame() && StdDraw.mousePressed()) {
			return true;
		} else if (isMouseOverExit() && StdDraw.mousePressed()) {
			return null;
		} else if (isMouseOverControls() && StdDraw.mousePressed()) {
			showControls();
		}

		return false;
	}

	/**
	 * Hover effect for "New Game?" text
	 */
	private void mouseOverNewGameActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_controls, text_y_controls, "Controls");

	}

	/**
	 * Hover effect for "Exit" text
	 * 
	 */
	private void mouseOverExitActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
	}

	private void mouseOverControlsActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
	}

	/**
	 * Checks if the mouse is over the "New Game?" text
	 * 
	 * @return true or false
	 */
	private boolean isMouseOverNewGame() {
		return (StdDraw.mouseX() <= text_x_newgame + newgame_size_X)
				&& (StdDraw.mouseX() >= text_x_newgame - newgame_size_X)
				&& (StdDraw.mouseY() <= text_y_newgame + newgame_size_Y)
				&& (StdDraw.mouseY() >= text_y_newgame - newgame_size_Y);
	}

	/**
	 * Checks if the mouse is over the "exit" text
	 * 
	 * @return true or false
	 */
	private boolean isMouseOverExit() {
		return (StdDraw.mouseX() <= text_x_exit + exit_size_X)
				&& (StdDraw.mouseX() >= text_x_exit - exit_size_X)
				&& (StdDraw.mouseY() <= text_y_exit + exit_size_Y)
				&& (StdDraw.mouseY() >= text_y_exit - exit_size_Y);
	}

	private boolean isMouseOverControls() {
		return (StdDraw.mouseX() <= text_x_controls + controls_size_X)
				&& (StdDraw.mouseX() >= text_x_controls - controls_size_X)
				&& (StdDraw.mouseY() <= text_y_controls + controls_size_Y)
				&& (StdDraw.mouseY() >= text_y_controls - controls_size_Y);
	}

	private final double back_size_X = 60;
	private final double back_size_Y = 20;
	private final double text_x_back = 500;
	private final double text_y_back = 100;

	private void showControls() {
		StdDraw.resetMousePressedStatus();

		boolean back = false;
		while (!back) {
			StdDraw.clear();

			drawKeyOutlines();
			drawAwesomeArrowsAndLabels();

			if (mouseOverBack())
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.text(text_x_back, text_y_back, "back");
			if (mouseOverBack())
				StdDraw.setPenColor(StdDraw.BLACK);

			StdDraw.show();
			if (mouseOverBack() && StdDraw.mousePressed())
				back = true;
		}
	}

	private void drawKeyOutlines() {
		StdDraw.square(400, 700, 40);
		StdDraw.square(500, 700, 40);
		StdDraw.square(600, 700, 40);
		StdDraw.square(500, 800, 40);
		StdDraw.rectangle(500, 500, 300, 40);
	}

	private void drawAwesomeArrowsAndLabels() {
		StdDraw.text(320, 700, "left");
		StdDraw.text(400, 700, "←");
		StdDraw.text(500, 620, "down");
		StdDraw.text(500, 700, "↓");
		StdDraw.text(700, 700, "right");
		StdDraw.text(600, 700, "→");
		StdDraw.text(500, 870, "up");
		StdDraw.text(500, 800, "̣↑");
		StdDraw.text(500, 500, "spacebar");
		StdDraw.text(500, 420, "plant bomb");
	}

	private boolean mouseOverBack() {
		return (StdDraw.mouseX() <= text_x_back + back_size_X)
				&& (StdDraw.mouseX() >= text_x_back - back_size_X)
				&& (StdDraw.mouseY() <= text_y_back + back_size_Y)
				&& (StdDraw.mouseY() >= text_y_back - back_size_Y);

	}
}
