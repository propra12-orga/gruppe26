package bomberman;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Main Class Starts the game...
 * 
 * @author Jan
 * 
 * 
 */
public class Main {

	public static void main(final String[] args) throws UnknownHostException,
			IOException {
		final Menu m = new Menu();
		m.startGame();
	}

}
