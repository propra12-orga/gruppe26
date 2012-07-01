package bomberman.game;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import bomberman.game.character.BomberHuman;
import bomberman.game.network.Client;
import bomberman.game.network.Reader;
import bomberman.game.network.Server;
import bomberman.game.objects.Bomb;

/**
 * 
 * Controls Networkplay
 * 
 */
public class Network {

	public Reader r;

	/**
	 * Constructs Server and Client.
	 * 
	 * @param server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Network(final boolean server, final String level)
			throws UnknownHostException, IOException {
		if (server) {
			try {
				r = new Server(level);
			} catch (IOException e) {
				r = null;
			}
		} else {
			r = new Client();
			if (r == null) {
				System.out.println("debugwtf");
			}
		}
	}

	/**
	 * Get the position and Bombs of other Bomferman
	 * 
	 * @param bman
	 *            - bomferman to be read
	 * @param bombs
	 *            - bombs of bomferman
	 */
	public void read(final BomberHuman bman, final List<Bomb> bombs) {
		r.read(bman, bombs);
	}

	/**
	 * Write the position and Bombs of other Bomferman
	 * 
	 * @param bman
	 *            - bomferman to be written
	 */
	public void write(final BomberHuman bman) {
		r.write(bman.getMove() + "\n" + bman.getBomb());
	}

	public Level readLevel() {
		return r.readLevel();
	}
}
