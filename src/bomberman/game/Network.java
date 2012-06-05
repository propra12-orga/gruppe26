package bomberman.game;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import messing.around.network.Client;
import messing.around.network.Reader;
import messing.around.network.Server;
import bomberman.game.character.BomberHuman;
import bomberman.game.objects.Bomb;

public class Network {

	Reader r;

	public Network(final boolean server) throws UnknownHostException,
			IOException {
		if (server) {
			try {
				r = new Server();
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

	public void read(final BomberHuman bman, final List<Bomb> bombs) {
		r.read(bman, bombs);
	}

	public void write(final BomberHuman bman) {
		r.write(bman.getMove() + "\n" + bman.getBomb());
	}
}
