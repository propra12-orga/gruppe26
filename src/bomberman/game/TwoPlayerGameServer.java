package bomberman.game;

import java.io.IOException;
import java.util.HashMap;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.game.objects.Exit;
import bomberman.game.objects.PowerUp;
import bomberman.gui.GameGui;
import bomberman.gui.StdDraw;

/**
 * Serverclass for Twoplayer game, inherits methods from {@link Game}
 * 
 */
public class TwoPlayerGameServer extends Game {

	/**
	 * Network object. Should contain a Server-Reader later on.
	 */
	Network nw;

	/**
	 * Server(Host) for the Networkmultiplayer
	 * 
	 * @param controls
	 *            - controls of player
	 * @param exit
	 *            - the exit
	 * @param eac
	 *            - ExplosionAreaCalculator
	 * @param gui
	 *            - Graphical User Interface
	 * @param b
	 *            Board object
	 * @throws IOException
	 *             on network issues
	 */

	public TwoPlayerGameServer(final Controls controls, final Exit exit,
			final ExplosionAreaCalculator eac, final GameGui gui, final Board b)
			throws IOException {
		super(controls, exit, eac, gui, b);
		powerups = new HashMap<Wall, PowerUp>();
		StdDraw.reference = null;
		final Level l = new Level(b.getField(), exit);
		nw = new Network(true, l.toString());
		bman.add(new BomberHuman(25, 25, nw, true));

		// no powerups in network, hence:
		eac.bombRangeUp();
		eac.bombRangeUp();
	}

	@Override
	public void loop() {
		while (alive && !won) {
			final long diff = System.currentTimeMillis() - lastTickAt;
			if (diff < Settings.MINTICKLENGTH) {
				try {
					Thread.sleep(Settings.MINTICKLENGTH - diff);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nw.write(bman.get(0));
			controls.doSomethingWithInput(bman.get(0), bombs);
			bman.get(1).getNetworkMovement(bombs);
			manageBombs();
			checkWin();

			gui.draw(bombs, bman, exit, powerups);
			lastTickAt = System.currentTimeMillis();
		}

		nw.write(bman.get(0));

		if (alive) {
			gui.won();
		} else {
			gui.lost();
		}
	}

	@Override
	protected void checkWin() {
		if (bman.size() == 1) {
			won = true;
		}
	}

	@Override
	protected void tryToKillStuff(final Bomb b) {
		super.tryToKillStuff(b);
		if (eac.isInExplosionArea(b, bman.get(1))) {
			bman.remove(1);
		}
	}
}
