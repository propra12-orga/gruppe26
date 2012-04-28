package bomberman.game;

public class Board {

	final private int height;
	final private int width;
	final private int[][] field;

	public Board(final int height, final int width) {
		if (height < 2 || width < 2)
			throw new IllegalArgumentException();

		this.height = (height % 2 == 1) ? height : height + 1;
		this.width = (width % 2 == 1) ? width : width + 1;
		this.field = new int[this.height][this.width];

		initializeCollisions();
	}

	private void initializeCollisions() {
		for (int i = 1; i < height; i += 2) {
			for (int j = 1; j < width; j += 2) {
				field[i][j] = 1;
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int[][] getField() {
		return field;
	}

}
