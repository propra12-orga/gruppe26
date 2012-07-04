package bomberman;

/**
 * Main Class contains main method which starts the game...
 * 
 * @author Jan
 * 
 * 
 */
public class Main {

	/**
	 * main-method opens the Menu
	 * 
	 * @param args
	 *            Call arguments (ignored)
	 */
	public static void main(final String[] args) {
		final Menu m = new Menu();
		m.startGame();
	}

}
