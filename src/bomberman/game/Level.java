package bomberman.game;

import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;

public class Level {
	final int[][] board;
	final Exit ex;

	public Level(final int[][] board, final Exit ex) {
		this.board = board;
		this.ex = ex;
	}

	public int[][] getBoard() {
		return board;
	}

	public Exit getEx() {
		return ex;
	}

	public Game createGameFromLevel() {
		final Board b = new Board(board);
		final int TILESIZE = Settings.TILESIZE;
		final Controls c = new Controls(b, TILESIZE);
		final ExplosionAreaCalculator eac = new ExplosionAreaCalculator(board,
				TILESIZE);

		return new Game(c, ex, eac, new GameGui(board, TILESIZE, eac));
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("# bomferman level file\nspawn 0 0\nexit "
				+ ex.getArrayPosX() + " " + ex.getArrayPosY() + "\nboard\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]);
				if (j != board[0].length - 1)
					sb.append(" ");
			}
			if (i != board.length)
				sb.append("\n");
		}
		return sb.toString();
	}
}
