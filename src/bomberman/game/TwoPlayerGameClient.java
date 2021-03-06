package bomberman.game;

import java.io.IOException;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.GameGui;

/**
 * Two Player GameClient, inherits from {@link Game}
 * 
 */
public class TwoPlayerGameClient extends Game {

	/**
	 * Network object. Should contain a Client-Reader later on.
	 */
	Network nw;

	/**
	 * Constructs a two-player gameclient
	 * 
	 * @throws IOException
	 *             if server does not exist
	 */
	public TwoPlayerGameClient() throws IOException {
		super();
		nw = new Network(false, null);
		if (nw.r == null)
			throw new IOException("cannot initialize network");
		bman.add(new BomberHuman(25, 75, nw, false));
		final int TILESIZE = Settings.TILESIZE;
		final Level l = nw.readLevel();
		final int[][] field = l.getBoard();
		this.exit = l.getEx();
		this.eac = new ExplosionAreaCalculator(field, TILESIZE);
		this.gui = new GameGui(field, TILESIZE, eac);
		this.controls = new Controls(new Board(field), TILESIZE);
		bman.add(new BomberHuman(25, 25, nw, false));

		// no powerups in network, hence:
		eac.bombRangeUp();
		eac.bombRangeUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bomberman.game.Game#loop()
	 */
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
			bman.get(1).getNetworkMovement(bombs);
			controls.doSomethingWithInput(bman.get(0), bombs);
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
