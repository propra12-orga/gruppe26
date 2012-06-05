package bomberman.game;

import java.io.IOException;
import java.net.UnknownHostException;

import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Exit;
import bomberman.gui.GameGui;

public class TwoPlayerGameServer extends Game {

	private final BomberHuman other;
	Network nw;

	public TwoPlayerGameServer(final Controls controls, final Exit exit,
			final ExplosionAreaCalculator eac, final GameGui gui)
			throws UnknownHostException, IOException {
		super(controls, exit, eac, gui);
		nw = new Network(true);
		this.other = new BomberHuman(25, 25, nw, true);
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
			nw.write(bman);
			controls.doSomethingWithInput(bman, bombs);
			other.getNetworkMovement(bombs);
			manageBombs();
			gui.draw(bombs, bman, exit);
			lastTickAt = System.currentTimeMillis();
		}
	}
}
