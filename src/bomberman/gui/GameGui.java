package bomberman.gui;

import java.util.List;
import java.util.Map;
import java.util.Set;

import bomberman.game.ExplosionAreaCalculator;
import bomberman.game.Wall;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.game.objects.EPowerUps;
import bomberman.game.objects.Exit;
import bomberman.game.objects.PowerUp;

/**
 * GameGui - draws everything game-related except the explosion smoke.
 * 
 * @author Jan
 * 
 */
public class GameGui {

	/**
	 * Length of a tile's side edge.
	 */
	private final int TILESIZE;
	/**
	 * Array contains collisions.
	 */
	private final int[][] field;
	/**
	 * Width of the field.
	 */
	private final int width;
	/**
	 * Height of the field.
	 */
	private final int height;
	/**
	 * ExplosionsAreaCalculator to calculate the explosion area.
	 */
	private final ExplosionAreaCalculator eac;

	/**
	 * Explosion graphics module.
	 */
	private final Explosion bombe = new Explosion();

	/**
	 * Constructor of the GameGUI initializes a given field with given tilesizes
	 * 
	 * @param field
	 *            - [int] fieldarray
	 * @param TILESIZE
	 *            - [int] size of tiles
	 * @param eac
	 *            - explosionareacalculator
	 */
	public GameGui(final int[][] field, final int TILESIZE,
			final ExplosionAreaCalculator eac) {
		this.field = field;
		this.TILESIZE = TILESIZE;
		this.width = field[0].length;
		this.height = field.length;
		this.eac = eac;
	}

