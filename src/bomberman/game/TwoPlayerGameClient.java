package bomberman.game;

import java.io.IOException;
import java.net.UnknownHostException;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;

public class TwoPlayerGameClient extends Game {

	// private final BomberHuman other;
	Network nw;

	@Deprecated
	private TwoPlayerGameClient(final Controls controls, final Exit exit,
			final ExplosionAreaCalculator eac, final GameGui gui, final Board b)
			throws UnknownHostException, IOException {
		super(controls, exit, eac, gui, b);
		nw = new Network(false, null);
		bman.add(new BomberHuman(25, 75, nw, false));
	}

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
	}

	@Override
	protected void loop() {
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
			checkWin();
			nw.write(bman.get(0));
			bman.get(1).getNetworkMovement(bombs);
			controls.doSomethingWithInput(bman.get(0), bombs);

			manageBombs();
			gui.draw(bombs, bman, exit);
			lastTickAt = System.currentTimeMillis();
		}
	}
}
