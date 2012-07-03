package bomberman.game;

import java.util.HashMap;
import java.util.Map;

import bomberman.game.objects.EPowerUps;
import bomberman.game.objects.Exit;
import bomberman.game.objects.PowerUp;
import bomberman.gui.GameGui;

/**
 * 
 * Level class can construct a new level out of a bomferman levelfile
 * 
 */
public class Level {
	/**
	 * Saves the field, i.e. collisions.
	 */
	final int[][] board;
	/**
	 * Saves the exit.
	 */
	final Exit ex;

	/**
	 * Constructor for a level
	 * 
	 * @param board
	 *            - board of the level
	 * @param ex
	 *            - exit f the level
	 */
	public Level(final int[][] board, final Exit ex) {
		this.board = board;
		this.ex = ex;
	}

	/**
	 * gets a board-object of the new level
	 * 
	 * @return board object
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * get exit of the constructed level
	 * 
	 * @return exit of level
	 */
	public Exit getEx() {
		return ex;
	}

	/**
	 * Create new Game from Level
	 * 
	 * @return Game-Object constructed through the level-file, a new
	 *         Board-Object
	 */
	public Game createGameFromLevel() {
		final Board b = new Board(board);
		final int TILESIZE = Settings.TILESIZE;
		final Controls c = new Controls(b, TILESIZE);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(board,
				TILESIZE);

		return new Game(c, ex, eac, new GameGui(board, TILESIZE, eac),
				new Board(board));
	}

	/**
	 * generate destructible Walls with powerups under them randomly select one
	 * of three possible powerups
	 * 
	 * @return map with pairs of wall(key) and powerup under the wall(value)
	 */
	public Map<Wall, PowerUp> generatePowerUps() {
		final Map<Wall, PowerUp> powerUps = new HashMap<Wall, PowerUp>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!(board[i][j] == 2)
						|| !(Math.random() < Settings.POWERUPCHANCE)) {
					continue;
				}

				final Wall ie = new Wall(j, i);
				PowerUp pu;
				final int d = (int) (Math.random() * 3);

				switch (d) {
				case 0:
					pu = new PowerUp(EPowerUps.BOMBRANGE);
					break;

				case 1:
					pu = new PowerUp(EPowerUps.BOMBRATIO);
					break;

				default:
					pu = new PowerUp(EPowerUps.SPEEDUP);
					break;
				}

				powerUps.put(ie, pu);
			}
		}

		return powerUps;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("# bomferman level file\nspawn 0 0\nexit "
				+ ex.getArrayPosX() + " " + ex.getArrayPosY() + "\nboard\n");
		for (int i = board.length - 1; i >= 0; i--) {
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]);
				if (j != board[0].length - 1)
					sb.append(" ");
			}
			if (i != 0)
				sb.append("\n");
		}
		return sb.toString();
	}
}