	/**
	 * Draw Walls
	 */
	public void drawWalls() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (field[j][i] == 1) {
					StdDraw.picture(i * TILESIZE + TILESIZE / 2, j * TILESIZE
							+ TILESIZE / 2, "graphics/solid_wall.png");
				} else if (field[j][i] == 2) {
					StdDraw.picture(i * TILESIZE + TILESIZE / 2, j * TILESIZE
							+ TILESIZE / 2, "graphics/destwall2.png");
				}
			}
		}
	}

	/**
	 * Draw Floor
	 */
	public void drawFloor() {
		StdDraw.clear();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// if (field[j][i] == 0) {
				StdDraw.picture(i * TILESIZE + TILESIZE / 2, j * TILESIZE
						+ TILESIZE / 2, "graphics/floor2.png");
				// }
			}
		}
	}

	/**
	 * draws Bomfermen
	 * 
	 * @param bman
	 *            list of Bomfermen
	 */
	public void drawBomber(final List<BomberHuman> bman) {
		StdDraw.picture(bman.get(0).getPosX(), bman.get(0).getPosY(),
				"graphics/bomberman.png");
		if (bman.size() > 1) {
			StdDraw.picture(bman.get(1).getPosX(), bman.get(1).getPosY(),
					"graphics/bomferman.png");
		}
	}

	/**
	 * draw bombs
	 * 
	 * @param bombs
	 *            list of currently active bombs
	 */
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

	/**
	 * Draw explosions of bomb b
	 * 
	 * @param b
	 *            - bomb
	 */
	public void drawExplosion(final Bomb b) {
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
		if (b.getTimer() == -1) {
			for (int i = leftBound; i <= rightBound; i++) {
				if (eac.isInExplosionArea(b, i, arrPosY)) {
					if (i == leftBound) {
						bombe.dropBomb(i * TILESIZE + TILESIZE / 2, arrPosY
								* TILESIZE + TILESIZE / 2);
					} else if (i == rightBound) {
						bombe.dropBomb(i * TILESIZE + TILESIZE / 2, arrPosY
								* TILESIZE + TILESIZE / 2);
					} else {
						bombe.dropBomb(i * TILESIZE + TILESIZE / 2, arrPosY
								* TILESIZE + TILESIZE / 2);
					}
				}
			}

			for (int j = lowerBound; j <= upperBound; j++) {
				if (eac.isInExplosionArea(b, arrPosX, j)) {
					if (j == lowerBound) {
						bombe.dropBomb(arrPosX * TILESIZE + TILESIZE / 2, j
								* TILESIZE + TILESIZE / 2);
					} else if (j == upperBound) {
						bombe.dropBomb(arrPosX * TILESIZE + TILESIZE / 2, j
								* TILESIZE + TILESIZE / 2);
					} else {
						bombe.dropBomb(arrPosX * TILESIZE + TILESIZE / 2, j
								* TILESIZE + TILESIZE / 2);
					}
				}
			}
		}
		// StdDraw.filledSquare(i * TILESIZE + TILESIZE / 2, j
		// * TILESIZE + TILESIZE / 2, TILESIZE / 2);

		StdDraw.setPenColor(StdDraw.BLACK);
	}

	/**
	 * initializes draw-enviroment (i.e sets the scale and canvassize)
	 */
	public void initialize() {
		StdDraw.setCanvasSize(width * TILESIZE, height * TILESIZE);
		StdDraw.setXscale(0, width * TILESIZE);
		StdDraw.setYscale(0, height * TILESIZE);
	}

	/**
	 * Main draw-method, draw bombs, bomfermans and exit
	 * 
	 * @param bombs
	 *            - list of bombs to be drawn
	 * @param bmans
	 *            - list of bomfermans to be drawn
	 * @param exit
	 *            - exit to be drawn
	 * @param powerups
	 *            map of Walls to powerups to be drawn
	 */
	public void draw(final List<Bomb> bombs, final List<BomberHuman> bmans,
			final Exit exit, final Map<Wall, PowerUp> powerups) {
		drawFloor();
		drawExit(exit);
		drawPowerups(powerups);
		drawWalls();
		drawBomber(bmans);
		drawBombs(bombs);
		bombe.drawBombs();
		StdDraw.show();
	}

	/**
	 * Draws the powerups.
	 * 
	 * @param powerups
	 *            map of Walls to powerups to be drawn
	 */
	private void drawPowerups(final Map<Wall, PowerUp> powerups) {
		final Set<Wall> locations = powerups.keySet();

		for (Wall wall : locations) {
			EPowerUps ep = powerups.get(wall).getType();

			if (ep == EPowerUps.BOMBRANGE) {
				StdDraw.picture(wall.getX() * TILESIZE + TILESIZE / 2,
						wall.getY() * TILESIZE + TILESIZE / 2,
						"graphics/rangeup.png");
			} else if (ep == EPowerUps.BOMBRATIO) {
				StdDraw.picture(wall.getX() * TILESIZE + TILESIZE / 2,
						wall.getY() * TILESIZE + TILESIZE / 2,
						"graphics/bombup.png");
			} else if (ep == EPowerUps.SPEEDUP) {
				StdDraw.picture(wall.getX() * TILESIZE + TILESIZE / 2,
						wall.getY() * TILESIZE + TILESIZE / 2,
						"graphics/speedup.png");
			}
		}
	}

	/**
	 * Show "BOMF!" Message at loss
	 */
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

	/**
	 * Draws the exit
	 * 
	 * @param exit
	 *            - exit-object containing position and size of it
	 */
	private void drawExit(final Exit exit) {
		StdDraw.picture(exit.getArrayPosX() * TILESIZE + TILESIZE / 2,
				exit.getArrayPosY() * TILESIZE + TILESIZE / 2,
				"graphics/exit.png");
	}

	/**
	 * Show "Victory" Message
	 */
	public void won() {
		StdDraw.picture(width * TILESIZE / 2, height * TILESIZE / 2,
				"graphics/victory.png");
		StdDraw.show();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// we seriously don't care as this is just temporary
		}
	}

	/**
	 * Draws additionally to the victory screen a message in case you got a new
	 * highscore.
	 * 
	 * @param newScore
	 *            player achieved a new highscore
	 */
	public void highScore(final boolean newScore) {
		StdDraw.setPenColor(StdDraw.PINK);
		if (newScore) {
			StdDraw.text(width * TILESIZE / 2, 120, "new high score!");
		} else {
			StdDraw.text(width * TILESIZE / 2, 120, "no score for you");
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		won();
	}
}
