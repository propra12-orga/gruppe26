package bomberman.game;

import java.io.IOException;
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

	/**
	 * Reader object - is an instance of Server if Network is constructed so, an
	 * instance of Client otherwise.
	 */
	public Reader r;

	/**
	 * Constructs the server and client.
	 * 
	 * @param server
	 *            true, if caller is acting as a server, false if client
	 * @param level
	 *            only needed if caller is server, ignored otherwise
	 * @throws IOException
	 *             If server does not exist at entered IP.
	 */
	public Network(final boolean server, final String level) throws IOException {
		if (server) {
			try {
				r = new Server(level);
			} catch (IOException e) {
				r = null;
			}
		} else {
			r = new Client();
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

	/**
	 * Reads a Level from Network.
	 * 
	 * @return Level object or null.
	 */
	public Level readLevel() {
		return r.readLevel();
	}
}
