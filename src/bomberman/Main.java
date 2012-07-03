package bomberman;

import java.io.IOException;

/**
 * Main Class contains main method which starts the game...
 * 
 * @author Jan
 * 
 * 
 */
public class Main {

	/**
	 * main-method start the game
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {

		final Menu m = new Menu();
		m.startGame();
	}

}
