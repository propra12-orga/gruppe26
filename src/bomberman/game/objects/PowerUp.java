package bomberman.game.objects;


/**
 * Powerup
 * 
 * Contains: - a constructor to generate a powerup of one of three possible
 * types and a getter-method for returning the type of the powerup
 */
public class PowerUp {

	/**
	 * type of Powerup
	 */
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
