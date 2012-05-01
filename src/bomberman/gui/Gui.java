package bomberman.gui;

import java.util.List;

import messing.around.StdDraw;
import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.game.objects.Exit;

public class Gui {

	private final int TILESIZE;
	private final int[][] field;
	private final int width;
	private final int height;
	private final BomberHuman bman;
	private final Exit exit;
	private final List<Bomb> bombs;
	private final ExplosionAreaCalculator eac;

	public Gui(final int[][] field, final int TILESIZE, final List<Bomb> bombs,
			final BomberHuman bman, final Exit exit,
			final ExplosionAreaCalculator eac) {
		this.field = field;
		this.TILESIZE = TILESIZE;
		this.width = field[0].length;
		this.height = field.length;
		this.bman = bman;
		this.exit = exit;
		this.bombs = bombs;
		this.eac = eac;
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
		// StdDraw.circle(bman.getPosX(), bman.getPosY(), 5);
		StdDraw.picture(bman.getPosX(), bman.getPosY(),
				"graphics/bomberman.png");
	}

	public void drawBombs() {
		for (Bomb b : bombs) {
			if (b.isCurrentlyExploding()) {
				drawExplosion(b);
			} else {
				final int bombsprite = 11 - (11 * b.getTimer() / b
						.getMaxTimer());
				StdDraw.picture(b.getPosX(), b.getPosY() + 20, "graphics/bomb"
						+ bombsprite + ".png");
			}
		}
	}

	public void drawExplosion(Bomb b) {
		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrPosX = eac.getArrayPos(posX);
		final int arrPosY = eac.getArrayPos(posY);

		final int leftBound = eac.getLeftBoundsOfExplosion(arrPosX);
		final int rightBound = eac.getRightBoundsOfExplosion(arrPosX);
		final int upperBound = eac.getUpperBoundsOfExplosion(arrPosY);
		final int lowerBound = eac.getLeftBoundsOfExplosion(arrPosY);

		StdDraw.setPenColor(StdDraw.RED);

		// TODO: calculating too much here, a whole rectangle. but this is a lot
		// shorter.
		for (int i = leftBound; i <= rightBound; i++)
			for (int j = lowerBound; j <= upperBound; j++)
				if (eac.isInExplosionArea(b, i, j))
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	public void initialize() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);
		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);
	}

	public void draw() {
		drawWalls();
		drawExit();
		drawBomber();
		drawBombs();
		StdDraw.show();
	}

	public void lost() {
		StdDraw.clear();
		StdDraw.text(width * TILESIZE / 2, height * TILESIZE / 2, "BOMF!");
		StdDraw.show();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// we seriously don't care as this is just temporary
		}
	}

	private void drawExit() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(exit.getArrayPosX() * TILESIZE + TILESIZE / 2,
				exit.getArrayPosY() * TILESIZE + TILESIZE / 2, TILESIZE / 2);
		StdDraw.setPenColor(StdDraw.BLACK);
	}

	public void won() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.text(width * TILESIZE / 2, height * TILESIZE / 2, "VICTORY");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.show();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// we seriously don't care as this is just temporary
		}
	}
}
