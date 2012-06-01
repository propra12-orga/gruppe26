package bomberman.gui;

import java.util.List;

import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.game.objects.Exit;

public class GameGui {

	private final int TILESIZE;
	private final int[][] field;
	private final int width;
	private final int height;
	private final ExplosionAreaCalculator eac;

	public GameGui(final int[][] field, final int TILESIZE,
			final ExplosionAreaCalculator eac) {
		this.field = field;
		this.TILESIZE = TILESIZE;
		this.width = field[0].length;
		this.height = field.length;
		this.eac = eac;
	}

	public void drawWalls() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (field[j][i] == 1)
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);
				else if (field[j][i] == 2) {
					StdDraw.setPenColor(StdDraw.PINK);
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);
					StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
	}

	public void drawFloor() {
		StdDraw.clear();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (field[j][i] == 0) {
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2, TILESIZE / 2);
					StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
	}

	public void drawBomber(final BomberHuman bman) {
		StdDraw.picture(bman.getPosX(), bman.getPosY(),
				"graphics/bomberman.png");
	}

	public void drawBombs(final List<Bomb> bombs) {
		for (Bomb b : bombs) {
			if (b.isCurrentlyExploding()) {
				drawExplosion(b);
			} else {
				final int bombsprite = 11 - (11 * b.getTimer() / b
						.getMaxTimer());
				StdDraw.picture(b.getPosX(), b.getPosY() + 20,
						"graphics/bomb/bomb" + bombsprite + ".png");
			}
		}
	}

	public void drawExplosion(Bomb b) {
		final int posX = b.getPosX();
		final int posY = b.getPosY();
		final int arrPosX = eac.getArrayPos(posX);
		final int arrPosY = eac.getArrayPos(posY);

		final int leftBound;
		final int rightBound;
		final int upperBound;
		final int lowerBound;

		if (b.getTimer() == 0) {
			leftBound = eac.getLeftBoundsOfExplosion(arrPosX, arrPosY);
			rightBound = eac.getRightBoundsOfExplosion(arrPosX, arrPosY);
			upperBound = eac.getUpperBoundsOfExplosion(arrPosX, arrPosY);
			lowerBound = eac.getLowerBoundsOfExplosion(arrPosX, arrPosY);

			b.saveExplosionBorders(leftBound, rightBound, upperBound,
					lowerBound);
		} else {
			leftBound = b.getExplosionBorderLeft();
			rightBound = b.getExplosionBorderRight();
			upperBound = b.getExplosionBorderTop();
			lowerBound = b.getExplosionBorderBottom();

		}
		StdDraw.setPenColor(StdDraw.RED);

		// TODO: calculating too much here, a whole rectangle. but this is a lot
		// shorter.
		for (int i = leftBound; i <= rightBound; i++) {
			if (eac.isInExplosionArea(b, i, arrPosY)) {
				if (i == leftBound)
					StdDraw.picture(i * TILESIZE + TILESIZE / 2, arrPosY
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_edge_l.png");
				else if (i == rightBound)
					StdDraw.picture(i * TILESIZE + TILESIZE / 2, arrPosY
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_edge_r.png");
				else
					StdDraw.picture(i * TILESIZE + TILESIZE / 2, arrPosY
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_hori.png");
			}
		}

		for (int j = lowerBound; j <= upperBound; j++) {
			if (eac.isInExplosionArea(b, arrPosX, j)) {
				if (j == lowerBound)
					StdDraw.picture(arrPosX * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_edge_b.png");
				else if (j == upperBound)
					StdDraw.picture(arrPosX * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_edge_t.png");
				else
					StdDraw.picture(arrPosX * TILESIZE + TILESIZE / 2, j
							* TILESIZE + TILESIZE / 2,
							"graphics/explosion/explosion_vert.png");
			}
		}
		// StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
		// * TILESIZE + TILESIZE / 2, TILESIZE / 2);

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	public void initialize() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);
		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);
	}

	public void draw(final List<Bomb> bombs, final BomberHuman bman,
			final Exit exit) {
		drawFloor();
		drawExit(exit);
		drawWalls();
		drawBomber(bman);
		drawBombs(bombs);
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

	private void drawExit(final Exit exit) {
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
