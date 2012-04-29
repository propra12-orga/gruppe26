package bomberman.gui;

import java.util.List;

import messing.around.StdDraw;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public class Gui {

	private final int TILESIZE;
	private final int[][] field;
	private final int width;
	private final int height;
	private final BomberHuman bman;
	private final List<Bomb> bombs;

	public Gui(final int[][] field, final int TILESIZE, final List<Bomb> bombs,
			final BomberHuman bman) {
		this.field = field;
		this.TILESIZE = TILESIZE;
		this.width = field[0].length;
		this.height = field.length;
		this.bman = bman;
		this.bombs = bombs;
	}

	public void drawWalls() {
		StdDraw.clear();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (field[j][i] != 0)
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);
				else {
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);
					StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
	}

	public void drawBomber() {
		StdDraw.circle(bman.getPosX(), bman.getPosY(), 5);
	}

	public void drawBombs() {
		for (Bomb b : bombs) {
			StdDraw.filledCircle(b.getPosX(), b.getPosY(), 5);
		}
	}

	public void initialize() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);
		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);
	}

	public void draw() {
		drawWalls();
		drawBomber();
		drawBombs();
		StdDraw.show();
	}
}
