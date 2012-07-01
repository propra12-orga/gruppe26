package bomberman.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import bomberman.HighScore;
import bomberman.game.Settings;

/**
 * 
 * GUI after winning / loosing a game. User can restart or exit the game by
 * clicking one option.
 * 
 * @author Jan
 */
public class MenuGui {
	/*
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
	final private double text_y_exit = 100;

	final private double controls_size_X = 60;
	final private double controls_size_Y = 20;
	final private double text_x_controls = 500;
	final private double text_y_controls = 300;

	final private double server_size_X = 70;
	final private double server_size_Y = 20;
	final private double text_x_server = 100;
	final private double text_y_server = 500;

	final private double client_size_X = 70;
	final private double client_size_Y = 20;
	final private double text_x_client = 900;
	final private double text_y_client = 500;

	final private double text_x_load = 500;
	final private double text_y_load = 700;
	final private double load_size_X = 60;
	final private double load_size_Y = 20;

	final private double text_x_score = 500;
	final private double text_y_score = 200;
	final private double score_size_X = 100;
	final private double score_size_Y = 20;

	private boolean savedGameExists = false;

	/**
	 * Set up the GUI
	 */
	public MenuGui() {
		setSizeAndScales();

		try {
			new FileInputStream(Settings.saveGamePath);
			savedGameExists = true;
		} catch (FileNotFoundException swallowed) {
		}

	}

	private void setSizeAndScales() {
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
	 * @return <pre>
	 * int value
	 *  0 | no interaction
	 *  1 | new single player game
	 *  2 | starts server
	 *  3 | starts client
	 * -1 | exits
	 * </pre>
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public int gameStarted() {
		StdDraw.resetMousePressedStatus();
		StdDraw.clear();
		// Maus ueber Button "New Game?"
		if (isMouseOverNewGame()) {
			mouseOverNewGameActions();
		} else if (isMouseOverExit()) {
			mouseOverExitActions();
		} else if (isMouseOverControls()) {
			mouseOverControlsActions();
		} else if (isMouseOverClient()) {
			mouseOverClientActions();
		} else if (isMouseOverServer()) {
			mouseOverServerActions();
		} else if (isMouseOverLoad()) {
			mouseOverLoadActions();
		} else if (isMouseOverScore()) {
			mouseOverScoreActions();
		} else {
			StdDraw.text(text_x_controls, text_y_controls, "Controls");
			StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
			StdDraw.text(text_x_exit, text_y_exit, "Exit");
			StdDraw.text(text_x_server, text_y_server, "Server");
			StdDraw.text(text_x_client, text_y_client, "Client");
			StdDraw.text(text_x_score, text_y_score, "High Score");
			if (savedGameExists)
				StdDraw.text(text_x_load, text_y_load, "Load Game");
		}
		StdDraw.show();
		if (isMouseOverNewGame() && StdDraw.mousePressed()) {
			return 1;
		} else if (isMouseOverExit() && StdDraw.mousePressed()) {
			return -1;
		} else if (isMouseOverControls() && StdDraw.mousePressed()) {
			showControls();
		} else if (isMouseOverServer() && StdDraw.mousePressed()) {
			return 2;
		} else if (isMouseOverClient() && StdDraw.mousePressed()) {
			return 3;
		} else if (savedGameExists && isMouseOverLoad()
				&& StdDraw.mousePressed()) {
			return 4;
		} else if (isMouseOverScore() && StdDraw.mousePressed()) {
			HighScore.printScore();
		}

		return 0;

	}

	/**
	 * Hover effect for "New Game?" text
	 */
	private void mouseOverNewGameActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");
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
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");
	}

	/**
	 * Hover effect for "Controls" text
	 * 
	 */
	private void mouseOverControlsActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");
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

	/**
	 * Checks if the mouse is over the "controls" text
	 * 
	 * @return true or false
	 */
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

	/**
	 * Displays "Controls" menu item
	 */
	private void showControls() {
		StdDraw.resetMousePressedStatus();

		boolean back = false;
		while (!back) {
			StdDraw.clear();

			drawKeyOutlines();
			drawAwesomeArrowsAndLabels();

			if (mouseOverBack()) {
				StdDraw.setPenColor(StdDraw.BOOK_RED);
			}
			StdDraw.text(text_x_back, text_y_back, "back");
			if (mouseOverBack()) {
				StdDraw.setPenColor(StdDraw.BLACK);
			}

			StdDraw.show();
			if (mouseOverBack() && StdDraw.mousePressed()) {
				back = true;
			}
		}
	}

