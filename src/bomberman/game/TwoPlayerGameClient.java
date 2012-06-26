package bomberman.game;

import java.io.IOException;
import java.net.UnknownHostException;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;
import bomberman.gui.GameGui;

/**
 * Two Player GameClient, inherits from {@link #Game}
 * 
 */
public class TwoPlayerGameClient extends Game {

	Network nw;

	/**
	 * Constructs a two-player gameclient
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public TwoPlayerGameClient() throws UnknownHostException, IOException {
		super();
		nw = new Network(false, null);
		bman.add(new BomberHuman(25, 75, nw, false));
		final int TILESIZE = Settings.TILESIZE;
		final Level l = nw.readLevel();
		final int[][] field = l.getBoard();
		this.exit = l.getEx();
		this.eac = new ExplosionAreaCalculator(field, TILESIZE);
		this.gui = new GameGui(field, TILESIZE, eac);
		this.controls = new Controls(new Board(field), TILESIZE);
		bman.add(new BomberHuman(25, 25, nw, false));
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
			if (diff < 5) {
				try {
					Thread.sleep(5 - diff);
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
			gui.draw(bombs, bman, exit);
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
