package bomberman.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bomberman.HighScore;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.game.objects.EPowerUps;
import bomberman.game.objects.Exit;
import bomberman.game.objects.PowerUp;
import bomberman.gui.GameGui;
import bomberman.gui.StdDraw;

/**
 * Game Object After constructing a Game object, it requires to be started via
 * {@link #start()} separately. Once started, it should not terminate until
 * either you win or lose. Only one Game at a time should be active, so don't
 * use it in threads, else the GUI will probably get messed up.
 * 
 * @author philipp
 * 
 */
public class Game {

	protected Map<Wall, PowerUp> powerups;
	protected final List<BomberHuman> bman = new ArrayList<BomberHuman>();
	protected Exit exit;
	protected final List<Bomb> bombs = new ArrayList<Bomb>();
	// let's see if we can use this interface for something productive
	private final List<Character> enemies = new ArrayList<Character>();
	protected GameGui gui;
	protected Controls controls;
	protected ExplosionAreaCalculator eac;

	protected boolean alive = true;
	protected boolean won = false;

	protected long lastTickAt = System.currentTimeMillis();
	private long numberOfTicks = 0;

	/**
	 * Associates a Game with controls, an Exit, an EAC and a GUI. It will also
	 * create a BomberHuman that is controlled by the player.
	 * 
	 * @param controls
	 * @param exit
	 * @param eac
	 * @param gui
	 */
	public Game(final Controls controls, final Exit exit,
			final ExplosionAreaCalculator eac, final GameGui gui, final Board b) {
		bman.add(new BomberHuman(true, 25, 25));
		this.exit = exit;
		this.eac = eac;
		this.gui = gui;
		this.controls = controls;
		StdDraw.reference = this;

		final Level l = new Level(b.getField(), exit);
		this.powerups = l.generatePowerUps();
	}

	protected Game(final Level l, final Controls c,
			final ExplosionAreaCalculator eac, final GameGui gui) {
		this.controls = c;
		this.eac = eac;
		this.gui = gui;
		this.exit = l.getEx();
		bman.add(new BomberHuman(true, 25, 25));
		this.powerups = new HashMap<Wall, PowerUp>();
	}

	protected Game() {
		this.gui = null;
		this.exit = null;
		this.eac = null;
		this.controls = null;
		this.powerups = new HashMap<Wall, PowerUp>();
	}

	/**
	 * Starts the Game loop.
	 */
	public void start() {
		gui.initialize();
		loop();
	}

	public void loop() {
		while (alive && !won) {
			final long diff = System.currentTimeMillis() - lastTickAt;
			if (diff < Settings.MINTICKLENGTH) {
				try {
					Thread.sleep(Settings.MINTICKLENGTH - diff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			checkWin();
			controls.doSomethingWithInput(bman.get(0), bombs);
			checkPowerUp();
			manageBombs();
			gui.draw(bombs, bman, exit, powerups);
			lastTickAt = System.currentTimeMillis();
			numberOfTicks++;
		}
		if (!alive) {
			gui.lost();
		} else {
			final int score = HighScore.generateScore(numberOfTicks);
			if (HighScore.newScore(score))
				System.out.println("added score");
			else
				System.out.println("no score for you");
			HighScore.printScore();
		}
		StdDraw.reference = null;

	}

	private void checkPowerUp() {
		final BomberHuman bomfman = bman.get(0);
		final Wall bmanArrayPos = new Wall(bomfman.getPosX() / 50,
				bomfman.getPosY() / 50);

		if (powerups.containsKey(bmanArrayPos)) {
			PowerUp pup = powerups.get(bmanArrayPos);
			if (pup.getType() == EPowerUps.BOMBRANGE) {
				eac.bombRangeUp();
			} else if (pup.getType() == EPowerUps.BOMBRATIO) {
				controls.bombRatioUp();
			} else if (pup.getType() == EPowerUps.SPEEDUP) {
				bman.get(0).boostSpeed(1);
			}

			powerups.remove(bmanArrayPos);
		}
	}

	protected void manageBombs() {
		final List<Integer> exploded = new ArrayList<Integer>();

		int count = 0;

		for (Bomb b : bombs) {
			b.tick();
			final boolean exists = b.isStillThere();

			if (!exists) {
				exploded.add(count);
			}
			count++;
		}

		count = 0;
		// Fuer jede explodierende Bombe:
		// try to kill stuff!!!
		for (Integer integer : exploded) {
			final Bomb b = bombs.get(integer - count);
			if (b.getTimer() == -1) {
				tryToKillStuff(b);
				eac.affectedWalls(b);
			}
			// remove exploding bomb from Bomblist
			if (!b.isCurrentlyExploding()) {
				bombs.remove(integer - count);
				count++;
			}
		}
	}

	/**
	 * Try to kill stuff, checks if a bomferman is in explosionarea if yes: KILL
	 * IT!!!
	 * 
	 * @param b
	 *            - bomb which tries to kill stuff
	 */
	protected void tryToKillStuff(final Bomb b) {
		// right now, there's only bomferman. as soon as enemies are
		// implemented, we should add a list of Characters or something like
		// that. we will probably need that interface at this point.
		for (Bomb bomb : bombs)
			if (bomb != b && !bomb.isCurrentlyExploding())
				if (eac.isInExplosionArea(b, bomb)) {
					bomb.goBomf();
				}
		// bomferman in explosion area of bomb?
		if (eac.isInExplosionArea(b, bman.get(0))) {
			// KILL IT!
			alive = false;
		}
	}

	protected void checkWin() {
		if (!enemies.isEmpty())
			return;

		if (bman.get(0).getPosX() < exit.getPosX() + 20
				&& bman.get(0).getPosX() > exit.getPosX() - 20
				&& bman.get(0).getPosY() < exit.getPosY() + 20
				&& bman.get(0).getPosY() > exit.getPosY() - 20) {
			gui.won();
			won = true;
		}
	}

	public GameGui getGameGui() {
		StdDraw.reference = this;
		return gui;
	}
}
