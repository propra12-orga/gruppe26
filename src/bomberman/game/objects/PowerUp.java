package bomberman.game.objects;


public class PowerUp {

	private final EPowerUps type;

	/**
	 * Constructs a PowerUp of type type
	 * 
	 * @param type
	 *            - type of powerup, see EPowerUps.java
	 */
	public PowerUp(final EPowerUps type) {
		this.type = type;
	}

	/**
	 * Get the type of powerup
	 * 
	 * @return type of powerup
	 */
	public EPowerUps getType() {
		return type;
	}

}