	/**
	 * Draws key outlines for displaying controls
	 */
	private void drawKeyOutlines() {
		StdDraw.square(400, 700, 40);
		StdDraw.square(500, 700, 40);
		StdDraw.square(600, 700, 40);
		StdDraw.square(500, 800, 40);
		StdDraw.square(300, 800, 40);
		StdDraw.rectangle(500, 500, 300, 40);
	}

	/**
	 * Draws arrows and labels for the key outlines
	 * 
	 */
	private void drawAwesomeArrowsAndLabels() {
		StdDraw.text(320, 700, "left");
		StdDraw.text(400, 700, "←");
		StdDraw.text(500, 620, "down");
		StdDraw.text(500, 700, "↓");
		StdDraw.text(700, 700, "right");
		StdDraw.text(600, 700, "→");
		StdDraw.text(500, 870, "up");
		StdDraw.text(310, 870, "pause");
		StdDraw.text(300, 800, "p");
		StdDraw.text(500, 800, "↑");
		StdDraw.text(500, 500, "spacebar");
		StdDraw.text(500, 420, "plant bomb");
	}

	/**
	 * Works like isMouseOverControls()
	 * 
	 * @return true or false
	 */
	private boolean mouseOverBack() {
		return (StdDraw.mouseX() <= text_x_back + back_size_X)
				&& (StdDraw.mouseX() >= text_x_back - back_size_X)
				&& (StdDraw.mouseY() <= text_y_back + back_size_Y)
				&& (StdDraw.mouseY() >= text_y_back - back_size_Y);

	}

	/**
	 * Works like isMouseOverControls()
	 * 
	 * @return true or false
	 */
	private boolean isMouseOverServer() {
		return (StdDraw.mouseX() <= text_x_server + server_size_X)
				&& (StdDraw.mouseX() >= text_x_server - server_size_X)
				&& (StdDraw.mouseY() <= text_y_server + server_size_Y)
				&& (StdDraw.mouseY() >= text_y_server - server_size_Y);
	}

	/**
	 * Works like isMouseOverControls()
	 * 
	 * @return true or false
	 */
	private boolean isMouseOverClient() {
		return (StdDraw.mouseX() <= text_x_client + client_size_X)
				&& (StdDraw.mouseX() >= text_x_client - client_size_X)
				&& (StdDraw.mouseY() <= text_y_client + client_size_Y)
				&& (StdDraw.mouseY() >= text_y_client - client_size_Y);
	}

	/**
	 * Hover effect
	 */
	private void mouseOverClientActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");
	}

	/**
	 * Hover effect
	 */
	private void mouseOverServerActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");

	}

	private boolean isMouseOverLoad() {
		return (StdDraw.mouseX() <= text_x_load + load_size_X)
				&& (StdDraw.mouseX() >= text_x_load - load_size_X)
				&& (StdDraw.mouseY() <= text_y_load + load_size_Y)
				&& (StdDraw.mouseY() >= text_y_load - load_size_Y);

	}

	private boolean isMouseOverScore() {
		return (StdDraw.mouseX() <= text_x_score + score_size_X)
				&& (StdDraw.mouseX() >= text_x_score - score_size_X)
				&& (StdDraw.mouseY() <= text_y_score + score_size_Y)
				&& (StdDraw.mouseY() >= text_y_score - score_size_Y);

	}

	private void mouseOverLoadActions() {
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");
		StdDraw.text(text_x_score, text_y_score, "High Score");

		if (savedGameExists) {
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.text(text_x_load, text_y_load, "Load Game");
			StdDraw.setPenColor(StdDraw.BLACK);
		}
	}

	private void mouseOverScoreActions() {
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.text(text_x_score, text_y_score, "High Score");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(text_x_client, text_y_client, "Client");
		StdDraw.text(text_x_exit, text_y_exit, "Exit");
		StdDraw.text(text_x_newgame, text_y_newgame, "New Game?");
		StdDraw.text(text_x_server, text_y_server, "Server");
		StdDraw.text(text_x_controls, text_y_controls, "Controls");

		if (savedGameExists)
			StdDraw.text(text_x_load, text_y_load, "Load Game");
	}
}
