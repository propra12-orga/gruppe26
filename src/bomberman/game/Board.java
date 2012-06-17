package bomberman.game;

/**
 * Boardclass
 * 
 * contains constructors for creating a board out of an array or two int values
 * (i.e height an width)
 * 
 */
public class Board {

	final private int height;
	final private int width;
	final private int[][] field;

	/**
	 * Constructs a Board out of a given integer array
	 * 
	 * @param arr
	 *            two-dimensional int array containing collisions
	 */
	public Board(final int[][] arr) {
		this.height = arr.length;
		this.width = arr[0].length;
		this.field = arr;
	}

	/**
	 * Constructs a board out of given height and width
	 * 
	 * @param height
	 *            - height of board(tiles)
	 * @param width
	 *            - width of board(tiles)
	 */
	public Board(final int height, final int width) {
		if (height < 2 || width < 2)
			throw new IllegalArgumentException();

		// if height or width is an even number, make it uneven.
		// So no wall-blocks get stuck at the border

		this.height = (height % 2 == 1) ? height : height + 1;
		this.width = (width % 2 == 1) ? width : width + 1;
		this.field = new int[this.height][this.width];

		initializeCollisions();
	}

	/**
	 * Collisions: Bomberman can not pass walls Currently sets non walkable -
	 * cells with 1: The core of every wall is unwalkable... Cells with 2:
	 * Destructible wall!(still unwalkable)
	 */
	private void initializeCollisions() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 && j == 0 || i == 0 && j == 1 || i == 1 && j == 0)
					continue;

				if (Math.random() < 0.2)
					field[i][j] = 2;
			}
		}

		for (int i = 1; i < height; i += 2) {
			for (int j = 1; j < width; j += 2) {
				field[i][j] = 1;
			}
		}
	}

	/**
	 * get height of field
	 * 
	 * @return height in tiles(int)
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * get width of field
	 * 
	 * @return width in tiles (int)
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * get field
	 * 
	 * @return a 2D int array with collisions set...
	 */
	public int[][] getField() {
		return field;
	}

}
